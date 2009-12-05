
package pions.model;

import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.DateTime;
import com.google.gdata.data.Link;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.acl.AclEntry;
import com.google.gdata.data.acl.AclRole;
import com.google.gdata.data.acl.AclScope;
import com.google.gdata.data.calendar.CalendarAclRole;
import com.google.gdata.data.calendar.CalendarEntry;
import com.google.gdata.data.calendar.CalendarFeed;
import com.google.gdata.data.calendar.TimeZoneProperty;
import com.google.gdata.data.extensions.EventEntry;
import com.google.gdata.data.extensions.EventFeed;
import com.google.gdata.data.extensions.When;
import com.google.gdata.data.extensions.Who;
import com.google.gdata.data.extensions.Who.AttendeeStatus;
import com.google.gdata.data.extensions.Who.AttendeeType;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;
import java.awt.Desktop;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;
import java.util.Iterator;
import java.util.TimeZone;
import pions.model.Alert.AlertType;
import pions.model.ContactInfo.EmailAddress;
import pions.model.ModelException.AlertClassException;
import pions.model.ModelException.NotLoggedInException;

/**
 * This class is completely dependent on the Gmail class. If the Gmail class
 * has not been initialized properly, neither will this class.
 * 
 */
public class Calendar implements Serializable, AbstractAlert {
    private final static String OWN_CALENDARS = CalendarService.CALENDAR_ROOT_URL
            + "default/owncalendars/full";
    private final static String ACL_LIST = "http://schemas.google.com/acl/2007#accessControlList";
    private String calendar_name;
    private URL html_link;

    /**
     * Creates and initializes a calendar if init is true. The variable init
     * should only be true if the Gmail class has been tested using isValid().
     * @param calendar_name
     * @param init
     * @throws AuthenticationException
     * @throws MalformedURLException
     * @throws ServiceException
     * @throws IOException
     * @throws pions.model.ModelException.NotLoggedInException
     */
    public Calendar(String calendar_name) throws AuthenticationException,
            MalformedURLException, ServiceException, IOException, NotLoggedInException {
        this.calendar_name = calendar_name;
        create();
    }

    private void create() throws AuthenticationException, MalformedURLException,
            ServiceException, IOException, NotLoggedInException {
        
        // Create the calendar entry
        CalendarEntry active_calendar = new CalendarEntry();
        active_calendar.setTitle(new PlainTextConstruct(calendar_name));
        active_calendar.setTimeZone(new TimeZoneProperty(TimeZone.getDefault().getID()));
        active_calendar.setCanEdit(true);

        // GoogleCalendar created
        active_calendar = Calendars.getService().insert(new URL(OWN_CALENDARS), active_calendar);

        // Valid Link.Rel's: ALTERNATE, ENTRY_EDIT, SELF
        html_link = new URL(active_calendar.getLink(Link.Rel.ALTERNATE, Link.Type.ATOM).getHref());
    }

    public URI getReadLink()
            throws NotLoggedInException, AuthenticationException,
            ServiceException, IOException, URISyntaxException {
        return new URI(Calendars.getService().getFeed(html_link, CalendarFeed.class)
                .getLink(Link.Rel.ALTERNATE, Link.Type.HTML).getHref());
    }

    public void shareRead(String gmail_address)
            throws NotLoggedInException, AuthenticationException,
            ServiceException, MalformedURLException, IOException {
        share(html_link, gmail_address, CalendarAclRole.READ);
    }

    private void share(URL url, String gmail_address, AclRole rights)
            throws NotLoggedInException, AuthenticationException,
            ServiceException, MalformedURLException, IOException {
        AclEntry entry = new AclEntry();
        entry.setScope(new AclScope(AclScope.Type.USER, gmail_address));
        entry.setRole(rights);

        // This algorithm should retrieve the ACL list for this calendar's feed.
        Calendars.getService().insert(
                new URL(Calendars.getService().getFeed(url, CalendarFeed.class)
                .getLink(ACL_LIST, Link.Type.ATOM).getHref()
                ), entry);
    }

    public void drop(EventEntry entry)
            throws ServiceException, IOException,
            AuthenticationException, NotLoggedInException {
        entry.setContent(new PlainTextConstruct(EmployeeSingleton.getInstance().getGmail().getGmailAddress().toString()));
        Calendars.getService().update(html_link, entry);
    }

    //TODO implement the positions class, and/or allow multiple employees
    public void addEvent(EmailAddress gmail_address, String title,
            String details, Date start, Date end)
            throws NotLoggedInException, AuthenticationException,
            ServiceException, IOException {
        EventEntry entry = new EventEntry();

        //default values
        entry.setCanEdit(true);

        //user defined values
        entry.setTitle(new PlainTextConstruct(title));
        
        entry.setContent(new PlainTextConstruct(details));

        When when = new When();
        when.setStartTime(new DateTime(start));
        when.setEndTime(new DateTime(end));
        entry.addTime(when);

        //guest list, optional extension
        if(gmail_address != null){
            Who who = new Who();
            who.setEmail(gmail_address.getAddress());
            who.setValueString(gmail_address.getPersonal());
            who.setAttendeeStatus(AttendeeStatus.EVENT_ACCEPTED);
            who.setAttendeeType(AttendeeType.EVENT_REQUIRED);
            entry.addParticipant(who);
        }

        Calendars.getService().insert(html_link, entry);
    }

    public void delete(int index)
            throws NotLoggedInException, AuthenticationException,
            ServiceException, IOException {
        Calendars.getService().getFeed(html_link, CalendarFeed.class).getEntries().get(index).delete();
    }

    public Iterator<EventEntry> getEvents()
            throws NotLoggedInException, AuthenticationException,
            ServiceException, IOException {
        return Calendars.getService().getFeed(html_link, EventFeed.class).getEntries().iterator();
    }

    public EventEntry getEvent(int index)
            throws NotLoggedInException, AuthenticationException,
            ServiceException, IOException {
        return Calendars.getService().getFeed(html_link, EventFeed.class).getEntries().get(index);
    }

    public static String parseTitle(EventEntry entry){
        return entry.getTitle().getPlainText();
    }

    public static String parseText(EventEntry entry){
        return entry.getPlainTextContent();
    }

    public static String parseStartTime(EventEntry entry){
        //This last part is messy because gdata refused to parse the times
        String xml_blob = entry.getXmlBlob().getBlob();
        String lookup = "";
        int index = xml_blob.indexOf(lookup = "<gd:when") + lookup.length();
        int start_index1 = xml_blob.indexOf(lookup = "startTime='", index) + lookup.length();
        int start_index2 = xml_blob.indexOf(lookup = "'", start_index1);

        return DateTime.parseDateTime(xml_blob.substring(start_index1, start_index2)).toUiString();
    }

    public static String parseEndTime(EventEntry entry){
        //This last part is messy because gdata refused to parse the times
        String xml_blob = entry.getXmlBlob().getBlob();
        String lookup = "";
        int index = xml_blob.indexOf(lookup = "<gd:when") + lookup.length();
        int start_index1 = xml_blob.indexOf(lookup = "startTime='", index) + lookup.length();
        int start_index2 = xml_blob.indexOf(lookup = "'", start_index1);
        int end_index1 = xml_blob.indexOf(lookup = "endTime='", start_index2) + lookup.length();
        int end_index2 = xml_blob.indexOf(lookup = "'", end_index1);

        return DateTime.parseDateTime(xml_blob.substring(end_index1, end_index2)).toUiString();
    }

    public void acceptAlert(AlertType type, EmailAddress sender)
            throws AlertClassException, NotLoggedInException,
            AuthenticationException, ServiceException, IOException,
            URISyntaxException {
        switch(type){
            case Availability:
                Desktop.getDesktop().browse(this.getReadLink());
                break;
            case WorkSchedule:
                EmployeeSingleton.getInstance().getCalendars().setWorkSchedule(this);
                break;
            default:
                throw new AlertClassException(this.getClass(), type.getAssociatedClass());
        }
    }

    public void rejectAlert(AlertType type) throws AlertClassException {
        switch(type){
            case Availability:
                //DONOTHING
                break;
            case WorkSchedule:
                //DONOTHING
                break;
            default:
                throw new AlertClassException(this.getClass(), type.getAssociatedClass());
        }
    }

    public void ignoreAlert(AlertType type) throws AlertClassException {
        switch(type){
            case Availability:
                //DONOTHING
                break;
            case WorkSchedule:
                //DONOTHING
                break;
            default:
                throw new AlertClassException(this.getClass(), type.getAssociatedClass());
        }
    }

    //TODO getDetails()
    public String getDetails() {
        StringBuffer buffer = new StringBuffer();

        buffer.append("Name: " + calendar_name);

        return buffer.toString();
    }
}

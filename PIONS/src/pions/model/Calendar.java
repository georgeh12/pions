
package pions.model;

import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.DateTime;
import com.google.gdata.data.Link;
import com.google.gdata.data.PlainTextConstruct;
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
    private final static String CALENDAR_SERVICE = "PIONS Calendar";
    private final static String CALENDAR_SUFFIX = "/owncalendars/full";
    private final static String ACL_LINK =
            "http://www.google.com/calendar/feeds/default/private/full";
    private final URL CALENDAR_FEED;
    private transient CalendarService service = null;
    private String calendar_name;
    private URL html_link;
    private String read_link;
    private String gmail_username;
    private String gmail_password;

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
    public Calendar(String calendar_name, String gmail_username, String gmail_password) throws AuthenticationException,
            MalformedURLException, ServiceException, IOException, NotLoggedInException {
        this.calendar_name = calendar_name;
        CALENDAR_FEED = new URL(CalendarService.CALENDAR_ROOT_URL + gmail_username + CALENDAR_SUFFIX);
        this.gmail_username = gmail_username;
        this.gmail_password = gmail_password;

        create();
    }

    private CalendarService getService() throws AuthenticationException{
        if(service == null){
            service = new CalendarService(CALENDAR_SERVICE);
            service.setUserCredentials(gmail_username, gmail_password);
        }

        return service;
    }

    private void create() throws AuthenticationException, MalformedURLException,
            ServiceException, IOException, NotLoggedInException {
        
        // Create the calendar entry
        CalendarEntry active_calendar = new CalendarEntry();
        active_calendar.setTitle(new PlainTextConstruct(calendar_name));
        active_calendar.setTimeZone(new TimeZoneProperty(TimeZone.getDefault().getID()));
        active_calendar.setCanEdit(true);

        // GoogleCalendar created
        active_calendar = getService().insert(CALENDAR_FEED, active_calendar);

        // Valid Link.Rel's: ALTERNATE, ENTRY_EDIT, SELF
        html_link = new URL(active_calendar.getLink(Link.Rel.ALTERNATE, Link.Type.ATOM).getHref());
        read_link = getService().getFeed(html_link, CalendarFeed.class)
                .getLink(Link.Rel.ALTERNATE, Link.Type.HTML).getHref() + "&ctz=America/New_York";
    }

    public String getReadLink() {
        return read_link;
    }

    public void shareRead(String gmail_address)
            throws NotLoggedInException, AuthenticationException,
            ServiceException, MalformedURLException, IOException {
        share(gmail_address, CalendarAclRole.READ);
    }

    /**
     * Should add the user to the ACL for the specified calendar feed or events.
     * Not working at this time.
     * @param gmail_address
     * @param rights
     * @throws pions.model.ModelException.NotLoggedInException
     * @throws AuthenticationException
     * @throws ServiceException
     * @throws MalformedURLException
     * @throws IOException
     */
    private void share(String gmail_address, AclRole rights)
            throws NotLoggedInException, AuthenticationException,
            ServiceException, MalformedURLException, IOException {
        for(CalendarEntry entry: getService().getFeed(html_link, CalendarFeed.class).getEntries()){
            entry.addExtension(new AclScope(AclScope.Type.USER, gmail_address));
            entry.addExtension(rights);
            entry.update();
        }
    }

    public void drop(EventEntry entry)
            throws ServiceException, IOException,
            AuthenticationException, NotLoggedInException {
        entry.setContent(new PlainTextConstruct(EmployeeSingleton.getInstance().getGmail().getGmailAddress().toString()));
        getService().update(html_link, entry);
    }

    //TODO integrate the positions class, and/or allow multiple employees
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

        getService().insert(html_link, entry);
    }

    public void delete(int index)
            throws NotLoggedInException, AuthenticationException,
            ServiceException, IOException {
        getService().getFeed(html_link, CalendarFeed.class).getEntries().get(index).delete();
    }

    public Iterator<EventEntry> getEvents()
            throws NotLoggedInException, AuthenticationException,
            ServiceException, IOException {
        return getService().getFeed(html_link, EventFeed.class).getEntries().iterator();
    }

    public EventEntry getEvent(int index)
            throws NotLoggedInException, AuthenticationException,
            ServiceException, IOException {
        return getService().getFeed(html_link, EventFeed.class).getEntries().get(index);
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

    public void acceptAlert(AlertType type)
            throws AlertClassException, NotLoggedInException,
            AuthenticationException, ServiceException, IOException,
            URISyntaxException {
        switch(type){
            case Availability:
                Desktop.getDesktop().browse(new URI(getReadLink()));
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

    public String getDetails() {
        StringBuffer buffer = new StringBuffer();

        buffer.append("Name: " + calendar_name);
        buffer.append("\nInternet Address:\n" + getReadLink());

        return buffer.toString();
    }
}

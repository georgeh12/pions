
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
import com.google.gdata.data.extensions.When;
import com.google.gdata.data.extensions.Who;
import com.google.gdata.data.extensions.Who.AttendeeStatus;
import com.google.gdata.data.extensions.Who.AttendeeType;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;
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
 * @author George
 */
public class CalendarData implements Serializable, AbstractAlert {
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
    public CalendarData(String calendar_name) throws AuthenticationException,
            MalformedURLException, ServiceException, IOException, NotLoggedInException {
        this.calendar_name = calendar_name;
        create();
    }

    private void create() throws AuthenticationException, MalformedURLException,
            ServiceException, IOException, NotLoggedInException {
        
        // Create the calendar entry
        CalendarEntry active_calendar = new CalendarEntry();
        active_calendar.setTitle(new PlainTextConstruct(calendar_name));
        active_calendar.setTimeZone(new TimeZoneProperty(TimeZone.getDefault().getDisplayName()));
        active_calendar.setCanEdit(true);

        //TODO testing purposes only
        // GoogleCalendar created
        //active_calendar = Calendars.getService().insert(new URL(OWN_CALENDARS), active_calendar);

        // Valid Link.Rel's: ALTERNATE, ENTRY_EDIT, SELF
        //html_link = new URL(active_calendar.getLink(Link.Rel.ALTERNATE, Link.Type.ATOM).getHref());
        html_link = new URL("http://www.google.com/calendar/feeds/rn6gae36n49f3o7qbmc97ls7ak%40group.calendar.google.com/private/full");
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

    public void shareEdit(String gmail_address)
            throws NotLoggedInException, AuthenticationException,
            ServiceException, MalformedURLException, IOException {
        share(html_link, gmail_address, CalendarAclRole.EDITOR);
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

    public void drop(CalendarEntry entry)
            throws ServiceException, IOException,
            AuthenticationException, NotLoggedInException {
        entry.setService(Calendars.getService());
        entry.setContent(new PlainTextConstruct(EmployeeSingleton.getInstance().getGmail().getGmailAddress().toString()));
    }

    //TODO implement the positions class, and/or allow multiple employees
    public void addEvent(EmailAddress gmail_address, String position, String details, Date start, Date end)
            throws NotLoggedInException, AuthenticationException,
            ServiceException, IOException {
        CalendarEntry entry = new CalendarEntry();
        entry.setTitle(new PlainTextConstruct(position));
        entry.setContent(new PlainTextConstruct(details));
        entry.setTimeZone(new TimeZoneProperty(TimeZone.getDefault().getDisplayName()));
        entry.setCanEdit(true);

        When when = new When();
        when.setStartTime(new DateTime(start));
        when.setEndTime(new DateTime(end));
        entry.addExtension(when);

        Who who = new Who();
        who.setEmail(gmail_address.getAddress());
        who.setValueString(gmail_address.getPersonal());
        who.setAttendeeStatus(AttendeeStatus.EVENT_ACCEPTED);
        who.setAttendeeType(AttendeeType.EVENT_REQUIRED);
        entry.addExtension(who);

        entry = Calendars.getService().insert(html_link, entry);
    }

    public void delete(int index)
            throws NotLoggedInException, AuthenticationException,
            ServiceException, IOException {
        Calendars.getService().getFeed(html_link, CalendarFeed.class).getEntries().get(index).delete();
    }

    public Iterator<CalendarEntry> getEvents()
            throws NotLoggedInException, AuthenticationException,
            ServiceException, IOException {
        return Calendars.getService().getFeed(html_link, CalendarFeed.class).getEntries().iterator();
    }

    public CalendarEntry getEvent(int index)
            throws NotLoggedInException, AuthenticationException,
            ServiceException, IOException {
        return Calendars.getService().getFeed(html_link, CalendarFeed.class).getEntries().get(index);
    }

    public void acceptAlert(AlertType type, EmailAddress sender)
            throws AlertClassException, NotLoggedInException {
        switch(type){
            case NewWorkSchedule:
            case UpdatedWorkSchedule:
                EmployeeSingleton.getInstance().getCalendars().setWorkSchedule(this);
                break;
            default:
                throw new AlertClassException(this.getClass(), type.getAssociatedClass());
        }
    }

    public void rejectAlert(AlertType type) throws AlertClassException {
        switch(type){
            case NewWorkSchedule:
                //DONOTHING
                break;
            case UpdatedWorkSchedule:
                //DONOTHING
                break;
            default:
                throw new AlertClassException(this.getClass(), type.getAssociatedClass());
        }
    }

    public void ignoreAlert(AlertType type) throws AlertClassException {
        switch(type){
            case NewWorkSchedule:
                //DONOTHING
                break;
            case UpdatedWorkSchedule:
                //DONOTHING
                break;
            default:
                throw new AlertClassException(this.getClass(), type.getAssociatedClass());
        }
    }

    //TODO getDetails()
    public String getDetails() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

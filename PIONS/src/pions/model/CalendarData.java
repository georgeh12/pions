
package pions.model;

import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.Link;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.calendar.CalendarEntry;
import com.google.gdata.data.calendar.TimeZoneProperty;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
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
//TODO implement iterator
public class CalendarData implements Serializable, AbstractAlert {
    private final static String CALENDAR_SERVICE = "PIONS Calendar";
    private final static String OWN_CALENDARS =
            "http://www.google.com/calendar/feeds/default/owncalendars/full";
    private final static String ALL_CALENDARS =
            "http://www.google.com/calendar/feeds/default/allcalendars/full";
    private String calendar_name;
    private ArrayList<CalendarEntry> archive = new ArrayList<CalendarEntry>();
    private EmailAddress gmail_address;
    private String gmail_password;
    private CalendarEntry active_calendar;

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

    private void create() throws AuthenticationException,
            MalformedURLException, ServiceException, IOException, NotLoggedInException {
        gmail_address = EmployeeSingleton.getInstance().getGmail().getGmailAddress();
        gmail_password = EmployeeSingleton.getInstance().getGmail().getPassword();
        
        // Create a CalenderService and authenticate
        CalendarService service = new CalendarService(CALENDAR_SERVICE);
        service.setUserCredentials(gmail_address.getAddress(), gmail_password);

        // Create the calendar
        active_calendar = new CalendarEntry();
        active_calendar.setTitle(new PlainTextConstruct(calendar_name));
        active_calendar.setTimeZone(new TimeZoneProperty(TimeZone.getDefault().getID()));

        service.insert(new URL(OWN_CALENDARS), active_calendar);
    }

    public void update() throws ServiceException, IOException{
        active_calendar = active_calendar.update();
    }

    public Link getLink(){
        return active_calendar.getEditLink();
    }

    public void archiveCalendar(String new_calendar) throws AuthenticationException,
            MalformedURLException, ServiceException, IOException, NotLoggedInException {
        archive.add(active_calendar);
        create();
    }

    public void acceptAlert(AlertType type) throws AlertClassException {
        switch(type){
            case NewWorkSchedule:
                //TODO
                break;
            case UpdatedWorkSchedule:
                //TODO
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

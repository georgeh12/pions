
package pions.model;

import com.google.gdata.client.calendar.CalendarService;
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
import java.util.Observable;
import java.util.TimeZone;
import pions.model.ModelException.NotLoggedInException;

/**
 *
 * @author George
 */
//TODO implement iterator
public class CalendarCollection extends Observable implements Serializable {
    //TODO testing purposes only
    public static void main(String args[]){
        try {
            EmployeeSingleton.init("george", "password");
            EmployeeSingleton.getInstance().getGmail().setGmail("pionstest@gmail.com", "PIONSpassword");
            CalendarCollection mine = new CalendarCollection("test");
            mine.update();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final static String CALENDAR_SERVICE = "PIONS Calendar";
    private final static String OWN_CALENDARS =
            "http://www.google.com/calendar/feeds/default/owncalendars/full";
    private final static String ALL_CALENDARS =
            "http://www.google.com/calendar/feeds/default/allcalendars/full";
    private ArrayList<CalendarEntry> archive = new ArrayList<CalendarEntry>();
    private String gmail_username;
    private String gmail_password;
    private CalendarEntry active_calendar;

    CalendarCollection() { }

    public CalendarCollection(String calendar_name) throws AuthenticationException,
            MalformedURLException, ServiceException, IOException, NotLoggedInException {
        set(calendar_name);
    }

    public void set(String calendar_name) throws AuthenticationException,
            MalformedURLException, ServiceException, IOException, NotLoggedInException {
        gmail_username = EmployeeSingleton.getInstance().getGmail().getUsername();
        gmail_password = EmployeeSingleton.getInstance().getGmail().getPassword();
        
        // Create a CalenderService and authenticate
        CalendarService service = new CalendarService(CALENDAR_SERVICE);
        service.setUserCredentials(gmail_username, gmail_password);

        // Create the calendar
        active_calendar = new CalendarEntry();
        active_calendar.setTitle(new PlainTextConstruct(calendar_name));
        active_calendar.setTimeZone(new TimeZoneProperty(TimeZone.getDefault().getID()));

        service.insert(new URL(OWN_CALENDARS), active_calendar);
    }

    public void update() throws ServiceException, IOException{
        active_calendar = active_calendar.update();
    }

    public CalendarEntry get(){
        return active_calendar;
    }

    public void archiveCalendar(String new_calendar) throws AuthenticationException,
            MalformedURLException, ServiceException, IOException, NotLoggedInException {
        archive.add(active_calendar);
        set(new_calendar);
    }
}

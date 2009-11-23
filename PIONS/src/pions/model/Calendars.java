
package pions.model;

import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import pions.model.ModelException.NotLoggedInException;
import pions.model.ModelException.ScheduleNotFoundException;

/**
 *
 * @author George
 */
public class Calendars implements Serializable {
    private final static String CALENDAR_SERVICE = "PIONS Calendar";
    private final static String AVAILABILITY = "PIONS Availability";
    private final static String WORK_SCHEDULE = "PIONS Work Schedule";
    //private final static String DROP_SHIFT = "PIONS Drop Shift";
    private transient static CalendarService service = null;
    private Calendar availability;
    private Calendar work_schedule;
    private Calendar subordinate_schedule;

    Calendars() { }

    public static CalendarService getService() throws NotLoggedInException, AuthenticationException{
        if(service == null){
            // Create a CalenderService and authenticate
            String gmail_address = EmployeeSingleton.getInstance().getGmail().getGmailAddress().getAddress();
            String gmail_password = EmployeeSingleton.getInstance().getGmail().getPassword();
            service = new CalendarService(CALENDAR_SERVICE);
            service.setUserCredentials(gmail_address, gmail_password);
        }

        return service;
    }

    public Calendar getAvailability()
            throws AuthenticationException, MalformedURLException,
            ServiceException, IOException, NotLoggedInException{
        if(availability == null){
            availability = new Calendar(AVAILABILITY);
        }

        return availability;
    }

    public Calendar getWorkSchedule() throws ScheduleNotFoundException{
        if(work_schedule == null){
            throw new ScheduleNotFoundException(WORK_SCHEDULE);
        }

        return work_schedule;
    }

    public void setWorkSchedule(Calendar work_schedule) {
        this.work_schedule = work_schedule;
    }

    public Calendar getSubordinateSchedule()
            throws AuthenticationException, MalformedURLException,
            ServiceException, IOException, NotLoggedInException{
        if(subordinate_schedule == null){
            subordinate_schedule = new Calendar(WORK_SCHEDULE);
        }

        return subordinate_schedule;
    }
}

package pions.model;

import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import pions.model.ModelException.NotLoggedInException;
import pions.model.ModelException.ScheduleNotFoundException;

/**
 *
 * 
 */
public class Calendars implements Serializable {
    private final static String AVAILABILITY = "PIONS Availability";
    private final static String WORK_SCHEDULE = "PIONS Work Schedule";
    private Calendar availability;
    private Calendar work_schedule;
    private Calendar subordinate_schedule;
    private String gmail_address;
    private String gmail_password;

    Calendars(String gmail_address, String gmail_password) {
        this.gmail_address = gmail_address;
        this.gmail_password = gmail_password;
    }

    public Calendar getAvailability()
            throws AuthenticationException, MalformedURLException,
            ServiceException, IOException, NotLoggedInException{
        if(availability == null){
            availability = new Calendar(AVAILABILITY, gmail_address, gmail_password);
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
            subordinate_schedule = new Calendar(WORK_SCHEDULE, gmail_address, gmail_password);
        }

        return subordinate_schedule;
    }
}

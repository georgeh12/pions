
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
 * @author George
 */
public class Calendars implements Serializable {
    private final static String AVAILABILITY = "PIONS Availability";
    private final static String WORK_SCHEDULE = "PIONS Work Schedule";
    private final static String SUBORDINATE_SCHEDULE = "PIONS Subordinate Schedule";
    private final static String SWAP_SHIFT = "PIONS Swap Shift";
    private CalendarData availability;
    private CalendarData work_schedule;
    private CalendarData subordinate_schedule;

    public CalendarData getAvailability()
            throws AuthenticationException, MalformedURLException,
            ServiceException, IOException, NotLoggedInException{
        if(availability == null){
            availability = new CalendarData(AVAILABILITY);
        }

        return availability;
    }

    public CalendarData getWorkSchedule()
            throws AuthenticationException, MalformedURLException,
            ServiceException, IOException, NotLoggedInException,
            ScheduleNotFoundException{
        if(work_schedule == null){
            throw new ScheduleNotFoundException(WORK_SCHEDULE);
        }

        return work_schedule;
    }

    public CalendarData getSubordinateSchedule()
            throws AuthenticationException, MalformedURLException,
            ServiceException, IOException, NotLoggedInException{
        if(subordinate_schedule == null){
            subordinate_schedule = new CalendarData(SUBORDINATE_SCHEDULE);
        }

        return subordinate_schedule;
    }
}

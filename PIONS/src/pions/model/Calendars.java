
package pions.model;

import java.io.Serializable;

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

    public CalendarData getAvailability(){
        if(availability == null){
            pions.controller.Calendars.initCalendar(AVAILABILITY);
        }

        return availability;
    }

    public CalendarData getWorkSchedule(){
        if(work_schedule == null){
            pions.controller.Calendars.initCalendar(WORK_SCHEDULE);
        }

        return work_schedule;
    }

    public CalendarData getSubordinateSchedule(){
        if(subordinate_schedule == null){
            pions.controller.Calendars.initCalendar(SUBORDINATE_SCHEDULE);
        }

        return subordinate_schedule;
    }
}

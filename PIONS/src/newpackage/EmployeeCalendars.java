
package newpackage;

import java.io.Serializable;

/**
 *
 * @author George
 */
public class EmployeeCalendars implements Serializable {
    private final static String AVAILABILITY = "PIONS Availability";
    private final static String WORK_SCHEDULE = "PIONS Work Schedule";
    private final static String SUBORDINATE_SCHEDULE = "PIONS Subordinate Schedule";
    private CalendarCollection availability;
    private CalendarCollection work_schedule;
    private CalendarCollection subordinate_schedule;

    public CalendarCollection getAvailability(){
        if(availability == null){
            pions.controller.EmployeeCalendars.initCalendar(AVAILABILITY);
        }

        return availability;
    }

    public CalendarCollection getWorkSchedule(){
        if(work_schedule == null){
            pions.controller.EmployeeCalendars.initCalendar(WORK_SCHEDULE);
        }

        return work_schedule;
    }

    public CalendarCollection getSubordinateSchedule(){
        if(subordinate_schedule == null){
            pions.controller.EmployeeCalendars.initCalendar(SUBORDINATE_SCHEDULE);
        }

        return subordinate_schedule;
    }
}

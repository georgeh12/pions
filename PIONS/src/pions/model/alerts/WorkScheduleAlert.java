
package pions.model.alerts;

import java.io.Serializable;
import pions.model.CalendarCollection;

/**
 *
 * @author George
 */
//TODO update to a valid class container. Implement Memento
public class WorkScheduleAlert extends Alert implements Serializable {
    private CalendarCollection work_schedule = null;

    public WorkScheduleAlert(CalendarCollection work_schedule){
        set(work_schedule);
    }

    public void set(CalendarCollection work_schedule) {
        this.work_schedule = work_schedule;
    }

    @Override
    public Object get() {
        return work_schedule;
    }
}

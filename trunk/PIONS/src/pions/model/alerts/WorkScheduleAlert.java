
package pions.model.alerts;

import java.io.Serializable;
import pions.model.WorkSchedule;

/**
 *
 * @author George
 */
//TODO update to a valid class container. Implement Memento
public class WorkScheduleAlert extends Alert implements Serializable {
    private WorkSchedule work_schedule = null;

    public WorkScheduleAlert(WorkSchedule work_schedule){
        set(work_schedule);
    }

    public void set(WorkSchedule work_schedule) {
        this.work_schedule = work_schedule;
    }

    @Override
    public Object get() {
        return work_schedule;
    }
}

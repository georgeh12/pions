
package pions.model.alerts;

import java.io.Serializable;
import pions.model.CalendarCollection;

/**
 *
 * @author George
 */
//TODO update to a valid class container. Implement Memento
public class WorkScheduleAlert extends Alert implements Serializable {
    public WorkScheduleAlert(CalendarCollection work_schedule, AlertType type) {
        super(type);
        set(work_schedule);
    }
}


package pions.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author George
 */
//TODO implementation of Memento pattern
public class WorkSchedule implements Serializable {
    private ArrayList<GoogleCalendar> saved = new ArrayList<GoogleCalendar>();
    private GoogleCalendar new_schedule;

    public WorkSchedule(GoogleCalendar new_schedule){
        this.new_schedule = new_schedule;
    }

    public void newSchedule(GoogleCalendar new_schedule){
        saved.add(this.new_schedule);
        this.new_schedule = new_schedule;
    }

    public void updateSchedule(GoogleCalendar new_schedule){
        this.new_schedule = new_schedule;
    }
}

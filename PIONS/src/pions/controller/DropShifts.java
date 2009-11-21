
package pions.controller;

import com.google.gdata.data.calendar.CalendarEntry;
import pions.model.dropshift.DropShift;

/**
 *
 * @author George
 */
public class DropShifts {
    
    private static DropShift drop_shift = null;
    private static CalendarEntry shift = null;

    public static boolean isInit(){
        return drop_shift == null;
    }

    public static void setCurrentEntry(int index){
        shift = Calendars.getEvent(index);
    }

    public static void createDropShift() {
        drop_shift = new DropShift(shift);
    }

    public static void deleteDropShift() {
        drop_shift = null;
    }
}

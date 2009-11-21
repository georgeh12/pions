
package pions.controller;

import com.google.gdata.data.calendar.CalendarEntry;
import pions.model.swapshift.SwapShift;

/**
 *
 * @author George
 */
public class SwapShifts {
    
    private static SwapShift swap_shift = null;

    public static boolean isInit(){
        return swap_shift == null;
    }

    public static void createSwapShift(int index) {
        swap_shift = new SwapShift(Calendars.getEvent(index), new CalendarEntry());
    }
}

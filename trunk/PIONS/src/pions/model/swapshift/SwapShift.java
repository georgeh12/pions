
package pions.model.swapshift;

import java.io.Serializable;
import pions.model.GoogleCalendar;

/**
 *
 * @author George
 */
//TODO design SwapShift
public class SwapShift implements Serializable {
    private SwapShiftMachine machine = new SwapShiftMachine();
    private GoogleCalendar current;
    private GoogleCalendar proposed;

    public SwapShift(GoogleCalendar current){
        this.current = current;
    }

    public void setProposed(GoogleCalendar proposed){
        this.proposed = proposed;
    }
}

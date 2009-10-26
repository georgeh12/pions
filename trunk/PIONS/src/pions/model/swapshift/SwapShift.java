
package pions.model.swapshift;

import java.io.Serializable;
import pions.model.CalendarCollection;

/**
 *
 * @author George
 */
//TODO design SwapShift
public class SwapShift implements Serializable {
    private SwapShiftMachine machine = new SwapShiftMachine();
    private CalendarCollection current;
    private CalendarCollection proposed;

    public SwapShift(CalendarCollection current){
        this.current = current;
    }

    public void setProposed(CalendarCollection proposed){
        this.proposed = proposed;
    }
}

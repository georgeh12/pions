
package pions.model.alerts;

import java.io.Serializable;
import pions.model.swapshift.SwapShift;

/**
 *
 * @author George
 */
public class SwapShiftAlert extends Alert implements Serializable {
    private SwapShift swap_shift = null;

    public SwapShiftAlert(SwapShift swap_shift){
        set(swap_shift);
    }

    public void set(SwapShift swap_shift) {
        this.swap_shift = swap_shift;
    }

    @Override
    public Object get() {
        return swap_shift;
    }
}

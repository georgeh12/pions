
package pions.model.alerts;

import java.io.Serializable;
import pions.model.swapshift.SwapShift;

/**
 *
 * @author George
 */
public class SwapShiftAlert extends Alert implements Serializable {
    public SwapShiftAlert(SwapShift swap_shift, AlertType type) {
        super(type);
        set(swap_shift);
    }
}

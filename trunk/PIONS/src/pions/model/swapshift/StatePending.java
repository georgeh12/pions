
package pions.model.swapshift;

import java.io.Serializable;

/**
 *
 * @author George
 */
class StatePending extends AbstractState implements Serializable {
    protected StatePending(SwapShift swap_shift){
        super(swap_shift);
    }

    @Override
    protected void accepted() {
        swap_shift.setAccepted(swap_shift.final_state);
    }

    @Override
    protected void rejected() {
        swap_shift.setRejected(swap_shift.final_state);
    }

    @Override
    protected void ignored() {
        swap_shift.setIgnored(swap_shift.final_state);
    }
}

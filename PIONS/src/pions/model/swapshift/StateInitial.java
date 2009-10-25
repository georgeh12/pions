
package pions.model.swapshift;

import java.io.Serializable;

/**
 *
 * @author George
 */
class StateInitial extends AbstractState implements Serializable {
    protected StateInitial(SwapShift swap_shift){
        super(swap_shift);
    }

    @Override
    protected void accepted() {
        swap_shift.setAccepted(swap_shift.authorizing_state);
    }

    @Override
    protected void rejected() {
        swap_shift.setRejected(swap_shift.authorizing_state);
    }

    @Override
    protected void ignored() {
        swap_shift.setIgnored(swap_shift.authorizing_state);
    }
}

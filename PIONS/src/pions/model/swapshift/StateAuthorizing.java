
package pions.model.swapshift;

import java.io.Serializable;

/**
 *
 * @author George
 */
class StateAuthorizing extends AbstractState implements Serializable {
    protected StateAuthorizing(SwapShift swap_shift){
        super(swap_shift);
    }

    @Override
    protected void accepted() {
        swap_shift.setAccepted(swap_shift.pending_state);
    }

    @Override
    protected void rejected() {
        swap_shift.setRejected(swap_shift.pending_state);
    }

    @Override
    protected void ignored() {
        swap_shift.setIgnored(swap_shift.pending_state);
    }
}

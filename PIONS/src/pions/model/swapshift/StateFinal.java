
package pions.model.swapshift;

import java.io.Serializable;

/**
 *
 * @author George
 */
class StateFinal extends AbstractState implements Serializable {
    protected StateFinal(SwapShiftMachine swap_shift){
        super(swap_shift);
    }

    @Override
    protected void accepted() {
        throw new UnsupportedOperationException("Final state reached.");
    }

    @Override
    protected void rejected() {
        throw new UnsupportedOperationException("Final state reached.");
    }

    @Override
    protected void ignored() {
        throw new UnsupportedOperationException("Final state reached.");
    }
}

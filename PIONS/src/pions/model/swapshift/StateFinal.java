/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pions.model.swapshift;

/**
 *
 * @author George
 */
class StateFinal extends StateAbstract {
    protected StateFinal(SwapShift swap_shift){
        super(swap_shift);
    }

    @Override
    void accepted() {
        throw new UnsupportedOperationException("Final state reached.");
    }

    @Override
    void rejected() {
        throw new UnsupportedOperationException("Final state reached.");
    }

    @Override
    void ignored() {
        throw new UnsupportedOperationException("Final state reached.");
    }
}

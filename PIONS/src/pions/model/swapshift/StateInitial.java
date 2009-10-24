/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pions.model.swapshift;

/**
 *
 * @author George
 */
class StateInitial extends StateAbstract {
    protected StateInitial(SwapShift swap_shift){
        super(swap_shift);
    }

    @Override
    void accepted() {
        swap_shift.setAccepted(swap_shift.authorizing_state);
    }

    @Override
    void rejected() {
        swap_shift.setRejected(swap_shift.authorizing_state);
    }

    @Override
    void ignored() {
        swap_shift.setIgnored(swap_shift.authorizing_state);
    }
}

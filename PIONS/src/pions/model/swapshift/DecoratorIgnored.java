/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pions.model.swapshift;

/**
 *
 * @author George
 */
class DecoratorIgnored extends DecoratorAbstract {
    protected DecoratorIgnored(StateAbstract swap_shift_state){
        super(swap_shift_state);
    }

    @Override
    void accepted() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    void rejected() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    void ignored() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

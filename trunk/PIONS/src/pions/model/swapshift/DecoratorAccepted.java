
package pions.model.swapshift;

import java.io.Serializable;

/**
 *
 * @author George
 */
class DecoratorAccepted extends AbstractDecorator implements Serializable {
    protected DecoratorAccepted(AbstractState swap_shift_state){
        super(swap_shift_state);
    }

    @Override
    protected void accepted() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void rejected() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void ignored() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}


package pions.model.dropshift;

import java.io.Serializable;

/**
 * Contains a new constructor, and overrides some methods.
 * @author George
 */
abstract class AbstractDecorator extends AbstractState implements Serializable {
    protected AbstractState drop_shift_state;

    protected AbstractDecorator(AbstractState drop_shift_state){
        super(drop_shift_state.drop_shift);
        this.drop_shift_state = drop_shift_state;
    }

    /**
     * Gets the decorated object.
     * @return
     */
    @Override
    protected AbstractState get(){
        return drop_shift_state;
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

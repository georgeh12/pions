
package pions.model.dropshift;

import java.io.Serializable;

/**
 * Contains a new constructor, and overrides some methods.
 * 
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
        drop_shift_state.accepted();
    }

    @Override
    protected void rejected() {
        drop_shift_state.rejected();
    }

    @Override
    protected void ignored() {
        drop_shift_state.ignored();
    }
}

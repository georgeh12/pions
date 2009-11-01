
package pions.model.swapshift;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Contains a new constructor, and overrides some methods.
 * @author George
 */
abstract class AbstractDecorator extends AbstractState implements Serializable {
    protected AbstractState swap_shift_state;

    protected AbstractDecorator(AbstractState swap_shift_state){
        super(swap_shift_state.swap_shift);
        this.swap_shift_state = swap_shift_state;
    }

    /**
     * Gets the decorated object.
     * @return
     */
    @Override
    protected AbstractState get(){
        return swap_shift_state;
    }
}

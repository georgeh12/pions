
package pions.model.swapshift;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Contains useful functions and unimplemented functions.
 * @author George
 */
abstract class AbstractState implements Serializable {
    protected SwapShift swap_shift;

    protected AbstractState(SwapShift swap_shift){
        this.swap_shift = swap_shift;
    }
    
    protected ArrayList<AbstractState> getAttributes(){
        ArrayList<AbstractState> list = new ArrayList<AbstractState>();
        list.add(this);
        return list;
    }
    
    protected AbstractState get() {
        throw new UnsupportedOperationException("Not supported by this subclass.");
    }

    protected abstract void accepted();

    protected abstract void rejected();

    protected abstract void ignored();
}

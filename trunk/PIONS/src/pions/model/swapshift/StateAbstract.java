/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pions.model.swapshift;

import java.util.ArrayList;

/**
 * Contains useful functions and unimplemented functions.
 * @author George
 */
abstract class StateAbstract {
    protected SwapShift swap_shift;

    StateAbstract(SwapShift swap_shift){
        this.swap_shift = swap_shift;
    }
    
    ArrayList<StateAbstract> getAttributes(){
        ArrayList<StateAbstract> list = new ArrayList<StateAbstract>();
        list.add(this);
        return list;
    }
    
    protected StateAbstract get() {
        throw new UnsupportedOperationException("Not supported by this subclass.");
    }

    abstract void accepted();

    abstract void rejected();

    abstract void ignored();
}

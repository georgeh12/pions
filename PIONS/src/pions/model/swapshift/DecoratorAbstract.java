/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pions.model.swapshift;

import java.util.ArrayList;

/**
 * Contains a new constructor, and overrides some methods.
 * @author George
 */
abstract class DecoratorAbstract extends StateAbstract {
    protected StateAbstract swap_shift_state;

    DecoratorAbstract(StateAbstract swap_shift_state){
        super(swap_shift_state.swap_shift);
        this.swap_shift_state = swap_shift_state;
    }

    @Override
    ArrayList<StateAbstract> getAttributes(){
        ArrayList<StateAbstract> list = super.getAttributes();
        list.add(this);
        return list;
    }

    /**
     * Gets the decorated object.
     * @return
     */
    @Override
    protected StateAbstract get(){
        return swap_shift_state;
    }
}

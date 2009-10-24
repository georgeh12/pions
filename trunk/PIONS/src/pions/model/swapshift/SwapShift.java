/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pions.model.swapshift;

/**
 * The machine to handle swap shift states.
 * Implements state and decorator patterns. BOOYA!
 * @author George
 */
public class SwapShift {
    StateAbstract initial_state = new StateInitial(this);
    StateAbstract authorizing_state = new StateAuthorizing(this);
    StateAbstract pending_state = new StatePending(this);
    StateAbstract final_state = new StateFinal(this);
    StateAbstract current_state = initial_state;

    protected void setState(StateAbstract state){
        current_state = state;
    }

    protected void setAccepted(StateAbstract state){
        current_state = new DecoratorAccepted(state);
    }

    protected void setRejected(StateAbstract state){
        current_state = new DecoratorRejected(state);
    }

    protected void setIgnored(StateAbstract state){
        current_state = new DecoratorIgnored(state);
    }

    public void accepted(){
        current_state.accepted();
    }

    public void rejected(){
        current_state.rejected();
    }

    public void ignored(){
        current_state.ignored();
    }

    @Override
    public String toString(){
        return current_state.toString();
    }
}

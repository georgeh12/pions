
package pions.model.swapshift;

import java.io.Serializable;

/**
 * The machine to handle swap shift states.
 * Implements state and decorator patterns. BOOYA!
 * @author George
 */
public class SwapShift implements Serializable {


    AbstractState initial_state = new StateInitial(this);
    AbstractState authorizing_state = new StateAuthorizing(this);
    AbstractState pending_state = new StatePending(this);
    AbstractState final_state = new StateFinal(this);
    AbstractState current_state = initial_state;

    void setState(AbstractState state){
        current_state = state;
    }

    void setAccepted(AbstractState state){
        current_state = new DecoratorAccepted(state);
    }

    void setRejected(AbstractState state){
        current_state = new DecoratorRejected(state);
    }

    void setIgnored(AbstractState state){
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

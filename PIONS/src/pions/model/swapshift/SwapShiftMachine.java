
package pions.model.swapshift;

import java.io.Serializable;
import java.util.ArrayList;
import pions.model.ContactInfo.EmailAddress;
import pions.model.ModelException.NotLoggedInException;

/**
 * The machine to handle swap shift states.
 * Implements state and decorator patterns. BOOYA!
 * @author George
 */
public class SwapShiftMachine implements Serializable {
    AbstractState initial_state = new StateInitial(this);
    AbstractState authorizing_state = new StateAuthorizing(this);
    AbstractState pending_state = new StatePending(this);
    AbstractState final_state = new StateFinal(this);
    AbstractState current_state = initial_state;

    boolean isAccepted() {
        return current_state.isAccepted();
    }

    boolean isRejected() {
        return current_state.isRejected();
    }

    boolean isIgnored() {
        return current_state.isIgnored();
    }

    ArrayList<EmailAddress> getRecipients() throws NotLoggedInException{
        return current_state.getRecipients();
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

    void accept(){
        current_state.accepted();
    }

    void reject(){
        current_state.rejected();
    }

    void ignore(){
        current_state.ignored();
    }

    @Override
    public String toString(){
        return current_state.toString();
    }
}

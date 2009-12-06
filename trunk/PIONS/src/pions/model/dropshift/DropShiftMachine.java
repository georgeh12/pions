
package pions.model.dropshift;

import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import pions.model.ContactInfo.EmailAddress;
import pions.model.ModelException.NotLoggedInException;

/**
 * The machine to handle drop shift states.
 * Implements state and decorator patterns. BOOYA!
 * 
 */
public class DropShiftMachine implements Serializable {
    AbstractState initial_state = new StateInitial(this);
    AbstractState authorizing_state = new StateAuthorizing(this);
    AbstractState pending_state = new StatePending(this);
    AbstractState publish_state = new StatePublish(this);
    AbstractState final_state = new StateFinal(this);
    AbstractState current_state = initial_state;

    ArrayList<EmailAddress> getRecipients() throws NotLoggedInException {
        return current_state.getRecipients();
    }

    boolean isAccepted() {
        return current_state.isAccepted();
    }

    boolean isRejected() {
        return current_state.isRejected();
    }

    boolean isIgnored() {
        return current_state.isIgnored();
    }

    void setAccepted(AbstractState state) {
        current_state = new DecoratorAccepted(state);
    }

    void setRejected(AbstractState state){
        current_state = new DecoratorRejected(state);
    }

    void setIgnored(AbstractState state){
        current_state = new DecoratorIgnored(state);
    }

    void accept() throws NotLoggedInException,
            AuthenticationException, ServiceException, IOException{
        current_state.accepted();
    }

    void reject() {
        current_state.rejected();
    }

    void ignore() {
        current_state.ignored();
    }

    @Override
    public String toString(){
        return current_state.toString();
    }
}

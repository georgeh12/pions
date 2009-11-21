
package pions.model.dropshift;

import java.io.Serializable;
import java.util.ArrayList;
import pions.model.ContactInfo.EmailAddress;
import pions.model.ModelException.NotLoggedInException;

/**
 * Contains useful functions and unimplemented functions.
 * @author George
 */
abstract class AbstractState implements Serializable {
    protected DropShiftMachine drop_shift;

    protected AbstractState(DropShiftMachine drop_shift){
        this.drop_shift = drop_shift;
    }
    
    protected AbstractState get() {
        throw new UnsupportedOperationException("Not supported by this subclass.");
    }

    /**
     * This function implements the Chain of Responsibility Design pattern.
     * A null indicates that the recipient is the originating Employee.
     * @return
     * @throws pions.model.ModelException.NotLoggedInException
     */
    protected abstract ArrayList<EmailAddress> getRecipients() throws NotLoggedInException;

    protected abstract boolean isAccepted();

    protected abstract boolean isRejected();

    protected abstract boolean isIgnored();

    protected abstract void accepted();

    protected abstract void rejected();

    protected abstract void ignored();
}

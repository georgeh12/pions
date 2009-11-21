
package pions.model.swapshift;

import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import pions.model.ContactInfo.EmailAddress;
import pions.model.ModelException.NotLoggedInException;
import pions.model.ModelException.ScheduleNotFoundException;

/**
 * Contains useful functions and unimplemented functions.
 * @author George
 */
abstract class AbstractState implements Serializable {
    protected SwapShiftMachine swap_shift;

    protected AbstractState(SwapShiftMachine swap_shift){
        this.swap_shift = swap_shift;
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

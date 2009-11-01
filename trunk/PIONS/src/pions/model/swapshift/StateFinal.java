
package pions.model.swapshift;

import java.io.Serializable;
import java.util.ArrayList;
import pions.model.ContactInfo.EmailAddress;
import pions.model.ModelException.NotLoggedInException;

/**
 *
 * @author George
 */
class StateFinal extends AbstractState implements Serializable {
    protected StateFinal(SwapShiftMachine swap_shift){
        super(swap_shift);
    }

    @Override
    protected void accepted() {
        throw new UnsupportedOperationException("Final state reached.");
    }

    @Override
    protected void rejected() {
        throw new UnsupportedOperationException("Final state reached.");
    }

    @Override
    protected void ignored() {
        throw new UnsupportedOperationException("Final state reached.");
    }

    @Override
    protected ArrayList<EmailAddress> getRecipients() throws NotLoggedInException {
        return null;
    }

    @Override
    protected boolean isAccepted() {
        throw new UnsupportedOperationException("Handled by decorator classes.");
    }

    @Override
    protected boolean isRejected() {
        throw new UnsupportedOperationException("Handled by decorator classes.");
    }

    @Override
    protected boolean isIgnored() {
        throw new UnsupportedOperationException("Handled by decorator classes.");
    }
}

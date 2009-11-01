
package pions.model.swapshift;

import java.io.Serializable;
import java.util.ArrayList;
import pions.model.ContactInfo.EmailAddress;
import pions.model.EmployeeSingleton;
import pions.model.ModelException.NotLoggedInException;

/**
 *
 * @author George
 */
class StateAuthorizing extends AbstractState implements Serializable {
    protected StateAuthorizing(SwapShiftMachine swap_shift){
        super(swap_shift);
    }

    @Override
    protected void accepted() {
        swap_shift.setAccepted(swap_shift.pending_state);
    }

    @Override
    protected void rejected() {
        swap_shift.setRejected(swap_shift.pending_state);
    }

    @Override
    protected void ignored() {
        swap_shift.setIgnored(swap_shift.pending_state);
    }

    @Override
    protected ArrayList<EmailAddress> getRecipients() throws NotLoggedInException {
        return EmployeeSingleton.getInstance().getSubordinateGmails();
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

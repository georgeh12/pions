
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
class StatePending extends AbstractState implements Serializable {
    protected StatePending(SwapShiftMachine swap_shift){
        super(swap_shift);
    }

    @Override
    protected void accepted() {
        swap_shift.setAccepted(swap_shift.publish_state);
    }

    @Override
    protected void rejected() {
        swap_shift.setRejected(swap_shift.publish_state);
    }

    @Override
    protected void ignored() {
        swap_shift.setIgnored(swap_shift.publish_state);
    }

    @Override
    protected ArrayList<EmailAddress> getRecipients() throws NotLoggedInException {
        return EmployeeSingleton.getInstance().getManagerGmails();
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


package pions.model.dropshift;

import java.io.Serializable;
import java.util.ArrayList;
import pions.model.ContactInfo.EmailAddress;
import pions.model.EmployeeSingleton;
import pions.model.ModelException.NotLoggedInException;

/**
 *
 * 
 */
class StateAuthorizing extends AbstractState implements Serializable {
    protected StateAuthorizing(DropShiftMachine drop_shift){
        super(drop_shift);
    }

    @Override
    protected void accepted() {
        drop_shift.setAccepted(drop_shift.pending_state);
    }

    @Override
    protected void rejected() {
        drop_shift.setRejected(drop_shift.pending_state);
    }

    @Override
    protected void ignored() {
        drop_shift.setIgnored(drop_shift.pending_state);
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

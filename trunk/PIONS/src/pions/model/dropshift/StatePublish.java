
package pions.model.dropshift;

import java.io.Serializable;
import java.util.ArrayList;
import pions.model.ContactInfo.EmailAddress;

/**
 *
 * 
 */
class StatePublish extends AbstractState implements Serializable {
    protected StatePublish(DropShiftMachine drop_shift){
        super(drop_shift);
    }

    @Override
    protected void accepted() {
        drop_shift.setAccepted(drop_shift.final_state);
    }

    @Override
    protected void rejected() {
        drop_shift.setRejected(drop_shift.final_state);
    }

    @Override
    protected void ignored() {
        drop_shift.setIgnored(drop_shift.final_state);
    }

    @Override
    protected ArrayList<EmailAddress> getRecipients() {
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

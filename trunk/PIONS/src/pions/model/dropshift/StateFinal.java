
package pions.model.dropshift;

import java.io.Serializable;
import java.util.ArrayList;
import pions.model.ContactInfo.EmailAddress;

/**
 *
 * @author George
 */
class StateFinal extends AbstractState implements Serializable {
    protected StateFinal(DropShiftMachine drop_shift){
        super(drop_shift);
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
    protected ArrayList<EmailAddress> getRecipients() {
        return new ArrayList<EmailAddress>();
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

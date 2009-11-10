
package pions.model.swapshift;

import java.io.Serializable;
import java.util.ArrayList;
import pions.model.ContactInfo.EmailAddress;
import pions.model.ModelException.NotLoggedInException;

/**
 *
 * @author George
 */
class DecoratorIgnored extends AbstractDecorator implements Serializable {
    protected DecoratorIgnored(AbstractState swap_shift_state){
        super(swap_shift_state);
    }

    @Override
    protected ArrayList<EmailAddress> getRecipients() throws NotLoggedInException {
        return new ArrayList<EmailAddress>();
    }

    @Override
    protected boolean isAccepted() {
        return false;
    }

    @Override
    protected boolean isRejected() {
        return false;
    }

    @Override
    protected boolean isIgnored() {
        return true;
    }
}

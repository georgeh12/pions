
package pions.model.dropshift;

import java.io.Serializable;
import java.util.ArrayList;
import pions.model.ContactInfo.EmailAddress;

/**
 *
 * 
 */
class DecoratorIgnored extends AbstractDecorator implements Serializable {
    protected DecoratorIgnored(AbstractState drop_shift_state){
        super(drop_shift_state);
    }

    @Override
    protected ArrayList<EmailAddress> getRecipients() {
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

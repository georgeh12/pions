
package pions.model.swapshift;

import java.io.Serializable;
import java.util.ArrayList;
import pions.model.CalendarCollection;
import pions.model.ContactInfo.EmailAddress;
import pions.model.Employee;
import pions.model.ModelException.NotLoggedInException;

/**
 *
 * @author George
 */
//TODO design SwapShift
public final class SwapShift implements Serializable {
    private SwapShiftMachine machine = new SwapShiftMachine();
    private Employee creator;
    private CalendarCollection current;
    private CalendarCollection proposed;

    public SwapShift(Employee creator, CalendarCollection current){
        this.creator = creator;
        this.current = current;
    }

    public boolean isAccepted() {
        return machine.isAccepted();
    }

    public boolean isRejected() {
        return machine.isRejected();
    }

    public boolean isIgnored() {
        return machine.isIgnored();
    }

    /**
     * Sets the decorator to accepted and advances the state.
     */
    public void accept() {
        machine.accept();
    }

    /**
     * Sets the decorator to rejected and advances the state.
     */
    public void reject() {
        machine.reject();
    }

    /**
     * Sets the decorator to ignored and advances the state.
     */
    public void ignore() {
        machine.ignore();
    }

    public String getCreatorName(){
        return creator.getName();
    }

    public EmailAddress getCreatorGmail(){
        return creator.getGmail().getGmailAddress();
    }

    public ArrayList<EmailAddress> getRecipients() throws NotLoggedInException{
        ArrayList<EmailAddress> recipients = machine.getRecipients();
        if(recipients == null) {
            recipients = new ArrayList<EmailAddress>();
            recipients.add(creator.getGmail().getGmailAddress());
        }

        return recipients;
    }

    public CalendarCollection getProposed(){
        return proposed;
    }

    public void setProposed(CalendarCollection proposed){
        this.proposed = proposed;
    }
}

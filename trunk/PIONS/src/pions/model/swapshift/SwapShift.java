
package pions.model.swapshift;

import java.io.Serializable;
import java.util.ArrayList;
import pions.model.AbstractAlert;
import pions.model.Alert;
import pions.model.CalendarData;
import pions.model.ContactInfo.EmailAddress;
import pions.model.Employee;
import pions.model.ModelException.AlertClassException;
import pions.model.ModelException.NotLoggedInException;

/**
 *
 * @author George
 */
public final class SwapShift implements Serializable, AbstractAlert {
    private SwapShiftMachine machine = new SwapShiftMachine();
    private Employee creator;
    private CalendarData current;
    private CalendarData proposed;

    public SwapShift(Employee creator, CalendarData current){
        this.creator = creator;
        this.current = current;
    }

    public ArrayList<EmailAddress> getRecipients() throws NotLoggedInException{
        ArrayList<EmailAddress> recipients = machine.getRecipients();
        if(recipients == null) {
            recipients = new ArrayList<EmailAddress>();
            recipients.add(creator.getGmail().getGmailAddress());
        }

        return recipients;
    }

    public CalendarData getCurrent(){
        return current;
    }

    public CalendarData getProposed(){
        return proposed;
    }

    public void setProposed(CalendarData proposed){
        this.proposed = proposed;
    }

    /**
     * Sets the decorator to accepted and advances the state.
     */
    public void acceptAlert(Alert.AlertType type) throws AlertClassException {
        switch(type){
            case SwapShift:
                machine.accept();
                break;
            default:
                throw new AlertClassException(this.getClass(), type.getAssociatedClass());
        }
    }

    /**
     * Sets the decorator to rejected and advances the state.
     */
    public void rejectAlert(Alert.AlertType type) throws AlertClassException {
        switch(type){
            case SwapShift:
                machine.reject();
                break;
            default:
                throw new AlertClassException(this.getClass(), type.getAssociatedClass());
        }
    }

    /**
     * Sets the decorator to ignored and advances the state.
     */
    public void ignoreAlert(Alert.AlertType type) throws AlertClassException {
        switch(type){
            case SwapShift:
                machine.ignore();
                break;
            default:
                throw new AlertClassException(this.getClass(), type.getAssociatedClass());
        }
    }

    //TODO getDetails()
    public String getDetails() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

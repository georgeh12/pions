
package pions.model.swapshift;

import com.google.gdata.data.calendar.CalendarEntry;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;
import java.io.IOException;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.internet.AddressException;
import pions.model.AbstractAlert;
import pions.model.Alert;
import pions.model.Alert.AlertType;
import pions.model.ContactInfo.EmailAddress;
import pions.model.EmployeeSingleton;
import pions.model.ModelException.AlertClassException;
import pions.model.ModelException.NotLoggedInException;
import pions.model.ModelException.ScheduleNotFoundException;

/**
 *
 * @author George
 */
public final class SwapShift implements Serializable, AbstractAlert {
    private SwapShiftMachine machine;
    private CalendarEntry proposed;
    private CalendarEntry current;

    public SwapShift(CalendarEntry current, CalendarEntry proposed){
        this.current = current;
        this.proposed = proposed;
    }

    public CalendarEntry getCurrent(){
        return current;
    }

    public CalendarEntry getProposed(){
        return proposed;
    }

    public void setProposed(CalendarEntry proposed){
        this.proposed = proposed;
    }

    /**
     * Sets the decorator to accepted and advances the state.
     */
    public void acceptAlert(Alert.AlertType type) throws NotLoggedInException,
            ScheduleNotFoundException, AlertClassException, ServiceException,
            AuthenticationException, IOException, UnsupportedEncodingException,
            StreamCorruptedException, AddressException, NoSuchProviderException,
            MessagingException, ClassNotFoundException {
        switch(type){
            case SwapShift:
                // Before accepting, get the recipients
                ArrayList<EmailAddress> recipients = machine.getRecipients();

                // Set the machine to accepted, advancing the state
                machine.accept();

                // If there are no recipients, we must publish the proposed CalendarEntry
                if(recipients == null){
                    EmployeeSingleton.getInstance().getCalendars().getWorkSchedule().delete(current);
                    EmployeeSingleton.getInstance().getCalendars().getWorkSchedule().insert(proposed);
                }
                else {
                    for(EmailAddress email_address: recipients){
                        EmployeeSingleton.getInstance().getGmail().sendAlert(email_address, new Alert(this, AlertType.SwapShift));
                    }
                }
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

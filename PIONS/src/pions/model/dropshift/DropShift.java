
package pions.model.dropshift;

import com.google.gdata.data.extensions.EventEntry;
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
import pions.model.Calendar;
import pions.model.ContactInfo.EmailAddress;
import pions.model.EmployeeSingleton;
import pions.model.ModelException.AlertClassException;
import pions.model.ModelException.NotLoggedInException;
import pions.model.ModelException.ScheduleNotFoundException;

/**
 *
 * 
 */
public final class DropShift implements Serializable, AbstractAlert {
    private DropShiftMachine machine;
    private EventEntry shift;

    public DropShift(EventEntry shift){
        this.shift = shift;
    }

    /**
     * Sets the decorator to accepted and advances the state.
     */
    public void acceptAlert(Alert.AlertType type, EmailAddress sender)
            throws NotLoggedInException, ScheduleNotFoundException,
            AlertClassException, ServiceException, AuthenticationException,
            IOException, UnsupportedEncodingException, StreamCorruptedException,
            AddressException, NoSuchProviderException, MessagingException,
            ClassNotFoundException {
        switch(type){
            case DropShift:
                // Before accepting, get the recipients
                ArrayList<EmailAddress> recipients = machine.getRecipients();

                // Set the machine to accepted, advancing the state
                machine.accept();

                // If there are no recipients, we must publish the proposed CalendarEntry
                if(recipients == null){
                    EmployeeSingleton.getInstance().getCalendars().getWorkSchedule().drop(shift);
                }
                else {
                    for(EmailAddress email_address: recipients){
                        EmployeeSingleton.getInstance().getGmail()
                                .sendAlert(email_address, new Alert(this, AlertType.DropShift));
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
            case DropShift:
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
            case DropShift:
                machine.ignore();
                break;
            default:
                throw new AlertClassException(this.getClass(), type.getAssociatedClass());
        }
    }

    public String getDetails() {
        StringBuffer buffer = new StringBuffer();

        buffer.append("Name: " + Calendar.parseTitle(shift));
        buffer.append('\n');

        buffer.append("Text: " + Calendar.parseText(shift));
        buffer.append('\n');

        buffer.append("Start Time: " + Calendar.parseStartTime(shift));
        buffer.append('\n');

        buffer.append("  End Time: " + Calendar.parseEndTime(shift));
        buffer.append('\n');
        
        return buffer.toString();
    }
}

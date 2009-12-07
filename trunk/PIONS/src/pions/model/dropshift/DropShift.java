
package pions.model.dropshift;

import com.google.gdata.data.extensions.EventEntry;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;
import java.io.IOException;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.internet.AddressException;
import pions.model.AbstractAlert;
import pions.model.Alert;
import pions.model.Alert.AlertType;
import pions.model.Calendar;
import pions.model.ContactInfo.EmailAddress;
import pions.model.Contacts.Contact;
import pions.model.EmployeeSingleton;
import pions.model.ModelException.AlertClassException;
import pions.model.ModelException.NotLoggedInException;

/**
 * This class takes some shortcuts because EventEntry can't be serialized.
 * 
 */
public final class DropShift implements Serializable, AbstractAlert {
    private DropShiftMachine machine = new DropShiftMachine();
    private String shift;
    private String details;
    private Contact owner;

    public DropShift(EventEntry shift) throws NotLoggedInException,
            NoSuchAlgorithmException, IOException {
        details = parseDetails(shift);
        this.shift = Calendar.parseHref(shift);
        owner = EmployeeSingleton.getInstance().getContact();
    }

    /**
     * Sets the decorator to accepted and advances the state.
     */
    public void acceptAlert(Alert.AlertType type)
            throws NotLoggedInException,
            AlertClassException, ServiceException, AuthenticationException,
            IOException, UnsupportedEncodingException, StreamCorruptedException,
            AddressException, NoSuchProviderException, MessagingException,
            ClassNotFoundException, NoSuchAlgorithmException {
        switch(type){
            case DropShift:
                // Before accepting, get the recipients
                ArrayList<EmailAddress> recipients = machine.getRecipients();

                // Set the machine to accepted, advancing the state
                machine.accept();

                if(recipients == null){
                    // If there are no recipients and the owner of the event
                    // is EmployeeSingleton, we must publish the proposed CalendarEntry
                    if(owner.getAddress().equals(
                            EmployeeSingleton.getInstance().getContact().getAddress())){
                        EmployeeSingleton.getInstance().getCalendars().getWorkSchedule().drop(shift);
                    }
                    //If not, we must send the owner acknowledgment that
                    //this EmployeeSingleton has accepted the dropshift.
                    else{
                        EmployeeSingleton.getInstance().getContacts().addContact(owner);

                        EmployeeSingleton.getInstance().getGmail()
                                .sendAlert(owner.getAddress(), new Alert(this, AlertType.DropShift));
                    }
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

    private final static String parseDetails(EventEntry entry){
        StringBuffer details = new StringBuffer();

        details.append("Name: " + Calendar.parseTitle(entry));
        details.append('\n');

        details.append("Text: " + Calendar.parseText(entry));
        details.append('\n');

        details.append("Start Time: " + Calendar.parseStartTime(entry));
        details.append('\n');

        details.append("  End Time: " + Calendar.parseEndTime(entry));
        details.append('\n');

        return details.toString();
    }

    public String getDetails() {
        
        return details;
    }
}

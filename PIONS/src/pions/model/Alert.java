
package pions.model;

import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.internet.AddressException;
import pions.model.ContactInfo.EmailAddress;
import pions.model.Contacts.Contact;
import pions.model.ModelException.AlertClassException;
import pions.model.ModelException.NotLoggedInException;
import pions.model.ModelException.ScheduleNotFoundException;
import pions.model.dropshift.DropShift;

/**
 * This class acts as a Mediator between the model.Gmail class and all the
 * Objects which can be stored in an Alert.
 * @author George
 */
public final class Alert implements Serializable {
    private EmailAddress sender = null;
    private AlertType type = null;
    private AbstractAlert object = null;

    public Alert(EmailAddress sender, AbstractAlert object, AlertType type)
            throws AlertClassException{
        if(type.getAssociatedClass() != object.getClass()){
            throw new AlertClassException(type.getAssociatedClass(), object.getClass());
        }

        this.sender = sender;
        this.object = object;
        this.type = type;
    }

    public Alert(AbstractAlert object, AlertType type) throws AlertClassException, NotLoggedInException{
        this(EmployeeSingleton.getInstance().getGmail().getGmailAddress(),
                object, type);
    }

    public void accept()
            throws NotLoggedInException, ScheduleNotFoundException,
            AlertClassException, ServiceException, AuthenticationException,
            IOException, UnsupportedEncodingException, StreamCorruptedException,
            AddressException, NoSuchProviderException, MessagingException,
            ClassNotFoundException, NoSuchAlgorithmException {
        object.acceptAlert(type, sender);
    }

    public void reject() throws AlertClassException{
        object.rejectAlert(type);
    }

    public void ignore() throws AlertClassException{
        object.ignoreAlert(type);
    }

    public AbstractAlert get(){
        return object;
    }

    public static Object decryptAlert(String email_address, AlertType type, InputStream is)
            throws NotLoggedInException, StreamCorruptedException,
            ClassNotFoundException, IOException{
        if(type == AlertType.ContactRequest){
            return Login.getObject(is);
        }
        else{
            return EmployeeSingleton.getInstance().decryptRSA(
                    EmployeeSingleton.getInstance().getPublicKey(email_address),
                    is);
        }
    }

    /**
     * Subclasses should implement their own method for serialization,
     * if necessary.
     * @return
     * @throws pions.model.ModelException.NotLoggedInException
     * @throws IOException
     */
    public byte[] getBytes() throws NotLoggedInException, IOException,
            StreamCorruptedException, ClassNotFoundException {
        if(type == AlertType.ContactRequest){
            return Login.getBytes(object);
        }
        else{
            return EmployeeSingleton.getInstance().encryptRSA(object);
        }
    }

    public EmailAddress getAddress(){
        return sender;
    }

    public AlertType getType(){
        return type;
    }

    @Override
    public String toString(){
        return type.toString() + ":\n" + object.toString();
    }

    //TODO implement RemoveEmployee request
    public enum AlertType{
        AddManager, AddSubordinate,
        ContactRequest, ContactResponse, RemoveEmployee,
        NewWorkSchedule, UpdatedWorkSchedule,
        DropShift;

        public Class getAssociatedClass(){
            switch(this){
                case AddManager:
                case AddSubordinate:
                    return Employee.class.getClass();
                case ContactRequest:
                case ContactResponse:
                case RemoveEmployee:
                    return Contact.class.getClass();
                case NewWorkSchedule:
                case UpdatedWorkSchedule:
                    return Calendar.class.getClass();
                case DropShift:
                    return DropShift.class.getClass();
                default:
                    return null;
            }
        }

        public static AlertType parse(String type){
            if(type.equals(AddManager.toString())){
                return AddManager;
            }
            else if(type.equals(AddSubordinate.toString())){
                return AddSubordinate;
            }
            else if(type.equals(ContactRequest.toString())){
                return ContactRequest;
            }
            else if(type.equals(RemoveEmployee.toString())){
                return RemoveEmployee;
            }
            else if(type.equals(NewWorkSchedule.toString())){
                return NewWorkSchedule;
            }
            else if(type.equals(UpdatedWorkSchedule.toString())){
                return UpdatedWorkSchedule;
            }
            else if(type.equals(DropShift.toString())){
                return DropShift;
            }
            else{
                return null;
            }
        }

        @Override
        public String toString(){
            switch(this){
                case AddManager:
                    break;
                case AddSubordinate:
                    break;
                case ContactRequest:
                    break;
                case RemoveEmployee:
                    break;
                case NewWorkSchedule:
                    break;
                case UpdatedWorkSchedule:
                    break;
                case DropShift:
                    break;
                default:
            }

            return this.name();
        }
    }
}
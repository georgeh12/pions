
package pions.model;

import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.internet.AddressException;
import pions.model.Contacts.Contact;
import pions.model.ModelException.AlertClassException;
import pions.model.ModelException.NotLoggedInException;
import pions.model.ModelException.ScheduleNotFoundException;
import pions.model.dropshift.DropShift;

/**
 * This class acts as a Mediator between the model.Gmail class and all the
 * Objects which can be stored in an Alert.
 * 
 */
public final class Alert implements Serializable {
    private String email = null;
    private AlertType type = null;
    private AbstractAlert object = null;

    public Alert(String email, AbstractAlert object, AlertType type){
        this.object = object;
        this.email = email;
        this.type = type;
    }

    public Alert(AbstractAlert object, AlertType type) throws NotLoggedInException{
        this(EmployeeSingleton.getInstance().getGmail().getGmailAddress().getAddress(),
                object, type);
    }

    public void accept()
            throws NotLoggedInException, ScheduleNotFoundException,
            AlertClassException, ServiceException, AuthenticationException,
            IOException, UnsupportedEncodingException, StreamCorruptedException,
            AddressException, NoSuchProviderException, MessagingException,
            ClassNotFoundException, NoSuchAlgorithmException, URISyntaxException {
        object.acceptAlert(type);
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
        if(type.isContactAlert()){
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
    public byte[] getBytes(String recipient) throws NotLoggedInException,
            IOException, StreamCorruptedException, ClassNotFoundException {
        if(type.isContactAlert()){
            return Login.getBytes(object);
        }
        else{
            return EmployeeSingleton.getInstance().encryptRSA(
                    EmployeeSingleton.getInstance().getPublicKey(recipient),
                    object);
        }
    }

    public String getAddress(){
        return email;
    }

    public AlertType getType(){
        return type;
    }

    @Override
    public String toString(){
        return type.toString() + ": " + object.toString();
    }

    //TODO implement RemoveEmployee request
    public enum AlertType{
        AddManager, AddSubordinate,
        ContactRequest, ContactResponse, RemoveEmployee,
        WorkSchedule, Availability,
        DropShift;

        public Class getAssociatedClass(){
            switch(this){
                case AddManager:
                case AddSubordinate:
                    return Employee.class;
                case ContactRequest:
                case ContactResponse:
                case RemoveEmployee:
                    return Contact.class;
                case WorkSchedule:
                case Availability:
                    return Calendar.class;
                case DropShift:
                    return DropShift.class;
                default:
                    return null;
            }
        }

        public boolean isContactAlert(){
            return this == ContactRequest || this == ContactResponse;
        }

        public boolean equals(String type){
            return name().equals(type);
        }

        @Override
        public String toString(){
            return this.name();
        }
    }
}

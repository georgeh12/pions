
package pions.model;

import java.io.IOException;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.internet.AddressException;
import pions.model.Alert.AlertType;
import pions.model.ContactInfo.EmailAddress;
import pions.model.ModelException.AlertClassException;
import pions.model.ModelException.NotLoggedInException;

/**
 * Implements Observer design pattern.
 * 
 */
public class Contacts extends Observable implements Serializable {
    private ArrayList<Contact> contacts = new ArrayList<Contact>();

    public void addContact(PublicKey public_key, EmailAddress email_address){
        Contact contact = new Contact(public_key, email_address);
        
        Contact search = searchContacts(contact.gmail_address);

        if(search == null){
            contacts.add(contact);
        }
        else {
            contacts.set(contacts.indexOf(search), contact);
        }

        setChanged();
        notifyObservers();
    }

    public Iterator<Contact> iterator(){
        return contacts.iterator();
    }

    public Contact searchContacts(String email_address){
        for(Contact contact: contacts){
            if(contact.getAddress().equals(email_address)) return contact;
        }

        return null;
    }

    public Contact searchContacts(EmailAddress email_address){
        for(Contact contact: contacts){
            if(contact.equals(email_address)) return contact;
        }
        
        return null;
    }

    public Contact get(int index){
        return contacts.get(index);
    }

    public static class Contact implements Serializable, AbstractAlert {
        private PublicKey public_key;
        private EmailAddress gmail_address;

        Contact(PublicKey public_key, EmailAddress gmail_address){
            this.public_key = public_key;
            this.gmail_address = gmail_address;
        }

        public EmailAddress getAddress() {
            return gmail_address;
        }
        
        public boolean equals(EmailAddress gmail_address){
            return this.gmail_address.equals(gmail_address);
        }

        public void acceptAlert(AlertType type, EmailAddress sender)
                throws NotLoggedInException, AlertClassException,
                AddressException, UnsupportedEncodingException,
                NoSuchProviderException, MessagingException,
                StreamCorruptedException, ClassNotFoundException, IOException,
                NoSuchAlgorithmException {
            switch(type){
                case ContactRequest:
                    EmployeeSingleton.getInstance().getGmail().sendAlert(sender,
                            new Alert(EmployeeSingleton.getInstance().getContact(), AlertType.ContactResponse));
                case ContactResponse:
                    EmployeeSingleton.getInstance().getContacts().addContact(public_key, gmail_address);
                    break;
                default:
                    throw new AlertClassException(this.getClass(), type.getAssociatedClass());
            }
        }

        public void rejectAlert(AlertType type) throws AlertClassException {
            switch(type){
                case ContactRequest:
                    //DONOTHING
                    break;
                default:
                    throw new AlertClassException(this.getClass(), type.getAssociatedClass());
            }
        }

        public void ignoreAlert(AlertType type) throws AlertClassException {
            switch(type){
                case ContactRequest:
                    //DONOTHING
                    break;
                default:
                    throw new AlertClassException(this.getClass(), type.getAssociatedClass());
            }
        }

        //TODO getDetails()
        public String getDetails() {
            StringBuffer buffer = new StringBuffer();

            buffer.append("Gmail Address: " + gmail_address.toString());

            return buffer.toString();
        }
    }
}

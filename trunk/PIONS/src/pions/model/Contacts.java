
package pions.model;

import java.io.Serializable;
import java.security.PublicKey;
import java.util.ArrayList;
import pions.model.Alert.AlertType;
import pions.model.ContactInfo.EmailAddress;
import pions.model.ModelException.AlertClassException;
import pions.model.ModelException.NotLoggedInException;

/**
 *
 * @author George
 */
public class Contacts implements Serializable {
    private ArrayList<Contact> contacts = new ArrayList<Contact>();

    public Contact addContact(PublicKey public_key, EmailAddress email_address){
        Contact contact = new Contact(public_key, email_address);
        
        Contact search = searchContacts(contact.gmail_address);

        if(search != null){
            contacts.indexOf(search);
        }
        else {
            contacts.add(contact);
        }

        return contact;
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

        public void acceptAlert(AlertType type) throws NotLoggedInException, AlertClassException {
            switch(type){
                case ContactRequest:
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
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}


package pions.model;

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
public class Contacts {
    private ArrayList<Contact> contacts = new ArrayList<Contact>();

    public Contact addContact(PublicKey public_key, EmailAddress email_address){
        Contact contact = new Contact(public_key, email_address);
        
        Contact search = searchContacts(contact.email_address);

        if(search != null){
            contacts.indexOf(search);
        }
        else {
            contacts.add(contact);
        }

        return contact;
    }

    public Contact searchContacts(EmailAddress email_address){
        for(Contact contact: contacts){
            if(contact.equals(email_address)) return contact;
        }
        
        return null;
    }

    public static class Contact implements AbstractAlert {
        private PublicKey public_key;
        private EmailAddress email_address;

        private Contact(PublicKey public_key, EmailAddress email_address){
            this.public_key = public_key;
            this.email_address = email_address;
        }

        public PublicKey getPublicKey(){
            return public_key;
        }
        
        public boolean equals(EmailAddress email_address){
            return this.email_address.equals(email_address);
        }

        public void acceptAlert(AlertType type) throws NotLoggedInException, AlertClassException {
            switch(type){
                case ContactRequest:
                    EmployeeSingleton.getInstance().getContacts().addContact(public_key, email_address);
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
    }
}

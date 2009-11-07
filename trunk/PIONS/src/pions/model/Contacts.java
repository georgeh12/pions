
package pions.model;

import java.security.PublicKey;
import java.util.ArrayList;
import pions.model.ContactInfo.EmailAddress;

/**
 *
 * @author George
 */
public class Contacts {
    private ArrayList<Contact> contacts;

    public Contact addContact(PublicKey public_key, EmailAddress email_address){
        Contact contact = new Contact(public_key, email_address);
        contacts.add(contact);

        return contact;
    }

    public Contact replaceContact(PublicKey public_key, EmailAddress email_address){
        Contact contact = new Contact(public_key, email_address);
        contacts.add(contact);

        return contact;
    }

    public Contact searchContacts(EmailAddress email_address){
        for(Contact contact: contacts){
            if(contact.equals(email_address)) return contact;
        }
        
        return null;
    }

    public static class Contact {
        private PublicKey public_key;
        private EmailAddress email_address;

        private Contact(PublicKey public_key, EmailAddress email_address){
            this.public_key = public_key;
            this.email_address = email_address;
        }

        //TODO
        public boolean equals(EmailAddress email_address){
            return false;
        }
    }
}

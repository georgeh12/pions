
package pions.controller;

import java.util.ArrayList;
import pions.model.Alert;
import pions.model.Alert.AlertType;
import pions.model.ContactInfo.EmailAddress;
import pions.model.Contacts.Contact;
import pions.model.ModelException.AlertClassException;
import pions.model.ModelException.NotLoggedInException;

/**
 *
 * @author George
 */
public class Contacts {
    public static void sendContactRequest(ArrayList<EmailAddress> gmail_addresses, Contact contact)
            throws NotLoggedInException{
        try {
            Gmail.sendAlert(gmail_addresses, new Alert(contact, AlertType.ContactRequest));
        } catch (AlertClassException e) {
            e.printStackTrace();
        }
    }
}

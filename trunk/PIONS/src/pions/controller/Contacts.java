
package pions.controller;

import pions.controller.xml.ContactIterator;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Observer;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import pions.controller.xml.AbstractXMLFactory;
import pions.model.Alert;
import pions.model.Alert.AlertType;
import pions.model.ContactInfo.EmailAddress;
import pions.model.EmployeeSingleton;
import pions.model.ModelException.NotLoggedInException;

/**
 * Implements Observer design pattern
 * 
 */
public final class Contacts {
    public static void subscribe(Observer o){
        try {
            EmployeeSingleton.getInstance().getContacts().addObserver(o);
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }
    }

    public static void sendContactRequest(String email_address) {
        try {
            Gmail.sendAlert(new EmailAddress(email_address),
                    new Alert(EmployeeSingleton.getInstance().getContact(),
                    AlertType.ContactRequest));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }
    }

    public static ContactIterator getContactIterator(){
        try {
            return new ContactIterator(EmployeeSingleton.getInstance().getContacts().iterator());
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Document getContact(int index) {
        try {
            return AbstractXMLFactory
                    .newInstance(EmployeeSingleton.getInstance().getContacts().get(index));
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void sendContactRequest(int index){
        try {
            Gmail.sendAlert(EmployeeSingleton.getInstance().getContacts().get(index).getAddress(),
                    new Alert(EmployeeSingleton.getInstance().getContact(),
                    AlertType.ContactRequest));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }
    }
}

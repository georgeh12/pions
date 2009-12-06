
package pions.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Observer;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import pions.controller.xml.AbstractXMLFactory;
import pions.controller.xml.XMLIterator;
import pions.model.Alert;
import pions.model.Alert.AlertType;
import pions.model.ContactInfo.EmailAddress;
import pions.model.Contacts.Contact;
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

    public static void sendContactResponse(Integer[] contact_indices) {
        try {
            for(int index: contact_indices){
                sendContactRequest(EmployeeSingleton.getInstance()
                        .getContacts().get(index).getAddress().getAddress(),
                        AlertType.ContactResponse);
            }
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }
    }

    public static boolean sendContactRequest(String email_address) {
        try{
            return sendContactRequest(email_address, AlertType.ContactRequest);
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }

        return true;
    }

    /**
     * Returns false if the employee tries to send a request to themself.
     * Even if an error occurs, it will return true.
     * @param email_address
     * @return
     */
    public static boolean sendContactRequest(String email_address, AlertType type)
            throws NotLoggedInException {
        try {
            if(EmployeeSingleton.getInstance().getGmail().getGmailAddress()
                    .getAddress().equals(email_address)){
                return false;
            }
            else {
                Gmail.sendAlert(new EmailAddress(email_address),
                        new Alert(EmployeeSingleton.getInstance().getContact(),
                        type));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    public static XMLIterator<Contact> getContactIterator(){
        try {
            return new XMLIterator<Contact>(EmployeeSingleton.getInstance().getContacts().iterator());
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
}

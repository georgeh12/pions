
package pions.controller;

import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.internet.AddressException;
import org.w3c.dom.Element;
import pions.controller.xml.XMLFactory;
import pions.controller.xml.XMLIterator;
import pions.model.Alert;
import pions.model.Contacts.Contact;
import pions.model.EmployeeSingleton;
import pions.model.ModelException.AlertClassException;
import pions.model.ModelException.NotLoggedInException;

/**
 *
 * 
 */
public final class Alerts {
    public static XMLIterator<Alert> getActiveAlertIterator() {
        try {
            return new XMLIterator<Alert>(EmployeeSingleton.getInstance().getGmail().getActiveAlerts());
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static XMLIterator<Alert> getSavedAlertIterator() {
        try {
            return new XMLIterator<Alert>(EmployeeSingleton.getInstance().getGmail().getSavedAlerts());
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ArrayList<String> parseAlerts() {
        ArrayList<String> exceptions = new ArrayList<String>();

        try {
            for(Exception exception: EmployeeSingleton.getInstance().getGmail().parseAlerts()){
                exceptions.add(exception.getCause().getMessage());
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        } finally {
            return exceptions;
        }
    }

    public static String getContactName(Element alert){
        try {
            Contact contact = EmployeeSingleton.getInstance().getContacts()
                    .searchContacts(XMLFactory.getAttribute(alert, XMLFactory.SENDER_EMAIL));
            if(contact != null) return contact.getAddress().getPersonal();
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static String getActiveAlert(int index){
        try {
            return EmployeeSingleton.getInstance().getGmail().getActiveAlert(index).get().getDetails();
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static String getSavedAlert(int index){
        try {
            return EmployeeSingleton.getInstance().getGmail().getSavedAlert(index).get().getDetails();
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static void deleteSavedAlert(int index){
        try {
            EmployeeSingleton.getInstance().getGmail().deleteSavedAlert(index);
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }
    }

    public static void acceptActiveAlert(int index) {
        try {
            EmployeeSingleton.getInstance().getGmail().saveAlert(index).accept();
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        } catch (AlertClassException e) {
            e.printStackTrace();
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static void rejectActiveAlert(int index){
        try {
            EmployeeSingleton.getInstance().getGmail().saveAlert(index).reject();
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        } catch (AlertClassException e) {
            e.printStackTrace();
        }
    }

    public static void ignoreActiveAlert(int index){
        try {
            EmployeeSingleton.getInstance().getGmail().saveAlert(index).ignore();
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        } catch (AlertClassException e) {
            e.printStackTrace();
        }
    }
}

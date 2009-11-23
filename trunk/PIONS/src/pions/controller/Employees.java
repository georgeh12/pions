
package pions.controller;

import pions.controller.xml.EmployeeXMLFactory;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import pions.model.Alert;
import pions.model.Alert.AlertType;
import pions.model.ContactInfo.EmailAddress;
import pions.model.Employee;
import pions.model.EmployeeSingleton;
import pions.model.ModelException.AlertClassException;
import pions.model.ModelException.ContactNotFoundException;
import pions.model.ModelException.NotLoggedInException;

/**
 *
 * @author George
 */
public final class Employees {
    /**
     * Initializes the new employee as the EmployeeSingleton.
     * @param name
     * @param username
     * @param password
     * @param gmail_username
     * @param gmail_password
     */
    public static boolean createEmployee(String name, String username, String password,
            String gmail_username, String gmail_password){
        try {
            EmployeeSingleton.init(name, username, password);
            Gmail.setGmail(gmail_username, gmail_password);
            EmployeeSingleton.getInstance().saveFile();

            return true;
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean login(String username, String password){
        try {
            EmployeeSingleton.login(username, password);

            return true;
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean logout() {
        try {
            EmployeeSingleton.getInstance().deleteFile();
            EmployeeSingleton.getInstance().saveFile();
            EmployeeSingleton.getInstance().logout();

            return true;
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean isLoggedIn(){
        return EmployeeSingleton.isLoggedIn();
    }

    public static Document getManagerXML(int index) {
        try {
            return new EmployeeXMLFactory()
                    .newInstance(EmployeeSingleton.getInstance().getManager(index));
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Document getSubordinateXML(int index){
        try {
            return new EmployeeXMLFactory()
                    .newInstance(EmployeeSingleton.getInstance().getSubordinate(index));
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ArrayList<String> getManagers(){
        try {
            return EmployeeSingleton.getInstance().getManagerNames();
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }

        return new ArrayList<String>();
    }

    public static ArrayList<String> getSubordinates(){
        try {
            return EmployeeSingleton.getInstance().getSubordinateNames();
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }

        return new ArrayList<String>();
    }

    public static void sendNewManager(String email) {
        try {
            sendNewEmployee(email, AlertType.AddManager);
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        } catch (AlertClassException e) {
            e.printStackTrace();
        } catch (ContactNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void sendNewSubordinate(String email) {
        try {
            sendNewEmployee(email, AlertType.AddSubordinate);
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        } catch (AlertClassException e) {
            e.printStackTrace();
        } catch (ContactNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void sendNewEmployee(String email, AlertType type)
            throws NotLoggedInException, AlertClassException,
            ContactNotFoundException {
        EmailAddress recipient = EmployeeSingleton.getInstance().getContacts()
                .searchContacts(email).getAddress();
        if(recipient != null){
            Gmail.sendAlert(recipient,
                    new Alert((Employee)EmployeeSingleton.getInstance(),
                    type));
        }
        else{
            throw new ContactNotFoundException();
        }
    }

    public static String getDisplayName() {
        try {
            return EmployeeSingleton.getInstance().getDisplayName();
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }

        return "User";
    }
}

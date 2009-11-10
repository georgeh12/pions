
package pions.controller;

import java.io.IOException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import pions.model.Alert;
import pions.model.Alert.AlertType;
import pions.model.ContactInfo.EmailAddress;
import pions.model.Employee;
import pions.model.EmployeeSingleton;
import pions.model.ModelException.AlertClassException;
import pions.model.ModelException.NotLoggedInException;

/**
 *
 * @author George
 */
public class Employees {
    /**
     * Initializes the new employee as the EmployeeSingleton.
     * @param name
     * @param username
     * @param password
     * @param gmail_username
     * @param gmail_password
     */
    public static void createEmployee(String name, String username, String password,
            String gmail_username, String gmail_password){
        try {
            EmployeeSingleton.init(name, username, password);
            EmployeeSingleton.getInstance().getGmail().setGmail(new EmailAddress(gmail_username, name), gmail_password);
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }
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
        } finally {
            return false;
        }
    }

    public static void logout(){
        try {
            EmployeeSingleton.getInstance().logout();
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }
    }

    public static EmployeeXML getManagerXML(int index){
        try {
            return new EmployeeXML(EmployeeSingleton.getInstance().getManager(index));
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        } finally {
            return null;
        }
    }

    public static EmployeeXML getSubordinateXML(int index){
        try {
            return new EmployeeXML(EmployeeSingleton.getInstance().getSubordinate(index));
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        } finally {
            return null;
        }
    }

    public static ArrayList<String> getManagers(){
        try {
            return EmployeeSingleton.getInstance().getManagerNames();
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        } finally {
            return new ArrayList<String>();
        }
    }

    public static ArrayList<String> getSubordinates(){
        try {
            return EmployeeSingleton.getInstance().getSubordinateNames();
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        } finally {
            return new ArrayList<String>();
        }
    }

    public static void sendNewManager(EmployeeSingleton manager){
        try {
            sendNewSubordinate(EmployeeSingleton.getInstance().getManagerGmails(),
                    manager);
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        } catch (AlertClassException e) {
            e.printStackTrace();
        }
    }

    static void sendNewManager(ArrayList<EmailAddress> gmails,
            EmployeeSingleton manager) throws NotLoggedInException,
            AlertClassException{
        Gmail.sendAlert(gmails, new Alert((Employee)manager, AlertType.AddManager));
    }

    public static void sendNewSubordinate(EmployeeSingleton subordinate){
        try {
            sendNewSubordinate(EmployeeSingleton.getInstance().getSubordinateGmails(),
                    subordinate);
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        } catch (AlertClassException e) {
            e.printStackTrace();
        }
    }

    static void sendNewSubordinate(ArrayList<EmailAddress> gmails,
            EmployeeSingleton subordinate) throws NotLoggedInException,
            AlertClassException{
        Gmail.sendAlert(gmails, new Alert((Employee)subordinate, AlertType.AddSubordinate));
    }
}

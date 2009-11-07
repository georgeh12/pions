
package pions.controller;

import java.util.ArrayList;
import pions.model.ContactInfo.EmailAddress;
import pions.model.EmployeeSingleton;
import pions.model.ModelException.AlertClassException;
import pions.model.ModelException.NotLoggedInException;

/**
 *
 * @author George
 */
public class Employee {
    public static void addAbove(String name, String username, String password,
            String gmail_username, String gmail_password){
        try {
            ArrayList<EmailAddress> gmails = EmployeeSingleton.getInstance().getManagerGmails();
            EmployeeSingleton.getInstance().addAbove(name, username, password);
            
            initEmployee(name, username, password, gmail_username, gmail_password, gmails);
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        } catch (AlertClassException e) {
            e.printStackTrace();
        }
    }

    public static void addBelow(String name, String username, String password,
            String gmail_username, String gmail_password){
        try {
            ArrayList<EmailAddress> gmails = EmployeeSingleton.getInstance().getSubordinateGmails();
            EmployeeSingleton.getInstance().addBelow(name, username, password);

            initEmployee(name, username, password, gmail_username, gmail_password, gmails);
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        } catch (AlertClassException e) {
            e.printStackTrace();
        }
    }

    private static void initEmployee(String name, String username, String password,
            String gmail_username, String gmail_password, ArrayList<EmailAddress> gmails)
            throws NotLoggedInException, AlertClassException{
        EmployeeSingleton.init(name, username, password);

        EmployeeSingleton.getInstance().getGmail().setGmail(new EmailAddress(gmail_username, name), gmail_password);

        Gmail.sendNewManager(gmails, EmployeeSingleton.getInstance());
    }
}

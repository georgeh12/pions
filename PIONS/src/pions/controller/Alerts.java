
package pions.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.mail.MessagingException;
import pions.model.EmployeeSingleton;
import pions.model.ModelException.AlertClassException;
import pions.model.ModelException.NotLoggedInException;

/**
 *
 * @author George
 */
public class Alerts {public static Iterator getActiveAlertIterator() {
        try {
            return new AlertIterator(EmployeeSingleton.getInstance().getGmail().getActiveAlerts());
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        } finally {
            //might cause cascading View exception
            return null;
        }
    }

    public static Iterator getSavedAlertIterator() {
        try {
            return new AlertIterator(EmployeeSingleton.getInstance().getGmail().getSavedAlerts());
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        } finally {
            //might cause cascading View exception
            return null;
        }
    }

    public static ArrayList<String> parseAlerts() {
        ArrayList<String> exceptions = new ArrayList<String>();

        try {
            for(Exception exception: EmployeeSingleton.getInstance().getGmail().parseAlerts()){
                exceptions.add(exception.getCause().getMessage());
                exceptions.add(exception.getMessage());
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

    public static void deleteSavedAlert(int index){
        try {
            EmployeeSingleton.getInstance().getGmail().deleteSavedAlert(index);
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }
    }

    public static void acceptActiveAlert(int index){
        try {
            EmployeeSingleton.getInstance().getGmail().saveAlert(index).accept();
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        } catch (AlertClassException e) {
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


package pions.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observer;
import javax.mail.MessagingException;
import pions.model.Alert;
import pions.model.EmployeeSingleton;
import pions.model.ModelException.AlertClassException;
import pions.model.ModelException.NotLoggedInException;

/**
 *
 * @author George
 */
public class Alerts {
    public static Iterator getActiveAlertIterator(Observer observer) {
        try {
            return new AlertIterator(EmployeeSingleton.getInstance().getGmail().getActiveAlerts(),
                    observer);
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        } finally {
            //might cause cascading View exception
            return null;
        }
    }

    public static Iterator getSavedAlertIterator(Observer observer) {
        try {
            return new AlertIterator(EmployeeSingleton.getInstance().getGmail().getSavedAlerts(),
                    observer);
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
            pions.model.Gmail gmail = EmployeeSingleton.getInstance().getGmail();

            for(Exception exception: gmail.parseAlerts()){
                exceptions.add(exception.getCause().getMessage());
                exceptions.add(exception.getMessage());
            }

            gmail.notifyObservers();
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

    public static void acceptActiveAlert(Observer observer, int index){
        try {
            Alert alert = EmployeeSingleton.getInstance().getGmail().saveAlert(index);

            alert.accept();

            alert.addObserver(observer);
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        } catch (AlertClassException e) {
            e.printStackTrace();
        }
    }

    public static void rejectActiveAlert(Observer observer, int index){
        try {
            Alert alert = EmployeeSingleton.getInstance().getGmail().saveAlert(index);

            alert.reject();

            alert.addObserver(observer);
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        } catch (AlertClassException e) {
            e.printStackTrace();
        }
    }

    public static void ignoreActiveAlert(Observer observer, int index){
        try {
            Alert alert = EmployeeSingleton.getInstance().getGmail().saveAlert(index);

            alert.ignore();

            alert.addObserver(observer);
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        } catch (AlertClassException e) {
            e.printStackTrace();
        }
    }
}

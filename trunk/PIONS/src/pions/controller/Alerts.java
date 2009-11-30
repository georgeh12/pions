
package pions.controller;

import pions.controller.xml.AlertIterator;
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
import pions.model.EmployeeSingleton;
import pions.model.ModelException.AlertClassException;
import pions.model.ModelException.NotLoggedInException;
import pions.model.ModelException.ScheduleNotFoundException;

/**
 *
 * 
 */
public final class Alerts {
    public static AlertIterator getActiveAlertIterator() {
        try {
            return new AlertIterator(EmployeeSingleton.getInstance().getGmail().getActiveAlerts());
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }

        //might cause cascading View exception
        return null;
    }

    public static AlertIterator getSavedAlertIterator() {
        try {
            return new AlertIterator(EmployeeSingleton.getInstance().getGmail().getSavedAlerts());
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }

        //might cause cascading View exception
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
            EmployeeSingleton.getInstance().getGmail().getSavedAlert(index).get().getDetails();
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
        } catch (ScheduleNotFoundException e) {
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

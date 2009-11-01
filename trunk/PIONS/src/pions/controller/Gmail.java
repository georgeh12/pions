
package pions.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.internet.AddressException;
import pions.model.ContactInfo.EmailAddress;
import pions.model.EmployeeSingleton;
import pions.model.ModelException.NotLoggedInException;
import pions.model.alerts.Alert.AlertType;
import pions.model.alerts.WorkScheduleAlert;

/**
 *
 * @author George
 */
public class Gmail implements Serializable {
    public void setGmail(Observer observer, String gmail_username, String gmail_password){
        try {
            pions.model.Gmail gmail = EmployeeSingleton.getInstance().getGmail();
            gmail.setGmail(new EmailAddress(gmail_username), gmail_password);

            gmail.notifyObservers();
            gmail.addObserver(observer);
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> parseAlerts() {
        ArrayList<String> exceptions = new ArrayList<String>();

        try {
            pions.model.Gmail gmail = EmployeeSingleton.getInstance().getGmail();

            for(Exception exception: gmail.parseAlerts()){
                exceptions.add(exception.toString());
            }
            
            gmail.notifyObservers();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
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

    public void saveAlert(Observer observer, int index){
        try {
            EmployeeSingleton.getInstance().getGmail().saveAlert(index).addObserver(observer);
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getSubordinates(){
        try {
            return EmployeeSingleton.getInstance().getSubordinateNames();
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        } finally {
            return new ArrayList<String>();
        }
    }

    public ArrayList<String> getManagers(){
        try {
            return EmployeeSingleton.getInstance().getManagerNames();
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        } finally {
            return new ArrayList<String>();
        }
    }

    public void sendNewWorkSchedule(boolean[] indices){
        try {
            ArrayList<EmailAddress> gmail_addresses = EmployeeSingleton.getInstance().getSubordinateGmails();
            ArrayList<EmailAddress> selected = new ArrayList<EmailAddress>();

            for(int i = 0; i < gmail_addresses.size(); i++){
                if(indices[i]) {
                    selected.add(gmail_addresses.get(i));
                }
            }

            sendWorkSchedule(gmail_addresses, AlertType.NewWorkSchedule);
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }
    }

    public void sendUpdatedWorkSchedule(boolean[] indices){
        try {
            ArrayList<EmailAddress> gmail_addresses = EmployeeSingleton.getInstance().getSubordinateGmails();
            ArrayList<EmailAddress> selected = new ArrayList<EmailAddress>();

            for(int i = 0; i < gmail_addresses.size(); i++){
                if(indices[i]) {
                    selected.add(gmail_addresses.get(i));
                }
            }

            sendWorkSchedule(gmail_addresses, AlertType.UpdatedWorkSchedule);
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }
    }

    public void sendNewWorkSchedule(){
        try {
            sendWorkSchedule(EmployeeSingleton.getInstance().getSubordinateGmails(), AlertType.NewWorkSchedule);
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }
    }

    public void sendUpdatedWorkSchedule(){
        try {
            sendWorkSchedule(EmployeeSingleton.getInstance().getSubordinateGmails(), AlertType.UpdatedWorkSchedule);
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }
    }

    private void sendWorkSchedule(ArrayList<EmailAddress> gmail_addresses, AlertType alert_type)
            throws NotLoggedInException {
        try {
            EmployeeSingleton.getInstance().getGmail().sendAlert(gmail_addresses,
                    new WorkScheduleAlert(EmployeeSingleton.getInstance().getWorkSchedule(), alert_type));
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

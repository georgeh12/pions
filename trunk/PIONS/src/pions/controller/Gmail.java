
package pions.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observer;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.internet.AddressException;
import pions.model.ContactInfo.EmailAddress;
import pions.model.EmployeeSingleton;
import pions.model.ModelException.NotLoggedInException;
import pions.model.Alert;
import pions.model.Alert.AlertType;
import pions.model.swapshift.SwapShift;

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

    public ArrayList<String> getSubordinateGmails(){
        ArrayList<String> email_addresses = new ArrayList<String>();

        try {
            for(EmailAddress email_address: EmployeeSingleton.getInstance().getSubordinateGmails()){
                email_addresses.add(email_address.toString());
            }
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        } finally {
            return email_addresses;
        }
    }

    private ArrayList<EmailAddress> getSelectedEmails(ArrayList<EmailAddress> gmail_addresses, boolean[] indices)
            throws NotLoggedInException {
        ArrayList<EmailAddress> selected = new ArrayList<EmailAddress>();

        for(int i = 0; i < gmail_addresses.size(); i++){
            if(indices[i]) {
                selected.add(gmail_addresses.get(i));
            }
        }

        return selected;
    }

    public void sendNewWorkSchedule(boolean[] indices){
        try {
            sendWorkSchedule(getSelectedEmails(EmployeeSingleton.getInstance().getSubordinateGmails(), indices),
                    AlertType.NewWorkSchedule);
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }
    }

    public void sendUpdatedWorkSchedule(boolean[] indices){
        try {
            sendWorkSchedule(getSelectedEmails(EmployeeSingleton.getInstance().getSubordinateGmails(), indices),
                    AlertType.UpdatedWorkSchedule);
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }
    }

    private void sendWorkSchedule(ArrayList<EmailAddress> gmail_addresses, AlertType alert_type)
            throws NotLoggedInException {
        sendAlert(gmail_addresses, new Alert(EmployeeSingleton.getInstance().getWorkSchedule(), alert_type));
    }

    public void sendSwapShift(SwapShift swap_shift) throws NotLoggedInException{
        sendAlert(swap_shift.getRecipients(), new Alert(swap_shift, AlertType.SwapShift));
    }

    private void sendAlert(ArrayList<EmailAddress> gmail_addresses, Alert alert)
            throws NotLoggedInException {
        try {
            EmployeeSingleton.getInstance().getGmail().sendAlert(gmail_addresses,
                    alert);
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

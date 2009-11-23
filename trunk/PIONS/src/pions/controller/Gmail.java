
package pions.controller;

import java.io.IOException;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.internet.AddressException;
import pions.model.ContactInfo.EmailAddress;
import pions.model.EmployeeSingleton;
import pions.model.ModelException.NotLoggedInException;
import pions.model.Alert;

/**
 *
 * @author George
 */
public final class Gmail implements Serializable {
    public static void setGmail(String gmail_username, String gmail_password){
        try {
            EmployeeSingleton.getInstance().setGmail(
                    new EmailAddress(gmail_username, EmployeeSingleton.getInstance().getName()),
                    gmail_password);
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }
    }

    static ArrayList<EmailAddress> getSelectedEmails(ArrayList<EmailAddress> gmail_addresses,
            boolean[] indices) {
        ArrayList<EmailAddress> selected = new ArrayList<EmailAddress>();

        for(int i = 0; i < gmail_addresses.size(); i++){
            if(indices[i]) {
                selected.add(gmail_addresses.get(i));
            }
        }

        return selected;
    }

    public static ArrayList<String> getSubordinateGmails(){
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

    static void sendAlert(EmailAddress recipient, Alert alert)
            throws NotLoggedInException {
        try {
            EmployeeSingleton.getInstance().getGmail().sendAlert(recipient, alert);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}


package pions.controller;

import pions.model.Alert;
import pions.model.Alert.AlertType;
import pions.model.ContactInfo.EmailAddress;
import pions.model.EmployeeSingleton;
import pions.model.ModelException.AlertClassException;
import pions.model.ModelException.NotLoggedInException;

/**
 *
 * @author George
 */
public class Contacts {
    public static void sendContactRequest(String email_address) {
        try {
            Gmail.sendAlert(new EmailAddress(email_address),
                    new Alert(EmployeeSingleton.getInstance().getContact(),
                    AlertType.ContactRequest));
        } catch (AlertClassException e) {
            e.printStackTrace();
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }
    }
}

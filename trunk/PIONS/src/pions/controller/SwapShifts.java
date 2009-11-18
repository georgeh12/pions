
package pions.controller;

import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;
import java.io.IOException;
import java.net.MalformedURLException;
import pions.model.Alert;
import pions.model.Alert.AlertType;
import pions.model.ContactInfo.EmailAddress;
import pions.model.EmployeeSingleton;
import pions.model.ModelException.AlertClassException;
import pions.model.ModelException.NotLoggedInException;
import pions.model.swapshift.SwapShift;

/**
 *
 * @author George
 */
public class SwapShifts {
    private static SwapShift swap_shift;

    public static void createSwapShift(){
        try {
            swap_shift = new SwapShift(EmployeeSingleton.getInstance(),
                    EmployeeSingleton.getInstance().getCalendars().getWorkSchedule());
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }
    }

    public static void sendSwapShift(SwapShift swap_shift) {
        try {
            for(EmailAddress email_address: swap_shift.getRecipients()){
                Gmail.sendAlert(email_address, new Alert(swap_shift, AlertType.SwapShift));
            }
        } catch (AlertClassException e) {
            e.printStackTrace();
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }
    }
}

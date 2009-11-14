
package pions.controller;

import pions.model.Alert;
import pions.model.Alert.AlertType;
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
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }
    }

    public static void sendSwapShift(SwapShift swap_shift) {
        try {
            Gmail.sendAlert(swap_shift.getRecipients(), new Alert(swap_shift, AlertType.SwapShift));
        } catch (AlertClassException e) {
            e.printStackTrace();
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }
    }
}

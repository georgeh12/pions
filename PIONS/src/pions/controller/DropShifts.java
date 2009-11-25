
package pions.controller;

import com.google.gdata.data.calendar.CalendarEntry;
import pions.model.Alert;
import pions.model.Alert.AlertType;
import pions.model.EmployeeSingleton;
import pions.model.ModelException.AlertClassException;
import pions.model.ModelException.NotLoggedInException;
import pions.model.dropshift.DropShift;

/**
 *
 * 
 */
public final class DropShifts {
    
    private static DropShift drop_shift = null;
    private static CalendarEntry shift_event = null;

    public static boolean isInit(){
        return drop_shift != null;
    }

    public static void setCurrentShift(int index){
        shift_event = Calendars.getWorkEvent(index);
    }

    public static void createDropShift() {
        drop_shift = new DropShift(shift_event);
    }

    public static void deleteDropShift() {
        drop_shift = null;
    }

    public static void sendDropShift() {
        try{
            Gmail.sendAlert(EmployeeSingleton.getInstance().getManagerGmail(),
                    new Alert(drop_shift, AlertType.DropShift));
        } catch(NotLoggedInException e){
            e.printStackTrace();
        }
    }
}

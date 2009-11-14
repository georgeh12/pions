
package pions.controller;

import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;
import java.awt.Desktop;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import pions.model.Alert;
import pions.model.Alert.AlertType;
import pions.model.CalendarData;
import pions.model.ContactInfo.EmailAddress;
import pions.model.EmployeeSingleton;
import pions.model.ModelException.AlertClassException;
import pions.model.ModelException.NotLoggedInException;
import pions.model.swapshift.SwapShift;

/**
 *
 * @author George
 */
public class Calendars {
    public static enum CalendarType {
        Availability, SubordinateSchedule, WorkSchedule;
    }
    
    public static void openCalendar(CalendarType type){
        try {
            pions.model.Calendars calendars = EmployeeSingleton.getInstance().getCalendars();
            CalendarData calendar = null;

            switch (type) {
                case Availability:
                    calendar = calendars.getAvailability();
                    break;
                case SubordinateSchedule:
                    calendar = calendars.getSubordinateSchedule();
                    break;
                case WorkSchedule:
                    calendar = calendars.getWorkSchedule();
                    break;
                default:
            }

            Desktop.getDesktop().browse(new URI(calendar.getLink().getHref()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }
    }

    public static CalendarData initCalendar(String calendar_name){
        try {
            if(EmployeeSingleton.getInstance().getGmail().isValid()){
                    return new CalendarData(calendar_name, true);
            }
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

        //If an exception occurred, calendar is set to default
        return new CalendarData(calendar_name);
    }

    public static void sendNewWorkSchedule(boolean[] indices){
        try {
            sendWorkSchedule(Gmail.getSelectedEmails(EmployeeSingleton.getInstance().getSubordinateGmails(), indices),
                    AlertType.NewWorkSchedule);
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        } catch (AlertClassException e) {
            e.printStackTrace();
        }
    }

    public static void sendUpdatedWorkSchedule(boolean[] indices){
        try {
            sendWorkSchedule(Gmail.getSelectedEmails(EmployeeSingleton.getInstance().getSubordinateGmails(), indices), AlertType.UpdatedWorkSchedule);
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        } catch (AlertClassException e) {
            e.printStackTrace();
        }
    }

    static void sendWorkSchedule(ArrayList<EmailAddress> gmail_addresses, AlertType alert_type)
            throws NotLoggedInException, AlertClassException {
        Gmail.sendAlert(gmail_addresses,
                new Alert(EmployeeSingleton.getInstance().getCalendars().getWorkSchedule(), alert_type));
    }
}


package pions.controller;

import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;
import java.awt.Desktop;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import pions.model.Alert;
import pions.model.Alert.AlertType;
import pions.model.CalendarData;
import pions.model.ContactInfo.EmailAddress;
import pions.model.EmployeeSingleton;
import pions.model.ModelException.AlertClassException;
import pions.model.ModelException.NotLoggedInException;

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

            //TODO should NOT actually view the calendar
            Desktop.getDesktop().browse(new URI(calendar.getLink().getHref()));
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
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

    public static void sendNewWorkSchedule(boolean[] indices){
        try {
            for(EmailAddress email_address: Gmail.getSelectedEmails(
                    EmployeeSingleton.getInstance().getSubordinateGmails(), indices)){
                sendWorkSchedule(email_address, AlertType.NewWorkSchedule);
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
        } catch (AlertClassException e) {
            e.printStackTrace();
        }
    }

    public static void sendUpdatedWorkSchedule(boolean[] indices){
        try {
            for(EmailAddress email_address: Gmail.getSelectedEmails(
                    EmployeeSingleton.getInstance().getSubordinateGmails(), indices)){
                sendWorkSchedule(email_address, AlertType.UpdatedWorkSchedule);
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
        } catch (AlertClassException e) {
            e.printStackTrace();
        }
    }

    static void sendWorkSchedule(EmailAddress gmail_address, AlertType alert_type)
            throws NotLoggedInException, AlertClassException,
            AuthenticationException, MalformedURLException,
            ServiceException, IOException {
        Gmail.sendAlert(gmail_address,
                new Alert(EmployeeSingleton.getInstance().getCalendars().getWorkSchedule(), alert_type));
    }
}

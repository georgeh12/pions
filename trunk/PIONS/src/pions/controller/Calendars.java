
package pions.controller;

import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;
import java.awt.Desktop;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import pions.model.CalendarData;
import pions.model.EmployeeSingleton;
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
        } finally {
            //If an exception occurred, calendar is set to default
            return new CalendarData(calendar_name);
        }
    }
}

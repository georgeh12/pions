
package pions.controller;

import com.google.gdata.data.calendar.CalendarEntry;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import pions.model.Alert;
import pions.model.Alert.AlertType;
import pions.model.CalendarData;
import pions.model.ContactInfo.EmailAddress;
import pions.model.EmployeeSingleton;
import pions.model.ModelException.AlertClassException;
import pions.model.ModelException.NotLoggedInException;
import pions.model.ModelException.ScheduleNotFoundException;

/**
 *
 * @author George
 */
public class Calendars {

    public static void addAvailabilityEvent(String gmail_address, String contact, String details, Date start, Date end){
        try {
            EmployeeSingleton.getInstance().getCalendars().getAvailability().addEvent(gmail_address, contact, details, start, end);
        } catch (AuthenticationException e){
            e.printStackTrace();
        } catch (ServiceException e){
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }
    }

    public static CalendarIterator getAvailabilityEvents() {
        try {
            return new CalendarIterator(EmployeeSingleton.getInstance()
                    .getCalendars().getAvailability().getEvents());
        } catch (AuthenticationException e){
            e.printStackTrace();
        } catch (ServiceException e){
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }

        return null;
    }

    static CalendarEntry getEvent(int index) {
        try {
            return EmployeeSingleton.getInstance().getCalendars().getWorkSchedule().getEvent(index);
        } catch (AuthenticationException e){
            e.printStackTrace();
        } catch (ServiceException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        } catch (ScheduleNotFoundException e){
            e.printStackTrace();
        }

        return null;
    }
    
    public static URI getReadLink(CalendarType type){
        try {
            CalendarData calendar = null;

            switch (type) {
                case Availability:
                    calendar = EmployeeSingleton.getInstance().getCalendars().getAvailability();
                    break;
                case SubordinateSchedule:
                    calendar = EmployeeSingleton.getInstance().getCalendars().getSubordinateSchedule();
                    break;
                case WorkSchedule:
                    calendar = EmployeeSingleton.getInstance().getCalendars().getWorkSchedule();
                    break;
                default:
            }

            return calendar.getReadLink();
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
        } catch (ScheduleNotFoundException e){
            e.printStackTrace();
        }

        return null;
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
        } catch (ScheduleNotFoundException e){
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
        } catch (ScheduleNotFoundException e){
            e.printStackTrace();
        }
    }

    private static void sendWorkSchedule(EmailAddress gmail_address, AlertType alert_type)
            throws NotLoggedInException, AlertClassException,
            AuthenticationException, MalformedURLException,
            ServiceException, IOException, ScheduleNotFoundException {
        CalendarData work_schedule = EmployeeSingleton.getInstance().getCalendars().getWorkSchedule();
        work_schedule.shareRead(gmail_address.getAddress());

        Gmail.sendAlert(gmail_address,
                new Alert(work_schedule, alert_type));
    }

    public static enum CalendarType {
        Availability, SubordinateSchedule, WorkSchedule;
    }
}

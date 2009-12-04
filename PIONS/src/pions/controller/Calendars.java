
package pions.controller;

import javax.xml.parsers.ParserConfigurationException;
import com.google.gdata.data.extensions.EventEntry;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import org.w3c.dom.Document;
import pions.model.xml.AbstractXMLFactory;
import pions.model.xml.XMLIterator;
import pions.model.Alert;
import pions.model.Alert.AlertType;
import pions.model.Calendar;
import pions.model.ContactInfo.EmailAddress;
import pions.model.EmployeeSingleton;
import pions.model.ModelException.NotLoggedInException;
import pions.model.ModelException.ScheduleNotFoundException;

/**
 *
 * 
 */
public final class Calendars {

    public static void addAvailabilityEvent(String title, String description,
            Date start, Date end){
        try {
            EmployeeSingleton.getInstance().getCalendars().getAvailability()
                    .addEvent(null, title, description, start, end);
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

    public static XMLIterator<EventEntry> getAvailabilityEvents() {
        try {
            return new XMLIterator<EventEntry>(EmployeeSingleton.getInstance()
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

    public static void deleteAvailabilityEvent(int index) {
        try {
            EmployeeSingleton.getInstance().getCalendars().getAvailability().delete(index);
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

    public static void addScheduleShift(String gmail_address,
            String name, String position, Date start, Date end){
        try {
            EmployeeSingleton.getInstance().getCalendars().getAvailability()
                    .addEvent((gmail_address == null ? null : new EmailAddress(gmail_address)),
                    name, position, start, end);
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

    public static XMLIterator<EventEntry> getScheduleShifts() {
        try {
            return new XMLIterator<EventEntry>(EmployeeSingleton.getInstance()
                    .getCalendars().getSubordinateSchedule().getEvents());
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

    public static void deleteScheduleShift(int index) {
        try {
            EmployeeSingleton.getInstance().getCalendars().getAvailability().delete(index);
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

    public static Document getWorkShift(int index) {
        try {
            return AbstractXMLFactory
                    .newInstance(EmployeeSingleton.getInstance().getCalendars()
                    .getWorkSchedule().getEvent(index));
        } catch (ParserConfigurationException e){
            e.printStackTrace();
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

    static EventEntry getWorkEvent(int index) {
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
            Calendar calendar = null;

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

    public static void sendWorkSchedule(){
        try{
            for(EmailAddress gmail_address:
                    EmployeeSingleton.getInstance().getSubordinateGmails()){
                sendCalendar(gmail_address, AlertType.WorkSchedule);
            }
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }
    }

    public static void sendAvailability(String email_address, String personal){
        sendCalendar(new EmailAddress(email_address, personal),
                AlertType.Availability);
    }
    
    private static void sendCalendar(EmailAddress email_address, AlertType type){
        try {
            Calendar work_schedule =
                    EmployeeSingleton.getInstance().getCalendars().getWorkSchedule();
            work_schedule.shareRead(email_address.getAddress());

            Gmail.sendAlert(email_address,
                    new Alert(work_schedule, type));
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
        } catch (ScheduleNotFoundException e){
            e.printStackTrace();
        }
    }

    public static enum CalendarType {
        Availability, SubordinateSchedule, WorkSchedule;
    }
}

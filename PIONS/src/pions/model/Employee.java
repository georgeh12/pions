
package pions.model;

import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.ArrayList;
import pions.model.ModelException.NotLoggedInException;

/**
 *
 * @author George
 */
public class Employee extends Login implements Serializable {
    private final static String AVAILABILITY = "PIONS Availability";
    private final static String WORK_SCHEDULE = "PIONS Work Schedule";
    private final static String SUBORDINATE_SCHEDULE = "PIONS Subordinate Schedule";
    private Gmail gmail;
    private ContactInfo contact_info;
    private CalendarCollection availability;
    private CalendarCollection work_schedule;
    private CalendarCollection subordinate_schedule;
    private ArrayList<Position> positions = new ArrayList<Position>();
    private ArrayList<Employee> managers = new ArrayList<Employee>();
    private ArrayList<Employee> subordinates = new ArrayList<Employee>();

    public Gmail getGmail(){
        if(gmail == null) gmail = new Gmail();
        return gmail;
    }

    public ContactInfo getContactInfo(){
        if(contact_info == null) contact_info = new ContactInfo();
        return contact_info;
    }

    public CalendarCollection getAvailability(){
        if(availability == null) availability = new CalendarCollection();
        return availability;
    }

    public CalendarCollection getWorkSchedule(){
        if(work_schedule == null) work_schedule = new CalendarCollection();
        return work_schedule;
    }

    public CalendarCollection getSubordinateSchedule(){
        if(subordinate_schedule == null) subordinate_schedule = new CalendarCollection();
        return subordinate_schedule;
    }

    public Employee(String username, String password){
        super.setLogin(username, password);
    }

    public void initGoogle(String gmail_username, String gmail_password)
            throws AuthenticationException, MalformedURLException,
            ServiceException, IOException, NotLoggedInException {
        gmail = new Gmail(gmail_username, gmail_password);
        availability = new CalendarCollection(AVAILABILITY);
        work_schedule = new CalendarCollection(WORK_SCHEDULE);
        subordinate_schedule = new CalendarCollection(SUBORDINATE_SCHEDULE);
    }

    //TODO fix once public key is done
    public String getPublicKey(){
        return public_key.get();
    }

    public void addPosition(String title, boolean hourly, double rate){
        positions.add(new Position(title, hourly, rate));

        notifyObservers();
    }

    public void setPosition(int index, String title, boolean hourly, double rate){
        positions.set(index, new Position(title, hourly, rate));

        notifyObservers();
    }

    /**
     * Returns a clone of positions.
     * @return
     */
    public ArrayList<Position> getPositions(){
        return (ArrayList<Position>) positions.clone();
    }

    public void removeManager(int index){
        managers.remove(index);

        notifyObservers();
    }

    public void removeSubordinate(int index){
        subordinates.remove(index);

        notifyObservers();
    }

    /**
     * Adds a new manager for the current Employee.
     * @param new_manager
     */
    public void addManager(Employee new_manager) {
        managers.add(new_manager);

        notifyObservers();
    }

    /**
     * Adds a new subordinate for the current Employee.
     * @param new_subordinate
     */
    public void addSubordinate(Employee new_subordinate) {
        subordinates.add(new_subordinate);

        notifyObservers();
    }

    /**
     * Inserts the manager between this employee and their managers.
     * Clears the current managers.
     * @param manager
     */
    public void addAbove(Employee manager) {
        managers.clear();
        managers.add(manager);

        notifyObservers();
    }

    /**
     * Inserts the subordinate between this employee and their subordinates.
     * Clears the current subordinates.
     * @param subordinate
     */
    public void addBelow(Employee subordinate) {
        subordinates.clear();
        subordinates.add(subordinate);

        notifyObservers();
    }
}

package pions.model;

import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import pions.model.ContactInfo.EmailAddress;
import pions.model.ModelException.NotLoggedInException;

/**
 *
 * @author George
 */
public class Employee extends Login implements Serializable {
    Gmail gmail = Gmail.getInstance();
    private final static String AVAILABILITY = "PIONS Availability";
    private final static String WORK_SCHEDULE = "PIONS Work Schedule";
    private final static String SUBORDINATE_SCHEDULE = "PIONS Subordinate Schedule";
    private byte[] RSA_keys = null;
    private String name = "";
    private String display_name = null;
    private ContactInfo contact_info;
    private CalendarCollection availability;
    private CalendarCollection work_schedule;
    private CalendarCollection subordinate_schedule;
    private ArrayList<Position> positions = new ArrayList<Position>();
    private ArrayList<Employee> managers = new ArrayList<Employee>();
    private ArrayList<Employee> subordinates = new ArrayList<Employee>();

    public String getName(){
        return name;
    }

    /**
     * Get display name
     * @return
     */
    public String getDisplayName() throws NotLoggedInException {
        validate();

        if(display_name != null){
            return display_name;
        }
        else{
            return getUsername();
        }
    }

    /**
     * Set display name
     * @param display_name
     * @throws pions.model.ModelException.NotLoggedInException
     */
    public void setDisplayName(String display_name) throws NotLoggedInException {
        validate();

        this.display_name = display_name;
    }

    @Override
    public void generateRSAKeys() throws NotLoggedInException,
            NoSuchAlgorithmException, IOException {
        super.generateRSAKeys();

        RSA_keys = super.getRSAKeys();
    }

    public Gmail getGmail(){
        return gmail;
    }

    public ContactInfo getContactInfo(){
        if(contact_info == null) contact_info = new ContactInfo();
        return contact_info;
    }

    private CalendarCollection initCalendar(String calendar_name){
        try {
            if(gmail.isValid()){
                    return new CalendarCollection(calendar_name, true);
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
            return new CalendarCollection(calendar_name);
        }
    }

    public CalendarCollection getAvailability(){
        if(availability == null){
            initCalendar(AVAILABILITY);
        }
        
        return availability;
    }

    public CalendarCollection getWorkSchedule(){
        if(work_schedule == null){
            initCalendar(WORK_SCHEDULE);
        }
        
        return work_schedule;
    }

    public CalendarCollection getSubordinateSchedule(){
        if(subordinate_schedule == null){
            initCalendar(SUBORDINATE_SCHEDULE);
        }

        return subordinate_schedule;
    }

    protected Employee(String name, String username, String password){
        super(username, password);

        this.name = name;
    }

    public void initGoogle(EmailAddress gmail_username, String gmail_password) {
        gmail.setGmail(gmail_username, gmail_password);
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

    private ArrayList<String> getNames(ArrayList<Employee> employees){
        ArrayList<String> names = new ArrayList<String>();

        for(Employee employee: employees){
            names.add(employee.getName());
        }

        return names;
    }

    public ArrayList<String> getManagerNames(){
        return (ArrayList<String>)getNames(managers);
    }

    public ArrayList<String> getSubordinateNames(){
        return (ArrayList<String>)getNames(subordinates);
    }

    private ArrayList<EmailAddress> getGmails(ArrayList<Employee> employees){
        ArrayList<EmailAddress> gmail_addresses = new ArrayList<EmailAddress>();

        for(Employee employee: employees){
            gmail_addresses.add(employee.getGmail().getGmailAddress());
        }

        return gmail_addresses;
    }

    public ArrayList<EmailAddress> getManagerGmails(){
        return (ArrayList<EmailAddress>)getGmails(managers).clone();
    }

    public ArrayList<EmailAddress> getSubordinateGmails(){
        return (ArrayList<EmailAddress>)getGmails(subordinates).clone();
    }

    public void removeManager(int index){
        Employee manager = managers.remove(index);

        manager.notifyObservers();
        manager.deleteObservers();
        notifyObservers();
    }

    public void removeSubordinate(int index){
        Employee subordinate = subordinates.remove(index);

        subordinate.notifyObservers();
        subordinate.deleteObservers();
        notifyObservers();
    }

    /**
     * Adds a new manager for the current Employee.
     * @param new_manager
     */
    private void addManager(Employee new_manager) {
        managers.add(new_manager);
    }

    /**
     * Adds a new subordinate for the current Employee.
     * @param new_subordinate
     */
    private void addSubordinate(Employee new_subordinate) {
        subordinates.add(new_subordinate);
    }

    /**
     * Inserts the manager between this employee and their managers.
     * Clears the current managers.
     * @param manager
     */
    public void addAbove(Employee manager) {
        managers.clear();
        addManager(manager);
    }

    /**
     * Inserts the subordinate between this employee and their subordinates.
     * Clears the current subordinates.
     * @param subordinate
     */
    public void addBelow(Employee subordinate) {
        subordinates.clear();
        addSubordinate(subordinate);
    }
}

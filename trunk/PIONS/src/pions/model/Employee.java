
package pions.model;

import java.io.IOException;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import pions.model.Alert.AlertType;
import pions.model.ContactInfo.EmailAddress;
import pions.model.ModelException.AlertClassException;
import pions.model.ModelException.NotLoggedInException;

/**
 *
 * @author George
 */
public class Employee extends Login implements Serializable, AbstractAlert {
    private Gmail gmail = Gmail.getInstance();
    private Calendars calendars = new Calendars();
    private byte[] RSA_keys = null;
    private String name = "";
    private String display_name = null;
    private ContactInfo contact_info;
    private Positions positions;
    private ArrayList<Employee> managers = new ArrayList<Employee>();
    private ArrayList<Employee> subordinates = new ArrayList<Employee>();
    private Contacts contacts;

    //Unable to change name after Employee creation. Solve and implement Observer.
    public Calendars getCalendars(){
        return calendars;
    }

    //Unable to change name after Employee creation. Solve and implement Observer.
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

    public byte[] encryptRSA(Object object)
            throws IOException, NotLoggedInException, StreamCorruptedException,
            ClassNotFoundException {
        return super.encryptRSA(RSA_keys, object);
    }

    public void initRSAKeys() throws NotLoggedInException,
            NoSuchAlgorithmException, IOException {
        RSA_keys = super.generateRSAKeys();
    }

    public Gmail getGmail(){
        return gmail;
    }

    public ContactInfo getContactInfo(){
        if(contact_info == null) contact_info = new ContactInfo();
        return contact_info;
    }

    protected Employee(String name, String username, String password){
        super(username, password);

        this.name = name;
    }
    
    public Positions getPositions(){
        return positions;
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

    public Employee removeManager(int index){
        return managers.remove(index);
    }

    public Employee removeSubordinate(int index){
        return subordinates.remove(index);
    }

    /**
     * Adds a new manager for the current Employee.
     * @param new_manager
     */
    public void addManager(Employee new_manager) {
        managers.add(new_manager);
    }

    /**
     * Adds a new subordinate for the current Employee.
     * @param new_subordinate
     */
    public void addSubordinate(Employee new_subordinate) {
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

    public void acceptAlert(AlertType type) throws NotLoggedInException, AlertClassException {
        switch(type){
            case AddManager:
                EmployeeSingleton.getInstance().addManager(this);
                break;
            case AddSubordinate:
                EmployeeSingleton.getInstance().addSubordinate(this);
                break;
            default:
                throw new AlertClassException(this.getClass(), type.getAssociatedClass());
        }
    }

    public void rejectAlert(AlertType type) throws AlertClassException {
        switch(type){
            case AddManager:
                //DONOTHING
                break;
            case AddSubordinate:
                //DONOTHING
                break;
            default:
                throw new AlertClassException(this.getClass(), type.getAssociatedClass());
        }
    }

    public void ignoreAlert(AlertType type) throws AlertClassException {
        switch(type){
            case AddManager:
                //DONOTHING
                break;
            case AddSubordinate:
                //DONOTHING
                break;
            default:
                throw new AlertClassException(this.getClass(), type.getAssociatedClass());
        }
    }
}

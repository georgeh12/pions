
package pions.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.ArrayList;
import pions.model.Alert.AlertType;
import pions.model.ContactInfo.EmailAddress;
import pions.model.Contacts.Contact;
import pions.model.ModelException.AlertClassException;
import pions.model.ModelException.NotLoggedInException;

/**
 *
 * @author George
 */
public class Employee extends Login implements Serializable, AbstractAlert {
    private Gmail gmail = null;
    private Calendars calendars = new Calendars();
    private Contacts contacts = new Contacts();
    private KeyPair key_pair = null;
    private String name = "";
    private String display_name = null;
    private ContactInfo contact_info;
    private Positions positions;
    private Employee manager = null;
    private ArrayList<Employee> subordinates = new ArrayList<Employee>();

    public Calendars getCalendars(){
        return calendars;
    }

    public void setGmail(EmailAddress gmail_address, String gmail_password){
        gmail = new Gmail(gmail_address, gmail_password);
    }

    public Contacts getContacts(){
        return contacts;
    }

    //Unable to change name after Employee creation
    public String getName(){
        return name;
    }

    /**
     * Get display name
     * @return
     */
    public String getDisplayName() {
        return display_name;
    }

    public byte[] encryptRSA(Object object)
            throws IOException, StreamCorruptedException,
            ClassNotFoundException {
        return super.encryptRSA(key_pair.getPrivate(), object);
    }

    public Gmail getGmail(){
        return gmail;
    }

    public Contact getContact() throws NoSuchAlgorithmException, IOException{
        if(key_pair == null) super.generateRSAKeys();
        return new Contact(getPublicKey(), getGmail().getGmailAddress());
    }

    public ContactInfo getContactInfo(){
        if(contact_info == null) contact_info = new ContactInfo();
        return contact_info;
    }

    protected Employee(String name, String username, String password)
            throws FileNotFoundException, StreamCorruptedException,
            ClassNotFoundException, IOException{
        super.init(username, password);
        
        this.name = name;
    }
    
    public Positions getPositions(){
        return positions;
    }

    public Employee getManager(){
        return manager;
    }

    public Employee getSubordinate(int index){
        return subordinates.get(index);
    }

    public String getManagerNames(){
        return manager.getName();
    }

    public ArrayList<String> getSubordinateNames(){
        ArrayList<String> names = new ArrayList<String>();

        for(Employee employee: subordinates){
            names.add(employee.getName());
        }

        return names;
    }

    public PublicKey getPublicKey(){
        return key_pair.getPublic();
    }

    public PublicKey getPublicKey(String gmail_address){
        return searchSubordinates(gmail_address).getPublicKey();
    }

    private boolean hasGmailAddress(String gmail_address){
        return gmail.getGmailAddress().equals(gmail_address);
    }

    private Employee searchSubordinates(String gmail_address){
        if(manager.hasGmailAddress(gmail_address)) return manager;

        for(Employee employee: subordinates){
            if(employee.hasGmailAddress(gmail_address)) return employee;
        }

        return null;
    }

    public EmailAddress getManagerGmail(){
        return manager.getGmail().getGmailAddress();
    }

    public ArrayList<EmailAddress> getSubordinateGmails(){
        ArrayList<EmailAddress> gmail_addresses = new ArrayList<EmailAddress>();

        for(Employee employee: subordinates){
            gmail_addresses.add(employee.getGmail().getGmailAddress());
        }

        return (ArrayList<EmailAddress>)gmail_addresses.clone();
    }

    public void removeManager(){
        manager = null;
    }

    public Employee removeSubordinate(int index){
        return subordinates.remove(index);
    }

    /**
     * Adds a new manager for the current Employee.
     * @param new_manager
     */
    public void addManager(Employee new_manager) {
        manager = new_manager;
    }

    /**
     * Adds a new subordinate for the current Employee.
     * @param new_subordinate
     */
    public void addSubordinate(Employee new_subordinate) {
        subordinates.add(new_subordinate);
    }

    public void acceptAlert(AlertType type, EmailAddress sender)
            throws NotLoggedInException, AlertClassException {
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

    //TODO getDetails()
    public String getDetails() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

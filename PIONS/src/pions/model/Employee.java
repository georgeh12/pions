
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
 * 
 */
public class Employee extends Login implements Serializable, AbstractAlert {
    private String name = "";
    private Positions positions;
    private ContactInfo contact_info;
    private Gmail gmail = null;
    private Calendars calendars = new Calendars();
    private Contacts contacts = new Contacts();
    private KeyPair key_pair = null;
    private Employee manager = null;
    private ArrayList<Employee> subordinates = new ArrayList<Employee>();

    protected Employee(String name, String username, String password)
            throws FileNotFoundException, StreamCorruptedException,
            ClassNotFoundException, IOException{
        super.init(username, password);

        this.name = name;
    }

    /**
     * Gets the name String.
     */
    public String getName(){
        return name;
    }

    public Positions getPositions(){
        return positions;
    }

    public ContactInfo getContactInfo(){
        if(contact_info == null) contact_info = new ContactInfo();
        return contact_info;
    }

    public Gmail getGmail(){
        return gmail;
    }

    public Calendars getCalendars(){
        return calendars;
    }

    public Contacts getContacts(){
        return contacts;
    }

    public Contact getContact() throws NoSuchAlgorithmException, IOException{
        if(key_pair == null) key_pair = super.generateRSAKeys();
        return new Contact(getPublicKey(), getGmail().getGmailAddress());
    }

    private PublicKey getPublicKey(){
        return key_pair.getPublic();
    }

    public PublicKey getPublicKey(String gmail_address){
        Employee target = searchEmployees(gmail_address);

        //Check null to prevent Throwing a NullPointerException
        if(target == null){
            return null;
        }
        else{
            return target.getPublicKey();
        }
    }

    public Employee getManager(){
        return manager;
    }

    public Employee getSubordinate(int index){
        return subordinates.get(index);
    }

    public String getManagerName(){
        return manager.getName();
    }

    public ArrayList<String> getSubordinateNames(){
        ArrayList<String> names = new ArrayList<String>();

        for(Employee employee: subordinates){
            names.add(employee.getName());
        }

        return names;
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

    private boolean hasGmailAddress(String gmail_address){
        return gmail.getGmailAddress().equals(gmail_address);
    }

    private Employee searchEmployees(String gmail_address){
        if(manager.hasGmailAddress(gmail_address)) return manager;

        for(Employee employee: subordinates){
            if(employee.hasGmailAddress(gmail_address)) return employee;
        }

        return null;
    }

    public void setName(String name){
        this.name = name;
        gmail.setGmailAddressPersonal(name);
    }

    public void setGmail(EmailAddress gmail_address, String gmail_password){
        gmail = new Gmail(gmail_address, gmail_password);
    }

    public byte[] encryptRSA(Object object)
            throws IOException, StreamCorruptedException,
            ClassNotFoundException {
        return super.encryptRSA(key_pair.getPrivate(), object);
    }

    /**
     * Adds a new manager for the current Employee.
     * @param new_manager
     */
    protected void addManager(Employee new_manager) {
        manager = new_manager;
    }

    /**
     * Adds a new subordinate for the current Employee.
     * @param new_subordinate
     */
    protected void addSubordinate(Employee new_subordinate) {
        subordinates.add(new_subordinate);
    }

    /**
     * Unimplemented function.
     */
    protected void removeManager(){
        manager = null;
    }

    /**
     * Unimplemented function.
     * @param index
     * @return
     */
    protected Employee removeSubordinate(int index){
        return subordinates.remove(index);
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

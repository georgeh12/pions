
package pions.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Iterator;
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
    private Positions positions = null;
    private ContactInfo contact_info = null;
    private Gmail gmail = null;
    private Calendars calendars = new Calendars();
    private Contacts contacts = new Contacts();
    private KeyPair key_pair = null;
    private Employee manager = null;
    private ArrayList<Employee> subordinates = new ArrayList<Employee>();

    protected Employee(String name, String username, String password,
            String gmail_address, String gmail_password)
            throws FileNotFoundException, StreamCorruptedException,
            ClassNotFoundException, IOException{
        super.init(username, password);

        this.name = name;
        setGmail(new EmailAddress(gmail_address, name), gmail_password);
    }

    /**
     * Gets the name String.
     */
    public String getName(){
        return name;
    }

    public Positions getPositions(){
        if(positions == null) positions = new Positions();
        return positions;
    }

    public ContactInfo getContactInfo(){
        if(contact_info == null) contact_info = new ContactInfo();
        return contact_info;
    }

    public Gmail getGmail(){
        return gmail;
    }

    private void setGmail(EmailAddress gmail_address, String gmail_password){
        gmail = new Gmail(gmail_address, gmail_password);
    }

    public Calendars getCalendars(){
        return calendars;
    }

    public Contacts getContacts(){
        return contacts;
    }

    public Contact getContact() throws NoSuchAlgorithmException, IOException{
        if(key_pair == null) key_pair = super.generateRSAKeys();
        return new Contact(getPublicKey(), getGmailAddress());
    }

    private PublicKey getPublicKey(){
        return key_pair.getPublic();
    }

    public PublicKey getPublicKey(String gmail_address){
        Contact contact = getContacts().searchContacts(gmail_address);

        if(contact == null){
            return null;
        }
        else{
            return contact.getPublicKey();
        }
    }

    public Employee getManager(){
        return manager;
    }

    public Employee getSubordinate(int index){
        return subordinates.get(index);
    }

    public ArrayList<String> getSubordinateNames(){
        ArrayList<String> names = new ArrayList<String>();

        for(Employee employee: subordinates){
            names.add(employee.getName());
        }

        return names;
    }

    public EmailAddress getManagerGmail(){
        return getGmailAddress(manager);
    }

    public ArrayList<EmailAddress> getSubordinateGmails(){
        ArrayList<EmailAddress> gmail_addresses = new ArrayList<EmailAddress>();

        for(Employee employee: subordinates){
            gmail_addresses.add(getGmailAddress(employee));
        }

        return (ArrayList<EmailAddress>)gmail_addresses.clone();
    }

    private boolean hasGmailAddress(String gmail_address){
        return getGmailAddress().equals(gmail_address);
    }

    public void setName(String name){
        this.name = name;
        gmail.setGmailAddressPersonal(name);
    }

    /**
     * Adds a new manager for the current Employee.
     * @param new_manager
     */
    protected final void addManager(Employee new_manager) {
        int index = compareSubordinate(getGmailAddress(new_manager).getAddress());
        if(index != -1) subordinates.remove(index);

        manager = new_manager;
    }

    /**
     * Adds a new subordinate for the current Employee. If the gmail address
     * is equivalent to a current subordinate's gmail address, it sets the
     * new subordinate to that index.
     * @param new_subordinate
     */
    protected final void addSubordinate(Employee new_subordinate) {
        int index = compareSubordinate(getGmailAddress(new_subordinate).getAddress());
        if(index == -1){
            subordinates.add(new_subordinate);
            
            if(manager != null && manager.hasGmailAddress(
                    getGmailAddress(new_subordinate).getAddress())){
                manager = null;
            }
        }
        else {
            subordinates.set(index, new_subordinate);
        }
    }

    private EmailAddress getGmailAddress(){
        return getGmailAddress(this);
    }

    private EmailAddress getGmailAddress(Employee employee){
        return employee.getGmail().getGmailAddress();
    }

    private int compareSubordinate(String gmail){
        for(int i = 0; i < subordinates.size(); i++){
            if(subordinates.get(i).hasGmailAddress(gmail)){
                return i;
            }
        }

        return -1;
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

    public void acceptAlert(AlertType type)
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

    public String getDetails() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("Name: " + getName());
        buffer.append('\n');
        buffer.append(getPositions().toString());
        buffer.append('\n');
        buffer.append(getContactInfo().toString());
        buffer.append('\n');
        buffer.append("Gmail Address: " + getGmailAddress());
        buffer.append('\n');

        buffer.append("Manager: ");
        if(getManager() == null){
            buffer.append("none");
        }
        else{
            buffer.append(getManager().getName());
        }
        buffer.append('\n');
        
        buffer.append("Subordinates: ");
        Iterator<String> iter = getSubordinateNames().iterator();
        if(iter.hasNext()){
            buffer.append(iter.next());
        }
        else{
            buffer.append("none");
        }
        while(iter.hasNext()){
            buffer.append(", " + iter.next());
        }

        return buffer.toString();
    }
}


package pions.controller;

import java.util.ArrayList;
import pions.model.ContactInfo.Address;
import pions.model.ContactInfo.Address.State;
import pions.model.ContactInfo.EmailAddress;
import pions.model.ContactInfo.PhoneNumber;
import pions.model.EmployeeSingleton;
import pions.model.ModelException.NotLoggedInException;

/**
 * This class does not support multi-threaded applications.
 * 
 */
//TODO implement XML (it's already made), ie. universal set and get method
public final class ContactInfo {
    private static ArrayList<EmailAddress> cached_emailaddresses = null;
    private static ArrayList<PhoneNumber> cached_phonenumbers = null;
    private static Address cached_address = null;
    private static String cached_name = null;

    /**
     * This must be called to initialize the cached variables.
     */
    public static void initCache(){
        try {
            cached_name = EmployeeSingleton.getInstance().getName();
            cached_emailaddresses = EmployeeSingleton.getInstance().getContactInfo().getEmailAddresses();
            cached_phonenumbers = EmployeeSingleton.getInstance().getContactInfo().getPhoneNumbers();
            cached_address = EmployeeSingleton.getInstance().getContactInfo().getAddress();
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }
    }

    public static void commit(){
        try {
            EmployeeSingleton.getInstance().setName(cached_name);
            EmployeeSingleton.getInstance().getContactInfo().setEmailAddress(cached_emailaddresses);
            EmployeeSingleton.getInstance().getContactInfo().setPhoneNumbers(cached_phonenumbers);
            EmployeeSingleton.getInstance().getContactInfo().setAddress(cached_address);
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }
    }

    public static String getName(){
        return cached_name;
    }

    public static void setName(String name){
        cached_name = name;
    }

    public static void addEmailAddress(String name, String domain) {
        cached_emailaddresses.add(new EmailAddress(name, domain));
    }

    public static ArrayList<String> getEmailAddresses(){
        ArrayList<String> email_addresses = new ArrayList<String>();
        for(EmailAddress email_address: cached_emailaddresses){
            email_addresses.add(email_address.toString());
        }
        return email_addresses;
    }

    public static void setEmailAddress(int index, String name, String domain){
        cached_emailaddresses.set(index, new EmailAddress(name, domain));
    }

    public static void removeEmailAddress(int index){
        cached_emailaddresses.remove(index);
    }

    public static String getEmail(int index){
        return cached_emailaddresses.get(index).getAddress();
    }

    public static String getPersonal(int index){
        return cached_emailaddresses.get(index).getPersonal();
    }


    public static void addPhoneNumber(String type, String number, String extension){
        cached_phonenumbers.add(new PhoneNumber(PhoneNumber.PhoneType.valueOf(type),
                PhoneNumber.formatPhoneNumber(number),
                PhoneNumber.formatExt(extension)));
    }

    public static ArrayList<String> getPhoneNumbers(){
        ArrayList<String> phone_numbers = new ArrayList<String>();
        for(PhoneNumber phone_number: cached_phonenumbers){
            phone_numbers.add(phone_number.toString());
        }
        return phone_numbers;
    }

    public static void setPhoneNumber(int index, String type,
            String number, String extension){
        cached_phonenumbers.set(index,
                new PhoneNumber(PhoneNumber.PhoneType.valueOf(type),
                PhoneNumber.formatPhoneNumber(number),
                PhoneNumber.formatExt(extension)));
    }

    public static void removePhoneNumber(int index){
        cached_phonenumbers.remove(index);
    }

    public static String getPhoneType(int index){
        return cached_phonenumbers.get(index).getType().toString();
    }

    public static String getPhoneNumber(int index){
        return cached_phonenumbers.get(index).toStringPhone();
    }

    public static String getPhoneExtension(int index){
        return cached_phonenumbers.get(index).toStringExtension();
    }

    public static void setAddress(String street_address,
            String city, String state, String zip){
        cached_address = new Address(street_address,
                city,
                Address.State.valueOf(state),
                Address.parseZip(zip));
    }

    public static String getStreet(){
        return cached_address.getStreet();
    }

    public static String getCity(){
        return cached_address.getCity();
    }

    public static String getZip(){
        return cached_address.getZip();
    }

    public static int getState(){
        return cached_address.getState();
    }

    public static ArrayList<String> getStates(){
        ArrayList<String> states = new ArrayList<String>();

        for(State state: pions.model.ContactInfo.Address.State.values()){
            states.add(state.toAbbrev());
        }

        return states;
    }
}


package pions.controller;

import java.util.ArrayList;
import pions.model.ContactInfo.Address;
import pions.model.ContactInfo.EmailAddress;
import pions.model.ContactInfo.PhoneNumber;
import pions.model.EmployeeSingleton;
import pions.model.ModelException.NotLoggedInException;

/**
 *
 * @author George
 */
public class ContactInfo {
    public static void addEmailAddress(String name, String domain) {
        try{
            EmployeeSingleton.getInstance().getContactInfo().addEmailAddress(name, domain);
        } catch (NotLoggedInException e){
            e.printStackTrace();
        }
    }

    public static void setEmailAddress(int index, String name, String domain){
        try{
            EmployeeSingleton.getInstance().getContactInfo().setEmailAddress(index, name, domain);
        } catch (NotLoggedInException e){
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getEmailAddresses(){
        ArrayList<String> email_addresses = new ArrayList<String>();

        try{
            for(EmailAddress email_address: EmployeeSingleton.getInstance().getContactInfo().getEmailAddresses()){
                email_addresses.add(email_address.toString());
            }
        } catch (NotLoggedInException e){
            e.printStackTrace();
        } finally {
            return email_addresses;
        }
    }

    public static boolean removeEmailAddress(int index){
        try {
            EmployeeSingleton.getInstance().getContactInfo().removeEmailAddress(index);
            return true;
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }

        return false;
    }


    public static void addPhoneNumber(PhoneNumber.PhoneType type,
            long number, int extension){
        try{
            EmployeeSingleton.getInstance().getContactInfo().addPhoneNumber(type, number, extension);
        } catch (NotLoggedInException e){
            e.printStackTrace();
        }
    }

    public static void setPhoneNumber(int index, PhoneNumber.PhoneType type,
            long number, int extension){
        try{
            EmployeeSingleton.getInstance().getContactInfo().setPhoneNumber(index, type, number, extension);
        } catch (NotLoggedInException e){
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getPhoneNumbers(){
        ArrayList<String> phone_numbers = new ArrayList<String>();

        try{
            for(PhoneNumber phone_number: EmployeeSingleton.getInstance().getContactInfo().getPhoneNumbers()){
                phone_numbers.add(phone_number.toString());
            }
        } catch (NotLoggedInException e){
            e.printStackTrace();
        } finally {
            return phone_numbers;
        }
    }

    public static boolean removePhoneNumber(int index){
        try {
            EmployeeSingleton.getInstance().getContactInfo().removePhoneNumber(index);
            return true;
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void setAddress(String street_address,
            String city, Address.State state, int zip, String country){
        try{
            EmployeeSingleton.getInstance().getContactInfo().setAddress(street_address, city, state, zip, country);
        } catch (NotLoggedInException e){
            e.printStackTrace();
        }
    }
    
    public static String getAddress(){
        try {
            return EmployeeSingleton.getInstance().getContactInfo().getAddress().toString();
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static void removeAddress(){
        try {
            EmployeeSingleton.getInstance().getContactInfo().removeAddress();
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }
    }
}

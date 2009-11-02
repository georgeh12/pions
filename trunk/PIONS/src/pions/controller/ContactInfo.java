
package pions.controller;

import java.util.ArrayList;
import java.util.Observer;
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
    public void addEmailAddress(Observer observer, String name, String domain) {
        try{
            EmployeeSingleton.getInstance().getContactInfo().addEmailAddress(name, domain).addObserver(observer);
        } catch (NotLoggedInException e){
            //TODO exception
            e.printStackTrace();
        }
    }

    public void setEmailAddress(int index, String name, String domain){
        try{
            EmployeeSingleton.getInstance().getContactInfo().setEmailAddress(index, name, domain);
        } catch (NotLoggedInException e){
            //TODO exception
            e.printStackTrace();
        }
    }

    public ArrayList<String> getEmailAddresses(Observer observer){
        ArrayList<String> email_addresses = new ArrayList<String>();

        try{
            for(EmailAddress email_address: EmployeeSingleton.getInstance().getContactInfo().getEmailAddresses()){
                email_addresses.add(email_address.toString());
                email_address.addObserver(observer);
            }
        } catch (NotLoggedInException e){
            //TODO exception
            e.printStackTrace();
        } finally {
            return email_addresses;
        }
    }

    public boolean removeEmailAddress(int index){
        try {
            EmailAddress removed = EmployeeSingleton.getInstance().getContactInfo().removeEmailAddress(index);

            removed.deleteObservers();
            return true;
        } catch (NotLoggedInException e) {
            //TODO exception
            e.printStackTrace();
        } finally {
            return false;
        }
    }


    public void addPhoneNumber(Observer observer, PhoneNumber.PhoneType type,
            long number, int extension){
        try{
            EmployeeSingleton.getInstance().getContactInfo().addPhoneNumber(type, number, extension).addObserver(observer);
        } catch (NotLoggedInException e){
            //TODO exception
            e.printStackTrace();
        }
    }

    public void setPhoneNumber(int index, PhoneNumber.PhoneType type,
            long number, int extension){
        try{
            EmployeeSingleton.getInstance().getContactInfo().setPhoneNumber(index, type, number, extension);
        } catch (NotLoggedInException e){
            //TODO exception
            e.printStackTrace();
        }
    }

    public ArrayList<String> getPhoneNumbers(Observer observer){
        ArrayList<String> phone_numbers = new ArrayList<String>();

        try{
            for(PhoneNumber phone_number: EmployeeSingleton.getInstance().getContactInfo().getPhoneNumbers()){
                phone_numbers.add(phone_number.toString());
                phone_number.addObserver(observer);
            }
        } catch (NotLoggedInException e){
            //TODO exception
            e.printStackTrace();
        } finally {
            return phone_numbers;
        }
    }

    public boolean removePhoneNumber(int index){
        try {
            PhoneNumber removed = EmployeeSingleton.getInstance().getContactInfo().removePhoneNumber(index);

            removed.deleteObservers();
            return true;
        } catch (NotLoggedInException e) {
            //TODO exception
            e.printStackTrace();
        } finally {
            return false;
        }
    }

    public void setAddress(Observer observer, String street_address,
            String city, Address.State state, int zip, String country){
        try{
            EmployeeSingleton.getInstance().getContactInfo().setAddress(street_address, city, state, zip, country).addObserver(observer);
        } catch (NotLoggedInException e){
            //TODO exception
            e.printStackTrace();
        }
    }
    
    public String getAddress(Observer observer){
        Address address;
        try {
            address = EmployeeSingleton.getInstance().getContactInfo().getAddress();
            address.addObserver(observer);

            return address.toString();
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        } finally {
            return "";
        }
    }

    public void removeAddress(){
        try {
            Address removed = EmployeeSingleton.getInstance().getContactInfo().removeAddress();

            removed.deleteObservers();
        } catch (NotLoggedInException e) {
            //TODO exception
            e.printStackTrace();
        }
    }
}

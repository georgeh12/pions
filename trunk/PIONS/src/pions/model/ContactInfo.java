
package pions.model;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Observable;
import javax.mail.internet.InternetAddress;
import pions.model.ModelException.NotLoggedInException;

/**
 *
 * @author George
 */
public class ContactInfo implements Serializable {
    private ArrayList<EmailAddress> email_addresses = new ArrayList<EmailAddress>();
    private ArrayList<PhoneNumber> phone_numbers = new ArrayList<PhoneNumber>();
    private Address address = new Address();

    public EmailAddress addEmailAddress(String name, String domain){
        EmailAddress email_address = new EmailAddress(name, domain);
        email_addresses.add(email_address);

        return email_address;
    }

    public void setEmailAddress(int index, String name, String domain){
        email_addresses.set(index, new EmailAddress(name, domain));
    }

    public ArrayList<EmailAddress> getEmailAddresses(){
        return (ArrayList<EmailAddress>) email_addresses.clone();
    }

    public EmailAddress removeEmailAddress(int index){
        return email_addresses.remove(index);
    }

    public static class EmailAddress extends Observable implements Serializable {
        private String address = "";
        private String personal = "";

        public EmailAddress(String address, String personal){
            this.address = address;
            this.personal = personal;
        }
        
        public EmailAddress(String address) throws NotLoggedInException {
            this(address, EmployeeSingleton.getInstance().getName());
        }

        public InternetAddress getInternetAddress() throws UnsupportedEncodingException{
            return new InternetAddress(address, personal);
        }

        public String getAddress(){
            return address;
        }

        public String getPersonal(){
            return personal;
        }

        public boolean equals(EmailAddress email_address){
            return this.address.compareToIgnoreCase(email_address.getAddress()) == 0;
        }

        @Override
        public String toString(){
            return personal + "<" + address + ">";
        }

        @Override
        public EmailAddress clone(){
            return new EmailAddress(address, personal);
        }
    }

    public PhoneNumber addPhoneNumber(PhoneNumber.PhoneType type,
            long number, int extension){
        PhoneNumber phone_number = new PhoneNumber(type, number, extension);
        phone_numbers.add(phone_number);

        return phone_number;
    }

    public void setPhoneNumber(int index, PhoneNumber.PhoneType type,
            long number, int extension){
        phone_numbers.set(index, new PhoneNumber(type, number, extension));
    }

    public ArrayList<PhoneNumber> getPhoneNumbers(){
        return (ArrayList<PhoneNumber>) phone_numbers.clone();
    }

    public PhoneNumber removePhoneNumber(int index){
        return phone_numbers.remove(index);
    }

    public static class PhoneNumber extends Observable implements Serializable {
        private PhoneType type = PhoneType.Home;
        private long number = 0;
        private int extension = 0;

        public PhoneNumber(PhoneType type, long number, int extension){
            this.type = type;
            this.number = number;
            this.extension = extension;
        }

        private static String getDigits(String number_string){
            String new_string = "";

            for(int i = 0; i < number_string.length(); i ++){
                if(Character.isDigit(number_string.charAt(i))
                        && number_string.charAt(i) != '-'){
                    new_string += number_string.charAt(i);
                }
            }
            return new_string;
        }

        public void formatPhoneNumber(String number_string){
            String new_string = "";

            new_string = getDigits(number_string);

            if(new_string.length() == 0){
                new_string = "0";
            }
            else if(new_string.length() > 10){
                new_string = new_string.substring(0, 10);
            }

            number = Long.parseLong(new_string);
        }

        public void formatExt(String number_string){
            String new_string = getDigits(number_string);

            if(new_string.length() == 0){
                new_string = "0";
            }
            else if(new_string.length() > 7){
                new_string = new_string.substring(0, 7);
            }

            extension = Integer.parseInt(new_string);
        }

        public PhoneType getType(){
            return type;
        }

        public String toStringPhone(){
            String number_string = Long.toString(number);

            while(number_string.length() < 10){
                number_string = "0" + number_string;
            }

            return number_string.substring(0, 3) + "-" + number_string.substring(3, 6) +
                    "-" + number_string.substring(6);
        }

        public String toStringExtension(){
            return (extension == 0 ? "" : "" + extension);
        }

        /**
         * Returns a string containing the phone number followed by
         * ' x' + extension.
         * @return
         */
        @Override
        public String toString(){
            return toStringPhone() +
                    (toStringExtension().isEmpty() ? "" : " x" + toStringExtension());
        }

        /**
         * True if the PhoneNumbers have the same type.
         * @param contact
         * @return
         */
        public boolean equals(PhoneNumber contact){
            return this.type.equals(contact.type);
        }

        public static enum PhoneType {
            Home, Cell, Work, Fax, Other;
            @Override
            public String toString(){
                if(this.equals(Home)){
                    return "Home";
                }
                else if(this.equals(Cell)){
                    return "Cell";
                }
                else if(this.equals(Work)){
                    return "Work";
                }
                else if(this.equals(Fax)){
                    return "Fax";
                }

                return "Other";
            }
        }
    }

    public Address setAddress(String street_address, String city, Address.State state,
            int zip, String country){
        address = new Address(street_address, city, state, zip, country);

        return address;
    }

    /**
     * Returns a clone of address.
     * @return
     */
    public Address getAddress(){
        return address.clone();
    }

    public Address removeAddress(){
        Address temp = address;
        address = new Address();
        return temp;
    }

    public static class Address extends Observable implements Serializable {
        private String street_address = "";
        private String city = "";
        private State state = State.None;
        private int zip = 0;
        private String country = "";

        public Address() { }

        public Address(String street_address, String city, Address.State state,
            int zip, String country){
            this.street_address = street_address;
            this.city = city;
            this.state = state;
            this.zip = zip;
            this.country = country;
        }

        /**
         * Contains every state in the USA!
         */
        public static enum State{
            AL("Alabama"), AK("Alaska"), AZ("Arizona"), AR("Arkansas"),
            CA("California"), CO("Colorado"), CT("Connecticut"), DE("Delaware"),
            DC("District of Columbia"), FL("Florida"), GA("Georgia"),
            HI("Hawaii"), ID("Idaho"), IL("Illinois"), IN("Indiana"),
            IA("Iowa"), KS("Kansas"), KY("Kentucky"), LA("Louisiana"),
            ME("Maine"), MD("Maryland"), MA("Massachusetts"), MI("Michigan"),
            MN("Minnesota"), MS("Mississippi"), MO("Missouri"), MT("Montana"),
            NE("Nebraska"), NV("Nevada"), NH("New Hampshire"), NJ("New Jersey"),
            NM("New Mexico"), NY("New York"), NC("North Carolina"),
            ND("North Dakota"), OH("Ohio"), OK("Oklahoma"), OR("Oregon"),
            PA("Pennsylvania"), RI("Rhode Island"), SC("South Carolina"),
            SD("South Dakota"), TN("Tennessee"), TX("Texas"), UT("Utah"),
            VT("Vermont"), VA("Virginia"), WA("Washington"),
            WV("West Virginia"), WI("Wisconsin"), WY("Wyoming"),
            Other("Outside USA"), None("");

            private String state;
            
            State(String state){
                this.state = state;
            }

            public String toAbbrev(){
                return name();
            }

            @Override
            public String toString(){
                return state;
            }
        }

        @Override
        public String toString(){
            return street_address + "\n" +
                    city +
                    (state != State.None ? ", " + state : "") +
                    (zip != 0 ? " " + zip : "") +
                    (country.length() > 0 ? " " + country : "");
        }

        @Override
        public Address clone(){
            return new Address(street_address, city, state, zip, country);
        }
    }
}

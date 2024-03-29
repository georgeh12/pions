
package pions.model;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import javax.mail.internet.InternetAddress;

/**
 *
 * 
 */
public class ContactInfo implements Serializable {
    private ArrayList<EmailAddress> email_addresses = new ArrayList<EmailAddress>();
    private ArrayList<PhoneNumber> phone_numbers = new ArrayList<PhoneNumber>();
    private Address address = new Address();

    public ArrayList<EmailAddress> getEmailAddresses(){
        return (ArrayList<EmailAddress>) email_addresses.clone();
    }

    public ArrayList<PhoneNumber> getPhoneNumbers(){
        return (ArrayList<PhoneNumber>) phone_numbers.clone();
    }

    /**
     * Returns a clone of address.
     * @return
     */
    public Address getAddress(){
        return address.clone();
    }

    public void setEmailAddresses(ArrayList<EmailAddress> email_addresses){
        this.email_addresses = email_addresses;
    }

    public void setPhoneNumbers(ArrayList<PhoneNumber> phone_numbers){
        this.phone_numbers = phone_numbers;
    }

    public void setAddress(Address address){
        this.address = address;
    }

    @Override
    public String toString(){
        StringBuffer buffer = new StringBuffer();

        buffer.append("Email Address(es): " + email_addresses.toString()
                .replace("[", "").replace("]", ""));

        buffer.append("\nPhone Number(s): " + phone_numbers.toString()
                .replace("[", "").replace("]", ""));

        buffer.append("\nAddress: " + address.toString());

        return buffer.toString();
    }

    public static class EmailAddress implements Serializable {
        private String address = "";
        private String personal = "";

        public EmailAddress(String address, String personal){
            this.address = address;
            this.personal = personal;
        }
        
        public EmailAddress(String address) {
            this(address, "");
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

        public void setPersonal(String personal){
            this.personal = personal;
        }

        public boolean equals(EmailAddress email_address){
            return this.equals(email_address.address);
        }

        public boolean equals(String email_address){
            return this.address.compareToIgnoreCase(email_address) == 0;
        }

        @Override
        public String toString(){
            return "<" + address + ">" + " " + personal;
        }

        @Override
        public EmailAddress clone(){
            return new EmailAddress(address, personal);
        }
    }

    public static class PhoneNumber implements Serializable {
        private PhoneType type = null;
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

        public static long formatPhoneNumber(String number_string){
            String new_string = "";

            new_string = getDigits(number_string);

            if(new_string.length() == 0){
                new_string = "0";
            }
            else if(new_string.length() > 10){
                new_string = new_string.substring(0, 10);
            }

            return Long.parseLong(new_string);
        }

        public static int formatExtension(String number_string){
            String new_string = getDigits(number_string);

            if(new_string.length() == 0){
                new_string = "0";
            }
            else if(new_string.length() > 7){
                new_string = new_string.substring(0, 7);
            }

            return Integer.parseInt(new_string);
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
         * Returns a string containing the type, then phone number followed by
         * ' x' + extension.
         * @return
         */
        @Override
        public String toString(){
            return type + ": " + toStringPhone() +
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

        @Override
        public PhoneNumber clone(){
            return new PhoneNumber(type, number, extension);
        }

        public static enum PhoneType {
            Home("Home"), Cell("Cell"), Work("Work"), Fax("Fax");

            String type = null;
            PhoneType(String type){
                this.type = type;
            }

            @Override
            public String toString(){
                return type;
            }
        }
    }

    public static class Address implements Serializable {
        private String street_address = "";
        private String city = "";
        private State state = State.None;
        private String zip = "";

        private Address() { }

        public Address(String street_address, String city, Address.State state,
            String zip){
            this.street_address = street_address;
            this.city = city;
            this.state = state;
            this.zip = zip;
        }

        public static int parseZip(String zip){
            String digits = "";

            for(int i = 0; i < zip.length(); i ++){
                if(Character.isDigit(zip.charAt(i)) && digits.length() < 5){
                    digits += zip.charAt(i);
                }
            }

            return (digits.length() == 0 ? 0 : Integer.parseInt(digits));
        }

        public String getStreet(){
            return street_address;
        }

        public String getCity(){
            return city;
        }

        public State getState(){
            return state;
        }

        public String getZip(){
            return zip;
        }

        /**
         * Contains every state in the USA!
         */
        public static enum State{
            None(), AL("Alabama"), AK("Alaska"), AZ("Arizona"), AR("Arkansas"),
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
            Other("Outside USA");

            private String state;

            State(){
                this("");
            }
            
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
                    (state != State.None ? ", " + state : "") + " " +
                    zip;
        }

        @Override
        public Address clone(){
            return new Address(street_address, city, state, zip);
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pions.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author George
 */
public class ContactInfo implements Serializable {
    String email;
    ArrayList<PhoneNumber> phone_numbers = new ArrayList<PhoneNumber>();
    Address address = new Address();

    public static class PhoneNumber implements Serializable {
        private PhoneType type = PhoneType.Home;
        private long number = 0;
        private int extension = 0;

        public PhoneNumber(PhoneType type, long number){
            this.type = type;
            this.number = number;
        }

        public PhoneNumber(PhoneType type, long number, int extension){
            this(type, number);
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

        public static long format(String number_string){
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

        public static int formatExt(String number_string){
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

        public String toPhoneString(){
            String number_string = Long.toString(number);

            while(number_string.length() < 10){
                number_string = "0" + number_string;
            }

            return number_string.substring(0, 3) + "-" + number_string.substring(3, 6) +
                    "-" + number_string.substring(6);
        }

        public String toExtString(){
            return (extension == 0 ? "" : "" + extension);
        }

        public String toString(){
            return toPhoneString() +
                    (toExtString().isEmpty() ? "" : " x" + toExtString());
        }

        public boolean equals(PhoneNumber contact){
            return this.type.equals(contact.type);
        }

        public enum PhoneType {
            Home, Cell, Work, Fax, Other;
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

    public static class Address implements Serializable {
        private String street_address = "";
        private String city = "";
        private State state = State.None;
        private int zip = 0;

        public enum State{
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
            WV("West Virginia"), WI("Wisconsin"), WY("Wyoming"), None("");

            String state;
            
            State(String state){
                this.state = state;
            }

            public String toAbbrev(){
                return state.getClass().toString();
            }

            public String toString(){
                return state;
            }
        }
    }
}

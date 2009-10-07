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
    Address address;

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
        private String street_address;
        private String city;
        public enum State{
            AL("ALABAMA"), AK("ALASKA"), AS("AMERICAN SAMOA");
            String state;

/*
AZ

ARIZONA


AR

ARKANSAS


CA

CALIFORNIA


CO

COLORADO


CT

CONNECTICUT


DE

DELAWARE


DC

DISTRICT OF COLUMBIA


FM

FEDERATED STATES OF MICRONESIA


FL

FLORIDA


GA

GEORGIA


GU

GUAM


HI

HAWAII


ID

IDAHO


IL

ILLINOIS


IN

INDIANA


IA

IOWA


KS

KANSAS


KY

KENTUCKY


LA

LOUISIANA


ME

MAINE

MARSHALL ISLANDS


MD

MARYLAND


MA

MASSACHUSETTS


MI

MICHIGAN


MN

MINNESOTA


MS

MISSISSIPPI


MO

MISSOURI


MT

MONTANA


NE

NEBRASKA


NV

NEVADA


NH

NEW HAMPSHIRE


NJ

NEW JERSEY


NM

NEW MEXICO


NY

NEW YORK


NC

NORTH CAROLINA


ND

NORTH DAKOTA


MP

NORTHERN MARIANA ISLANDS


OH

OHIO


OK

OKLAHOMA


OR

OREGON


PW

PALAU


PA

PENNSYLVANIA


PR

PUERTO RICO


RI

RHODE ISLAND


SC

SOUTH CAROLINA


SD

SOUTH DAKOTA


TN

TENNESSEE


TX

TEXAS


UT

UTAH



VT
VERMONT


VI

VIRGIN ISLANDS



VA
VIRGINIA



WA
WASHINGTON



WV
WEST VIRGINIA


WI

WISCONSIN



WY
WYOMING*/

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


package pions.model;

import pions.model.Alert.AlertType;

/**
 *
 * @author George
 */
public class ModelException extends Exception {
    /**
     * Thrown when an alert is made with conflicting AlertType and
     * class association.
     */
    public static class AlertClassException extends ModelException {
        private Class required;
        private Class found;

        public AlertClassException(Class required, Class found){
            this.required = required;
            this.found = found;
        }

        @Override
        public String getMessage(){
            return "Conflicting class association.\n" +
                    "required: " + required + "\nfound: " + found + ".";
        }
    }

    /**
     * When a calendar cannot be created automatically, this exception is thrown.
     * In order to initialize such a calendar, it must be received via an alert.
     */
    public static class ScheduleNotFoundException extends ModelException {
        private String calendar_name;

        public ScheduleNotFoundException(String calendar_name){
            this.calendar_name = calendar_name;
        }

        @Override
        public String getMessage(){
            return "The calendar \"" + calendar_name + "\" does not exist.";
        }
    }

    /**
     * Thrown when Login has not been validated.
     */
    public static class NotLoggedInException extends ModelException {
        @Override
        public String getMessage(){
            return "User must be logged in first.";
        }
    }

    /**
     * Thrown when Login has not been validated.
     */
    public static class ContactNotFoundException extends ModelException {
        @Override
        public String getMessage(){
            return "Add recipient as a contact before sending requests.";
        }
    }

    /**
     * Used when the message parser receives an error while opening messages.
     * Holds useful information to diagnose the source of the exception.
     */
    public static class MessageParserException extends ModelException {
        private int alert_id;
        private AlertType type;

        MessageParserException(AlertType type, int alert_id){
            this.alert_id = alert_id;
            this.type = type;
        }

        @Override
        public String getMessage(){
            return "Error while parsing alert.\nType: " + type + ", ID: " + alert_id;
        }
    }
}

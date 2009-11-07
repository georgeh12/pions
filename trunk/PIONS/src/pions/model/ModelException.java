
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
     * Thrown when Login has not been validated.
     */
    public static class NotLoggedInException extends ModelException {
        @Override
        public String getMessage(){
            return "User must be logged in first.";
        }
    }

    /**
     * Used when the message parser receives an error while opening messages.
     * Holds useful information to diagnose the source of the exception.
     */
    public static class MessageParserException extends ModelException {
        private int alert_id;
        private AlertType type;

        MessageParserException(int alert_id, AlertType type){
            this.alert_id = alert_id;
            this.type = type;
        }

        @Override
        public String getMessage(){
            return "Error while parsing message: " + alert_id + ", " + type;
        }
    }
}


package pions.model;

import pions.model.alerts.Alert.AlertType;

/**
 *
 * @author George
 */
public class ModelException extends Exception {
    /**
     * Thrown when Login has not been validated.
     */
    public static class NotLoggedInException extends ModelException {
        @Override
        public String toString(){
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
        public String toString(){
            return "error while parsing message: " + alert_id + ", " + type;
        }
    }
}

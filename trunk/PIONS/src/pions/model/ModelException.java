
package pions.model;

import pions.model.alerts.Alert.AlertType;

/**
 *
 * @author George
 */
public class ModelException extends Exception {
    /**
     * This is thrown when Login has not been validated.
     */
    public static class NotLoggedInException extends ModelException { }

    public static class MessageParserException extends ModelException {
        private int alert_id;
        private AlertType type;

        MessageParserException(int alert_id, AlertType type){
            this.alert_id = alert_id;
            this.type = type;
        }

        public String toString(){
            return "error while parsing message: " + alert_id + ", " + type;
        }
    }
}

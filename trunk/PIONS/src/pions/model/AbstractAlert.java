
package pions.model;

import pions.model.ModelException.AlertClassException;
import pions.model.ModelException.NotLoggedInException;

/**
 * Implements the strategy design pattern.
 * @author George
 */
public interface AbstractAlert {
    public void acceptAlert(Alert.AlertType type) throws NotLoggedInException, AlertClassException;
    public void rejectAlert(Alert.AlertType type) throws AlertClassException;
    public void ignoreAlert(Alert.AlertType type) throws AlertClassException;
    public String getDetails();
}

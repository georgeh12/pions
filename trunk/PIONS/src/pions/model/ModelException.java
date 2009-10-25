
package pions.model;

/**
 *
 * @author George
 */
public class ModelException extends Exception {
    /**
     * This is thrown when Login has not been validated.
     */
    public static class NotLoggedInException extends ModelException{}
}

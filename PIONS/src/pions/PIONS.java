
package pions;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;
import pions.view.PIONSView;

/**
 * The main class of the application.
 * @author George
 */
public class PIONS extends SingleFrameApplication {

    /**
     * At startup create and show the main frame of the application.
     */
    @Override
    protected void startup() {
        PIONSView.getInstance();
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of PIONS
     */
    public static PIONS getApplication() {
        return Application.getInstance(PIONS.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        getApplication().startup();
    }
}

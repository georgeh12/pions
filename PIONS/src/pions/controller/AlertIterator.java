
package pions.controller;

import pions.model.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observer;

/**
 * Iterator design implementation.
 * @author George
 */
public class AlertIterator implements Iterator {
    private Iterator<Alert> iter;
    private Alert alert = null;
    private Observer observer;

    public AlertIterator(Iterator<Alert> iter, Observer observer) {
        this.iter = iter;
        this.observer = observer;
    }

    public boolean hasNext() {
        return iter.hasNext();
    }

    /**
     * Each alert returns 3 strings:
     * The first is the toString() method of the EmailAddress
     * The second is the toString() method of the AlertType
     * The third is the toString() method of the alert object
     * @return
     */
    public ArrayList<String> next() {
        ArrayList<String> alerts = new ArrayList<String>();

        alert = iter.next();
        alert.addObserver(observer);

        alerts.add(alert.getAddress().toString());
        alerts.add(alert.getType().toString());
        alerts.add(alert.get().toString());

        return alerts;
    }

    public void remove() {
        throw new UnsupportedOperationException("Not supported.");
    }
}

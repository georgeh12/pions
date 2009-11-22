
package pions.view.alerts;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import pions.controller.AlertIterator;
import pions.view.AbstractIList;

/**
 *
 * @author George
 */
public class AbstractAlertsIList extends AbstractIList {
    protected StringBuffer parseNext(){
        StringBuffer buffer = null;

        if(hasNext()){
            buffer = new StringBuffer();
            Document xml = iter.next();

            Element alert = xml.getElementById(AlertIterator.ALERT);

            buffer.append("Sender: \n");
            buffer.append(alert.getAttribute(AlertIterator.SENDER));

            buffer.append("Type: \n");
            buffer.append(alert.getAttribute(AlertIterator.TYPE));

            buffer.append("Description: \n");
            buffer.append(alert.getAttribute(AlertIterator.DESCRIPTION));
        }

        return buffer;
    }
}

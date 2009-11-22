
package pions.view.alerts;

import pions.controller.xml.AlertXMLFactory;
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

            root = iter.next().getElementById(AlertXMLFactory.ALERT);

            appendElement(buffer, AlertXMLFactory.PERSONAL);

            appendElement(buffer, AlertXMLFactory.EMAIL);

            appendElement(buffer, AlertXMLFactory.TYPE);

            appendElement(buffer, AlertXMLFactory.DESCRIPTION);
        }

        return buffer;
    }
}

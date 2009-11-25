
package pions.view.alerts;

import pions.controller.xml.AlertXMLFactory;
import pions.view.AbstractIList;

/**
 *
 * 
 */
public abstract class AbstractAlertsIList extends AbstractIList {
    protected StringBuffer parseNext(){
        StringBuffer buffer = null;

        if(hasNext()){
            buffer = new StringBuffer();

            setHead(iter.next(), AlertXMLFactory.ALERT);

            appendElement(buffer, AlertXMLFactory.PERSONAL);
            buffer.append('\n');

            appendElement(buffer, AlertXMLFactory.EMAIL);
            buffer.append('\n');

            appendElement(buffer, AlertXMLFactory.TYPE);
            buffer.append('\n');
        }

        return buffer;
    }
}

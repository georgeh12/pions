
package pions.view.alerts;

import pions.controller.xml.XMLFactory;
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

            root = XMLFactory.getHead(iter.next(), XMLFactory.ALERT);

            appendAttribute(buffer, XMLFactory.CONTACT_PERSONAL);
            buffer.append('\n');

            appendAttribute(buffer, XMLFactory.CONTACT_EMAIL);
            buffer.append('\n');

            appendAttribute(buffer, XMLFactory.ALERT_TYPE);
            buffer.append('\n');
        }

        return buffer;
    }
}


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

            appendElement(buffer, XMLFactory.CONTACT_PERSONAL);
            buffer.append('\n');

            appendElement(buffer, XMLFactory.CONTACT_EMAIL);
            buffer.append('\n');

            appendElement(buffer, XMLFactory.TYPE);
            buffer.append('\n');
        }

        return buffer;
    }
}


package pions.controller.xml;

import java.util.Iterator;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import pions.model.Alert;

/**
 * Iterator design implementation.
 * 
 */
public final class AlertIterator extends XMLIterator<Alert> {
    public AlertIterator(Iterator<Alert> iter) {
        super(iter);
    }

    @Override
    public Document next() {
        xml = null;
        
        try {
            xml = AbstractXMLFactory.newInstance(iter.next());
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        
        return xml;
    }
}

package pions.controller;

import java.util.ArrayList;
import java.util.Iterator;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import pions.model.Alert;

/**
 * Iterator design implementation.
 * @author George
 */
public class AlertIterator extends XMLIterator {
    public static final String ALERT = "ALERT";
    public static final String SENDER = "SENDER";
    public static final String TYPE = "TYPE";
    public static final String DESCRIPTION = "DESCRIPTION";

    public AlertIterator(Iterator<Alert> iter) {
        super(iter);
    }
    
    @Override
    public Document next() {
        xml = null;
        
        try {
            //create a new document
            xml = DocumentBuilderFactory.newInstance().newDocumentBuilder().getDOMImplementation().createDocument(null, null, null);

            Element alert = xml.createElement(ALERT);
            xml.appendChild(alert);

            Alert current = (Alert) iter.next();

            //Set sender
            alert.setAttribute(SENDER, current.getAddress().toString());

            //Set type
            alert.setAttribute(TYPE, current.getType().toString());

            //Set description
            alert.setAttribute(DESCRIPTION, current.get().toString());
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        
        return super.next();
    }
}

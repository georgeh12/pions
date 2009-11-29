
package pions.controller.xml;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import pions.model.Alert;

/**
 *
 * 
 */
public final class AlertXMLFactory extends XMLFactory<Alert> {
    protected Document newInstance(Alert alert) throws ParserConfigurationException{
        //create a new document
        xml = DocumentBuilderFactory.newInstance().newDocumentBuilder().getDOMImplementation().createDocument(null, null, null);

        Element root = setHead(ALERT);

        //Set sender
        setAttribute(root, SENDER_PERSONAL, alert.getAddress().getPersonal());
        setAttribute(root, SENDER_EMAIL, alert.getAddress().getAddress());

        //Set type
        setAttribute(root, TYPE, alert.getType().toString());

        return xml;
    }
}

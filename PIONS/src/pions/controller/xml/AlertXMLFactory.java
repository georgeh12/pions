
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
    public static final String ALERT = "ALERT";
    public static final String PERSONAL = "PERSONAL";
    public static final String EMAIL = "EMAIL";
    public static final String TYPE = "TYPE";
    public static final String DESCRIPTION = "DESCRIPTION";

    public Document newInstance(Alert alert) throws ParserConfigurationException{
        //create a new document
        xml = DocumentBuilderFactory.newInstance().newDocumentBuilder().getDOMImplementation().createDocument(null, null, null);

        Element root = getHead(ALERT);

        //Set sender
        setAttribute(root, PERSONAL, alert.getAddress().getPersonal());
        setAttribute(root, EMAIL, alert.getAddress().getAddress());

        //Set type
        setAttribute(root, TYPE, alert.getType().toString());

        return xml;
    }
}

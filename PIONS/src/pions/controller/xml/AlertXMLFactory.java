
package pions.controller.xml;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import pions.model.Alert;
import pions.model.Contacts.Contact;

/**
 *
 * @author George
 */
public class AlertXMLFactory extends XMLFactory<Alert> {
    public static final String ALERT = "ALERT";
    public static final String PERSONAL = "PERSONAL";
    public static final String EMAIL = "EMAIL";
    public static final String TYPE = "TYPE";
    public static final String DESCRIPTION = "DESCRIPTION";

    public Document newInstance(Alert alert) throws ParserConfigurationException{
        //create a new document
        xml = DocumentBuilderFactory.newInstance().newDocumentBuilder().getDOMImplementation().createDocument(null, null, null);

        Element root = xml.createElement(ALERT);
        xml.appendChild(root);

        //Set sender
        addNode(root, PERSONAL, alert.getAddress().getPersonal());
        addNode(root, EMAIL, alert.getAddress().getAddress());

        //Set type
        addNode(root, TYPE, alert.getType().toString());

        //Set description
        addNode(root, DESCRIPTION, alert.get().toString());

        return xml;
    }
}

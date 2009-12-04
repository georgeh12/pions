
package pions.controller;

import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import pions.model.xml.AbstractXMLFactory;
import pions.model.xml.XMLFactory;
import pions.model.ContactInfo.Address;
import pions.model.ContactInfo.Address.State;
import pions.model.ContactInfo.EmailAddress;
import pions.model.ContactInfo.PhoneNumber;
import pions.model.EmployeeSingleton;
import pions.model.ModelException.NotLoggedInException;
import pions.model.Positions.Position;
import pions.model.Positions.Position.PayType;

/**
 * This class does not support multi-threaded applications.
 * 
 */
public final class ContactInfo {

    public final static Document newInstance(){
        try {
            return AbstractXMLFactory.newInstance(EmployeeSingleton.getInstance());
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        return null;
    }

    public final static void commit(Document xml){
        try {
            Element head = XMLFactory.getHead(xml, XMLFactory.EMPLOYEE);

            //Set name
            EmployeeSingleton.getInstance().setName(XMLFactory.getAttribute(head, XMLFactory.NAME));

            //Set positions
            ArrayList<Position> position_list = new ArrayList<Position>();

            NodeList position_nodes = XMLFactory.getElements(head, XMLFactory.POSITION);
            for(int i = 0; i < position_nodes.getLength(); i ++){
                Element root = (Element)position_nodes.item(i);

                position_list.add(
                        new Position(
                        XMLFactory.getAttribute(root, XMLFactory._TITLE),
                        PayType.valueOf(XMLFactory.getAttribute(root, XMLFactory._PAY_TYPE)),
                        Double.parseDouble(XMLFactory.getAttribute(root, XMLFactory._RATE))));
            }
            EmployeeSingleton.getInstance().getPositions().set(position_list);

            //Set e-mail
            ArrayList<PhoneNumber> phone_list = new ArrayList<PhoneNumber>();

            NodeList phone_nodes = XMLFactory.getElements(head, XMLFactory.PHONE_NUMBER);
            for(int i = 0; i < phone_nodes.getLength(); i ++){
                Node root = phone_nodes.item(i);

                phone_list.add(
                        new PhoneNumber(
                        PhoneNumber.PhoneType.valueOf(XMLFactory.getAttribute(root, XMLFactory._PHONE_TYPE)),
                        PhoneNumber.formatPhoneNumber(XMLFactory.getAttribute(root, XMLFactory._NUMBER)),
                        PhoneNumber.formatExtension(XMLFactory.getAttribute(root, XMLFactory._EXTENSION))));
            }
            EmployeeSingleton.getInstance().getContactInfo().setPhoneNumbers(phone_list);

            //Set phone
            ArrayList<EmailAddress> email_list = new ArrayList<EmailAddress>();

            NodeList email_nodes = XMLFactory.getElements(head, XMLFactory.EMAIL_ADDRESS);
            for(int i = 0; i < email_nodes.getLength(); i ++){
                Node root = email_nodes.item(i);

                email_list.add(
                        new EmailAddress(
                        XMLFactory.getAttribute(root, XMLFactory._EMAIL_ADDRESS),
                        XMLFactory.getAttribute(root, XMLFactory._PERSONAL)));
            }
            EmployeeSingleton.getInstance().getContactInfo().setEmailAddresses(email_list);
            
            //Set address
            NodeList address = XMLFactory.getElements(head, XMLFactory.ADDRESS);
            {
                //there should only be one address, hence the index 0
                Node root = address.item(0);

                EmployeeSingleton.getInstance().getContactInfo().setAddress(
                        new Address(
                        XMLFactory.getAttribute(root, XMLFactory._STREET),
                        XMLFactory.getAttribute(root, XMLFactory._CITY),
                        Address.State.valueOf(XMLFactory.getAttribute(root, XMLFactory._STATE)),
                        XMLFactory.getAttribute(root, XMLFactory._ZIP)));
            }
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }
    }

    public final static ArrayList<String> getStates(){
        ArrayList<String> states = new ArrayList<String>();

        for(State state: pions.model.ContactInfo.Address.State.values()){
            states.add(state.toAbbrev());
        }

        return states;
    }
}

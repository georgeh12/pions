
package pions.controller;

import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import pions.controller.xml.AbstractXMLFactory;
import pions.controller.xml.XMLFactory;
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

    public static Document newInstance(){
        try {
            return AbstractXMLFactory.newInstance(EmployeeSingleton.getInstance());
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static Element getHead(Document xml, String id){
        return xml.getElementById(id);
    }

    private static String getElement(Element root, String tag_name){
        return root.getAttribute(tag_name);
    }

    private static String getAttr(Node root){
        return root.getNodeValue();
    }

    private static NodeList getElements(Element root, String tag_name){
        return root.getElementsByTagName(tag_name);
    }

    public static void commit(Document xml){
        try {
            Element head = getHead(xml, XMLFactory.EMPLOYEE);

            //Set name
            EmployeeSingleton.getInstance().setName(getElement(head, XMLFactory.NAME));

            //Set positions
            ArrayList<Position> position_list = new ArrayList<Position>();

            NodeList position_nodes = getElements(head, XMLFactory.POSITION);
            for(int i = 0; i < position_nodes.getLength(); i ++){
                NodeList attrs = position_nodes.item(i).getChildNodes();

                position_list.add(
                        new Position(
                        getAttr(attrs.item(0)),
                        PayType.valueOf(getAttr(attrs.item(1))),
                        Double.parseDouble(getAttr(attrs.item(2)))));
            }
            EmployeeSingleton.getInstance().getPositions().set(position_list);

            //Set e-mail
            ArrayList<PhoneNumber> phone_list = new ArrayList<PhoneNumber>();

            NodeList phone_nodes = getElements(head, XMLFactory.PHONE_NUMBER);
            for(int i = 0; i < phone_nodes.getLength(); i ++){
                NodeList attrs = phone_nodes.item(i).getChildNodes();

                phone_list.add(
                        new PhoneNumber(
                        PhoneNumber.PhoneType.valueOf(getAttr(attrs.item(0))),
                        PhoneNumber.formatPhoneNumber(getAttr(attrs.item(1))),
                        PhoneNumber.formatExt(getAttr(attrs.item(2)))));
            }

            //Set phone
            ArrayList<EmailAddress> email_list = new ArrayList<EmailAddress>();

            NodeList email_nodes = getElements(head, XMLFactory.EMAIL_ADDRESS);
            for(int i = 0; i < email_nodes.getLength(); i ++){
                NodeList attrs = email_nodes.item(i).getChildNodes();

                email_list.add(
                        new EmailAddress(
                        getAttr(attrs.item(0)),
                        getAttr(attrs.item(1))));
            }
            
            //Set address
            NodeList address = getElements(head, XMLFactory.ADDRESS);
            {
                //there should only be one address, hence the index 0
                NodeList attrs = address.item(0).getChildNodes();

                EmployeeSingleton.getInstance().getContactInfo().setAddress(
                        new Address(
                        getAttr(attrs.item(0)),
                        getAttr(attrs.item(1)),
                        Address.State.valueOf(getAttr(attrs.item(2))),
                        Address.parseZip(getAttr(attrs.item(3)))));
            }
        } catch (NotLoggedInException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getStates(){
        ArrayList<String> states = new ArrayList<String>();

        for(State state: pions.model.ContactInfo.Address.State.values()){
            states.add(state.toAbbrev());
        }

        return states;
    }
}

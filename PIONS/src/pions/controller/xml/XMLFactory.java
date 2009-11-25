
package pions.controller.xml;

import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Implements the AbstractFactory design pattern
 * 
 */
public abstract class XMLFactory<T> {
    protected Document xml;

    public abstract Document newInstance(T object) throws ParserConfigurationException;

    protected Element getHead(String root_id){
        Element root = xml.createElement(root_id);
        root.setAttribute(root_id, root_id);
        root.setIdAttribute(root_id, true);
        xml.appendChild(root);

        return root;
    }

    protected Element addNode(Element root, String child, String value){
        Element child_element = xml.createElement(child);
        root.appendChild(child_element);
        child_element.setAttribute(child, value);

        return child_element;
    }

    protected void setAttribute(Element root, String child, String value){
        root.setAttribute(child, value);
    }
}

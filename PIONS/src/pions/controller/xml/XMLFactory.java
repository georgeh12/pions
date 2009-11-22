
package pions.controller.xml;

import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author George
 */
public abstract class XMLFactory<T> {
    protected Document xml;

    public abstract Document newInstance(T object) throws ParserConfigurationException;

    protected Element addNode(Element root, String child, String value){
        Element child_element = xml.createElement(child);
        root.appendChild(child_element);
        if(value != null) child_element.setNodeValue(value);

        return child_element;
    }
}

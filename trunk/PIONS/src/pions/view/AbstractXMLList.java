
package pions.view;

import javax.swing.JPanel;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import pions.controller.xml.XMLFactory;

/**
 *
 * 
 */
public abstract class AbstractXMLList extends JPanel {
    protected Element root = null;

    protected final void appendAttribute(StringBuffer buffer, String tag_name){
        buffer.append(tag_name + ":\n");
        buffer.append(XMLFactory.getAttribute(root, tag_name));
    }

    protected final void appendElements(StringBuffer buffer, boolean numbered,
            String node, String[] attributes){
        NodeList elements = XMLFactory.getElements(root, node);
        
        buffer.append(node + ":");

        for(int i = 0; i < elements.getLength(); i ++){
            buffer.append("\n" + (numbered ? (i+1) + ". " : "\t"));
            for(int j = 0; j < attributes.length; j ++){
                if(j != 0) buffer.append("\n\t");

                String attribute = XMLFactory.getAttribute(elements.item(i), attributes[j]);

                buffer.append(attributes[j] + ": " + attribute);
            }
        }
    }
}

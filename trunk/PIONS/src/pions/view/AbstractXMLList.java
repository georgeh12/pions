
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

    protected void appendElement(StringBuffer buffer, String tag_name){
        buffer.append(tag_name + ":\n");
        buffer.append(root.getAttribute(tag_name));
    }

    protected void appendElements(StringBuffer buffer, String tag_name){
        NodeList node_list = XMLFactory.getElements(root, tag_name);

        buffer.append(tag_name + ":");

        for(int i = 0; i < node_list.getLength(); i ++){
            buffer.append("\n" + node_list.item(i));
        }
    }
}

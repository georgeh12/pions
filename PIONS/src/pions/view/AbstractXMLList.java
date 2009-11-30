
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
        buffer.append(getAttribute(tag_name));
    }

    protected final String getAttribute(String tag_name){
        return XMLFactory.getAttribute(root, tag_name);
    }

    protected final void appendElements(StringBuffer buffer, String tag_name){
        NodeList node_list = XMLFactory.getElements(root, tag_name);

        buffer.append(tag_name + ":");

        for(int i = 0; i < node_list.getLength(); i ++){
            buffer.append("\n" + node_list.item(i));
        }
    }
}

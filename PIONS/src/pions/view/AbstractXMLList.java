
package pions.view;

import javax.swing.JPanel;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import pions.model.xml.XMLFactory;

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

    protected final void appendElement(StringBuffer buffer, String tag_name){
        NodeList node_list = XMLFactory.getElements(root, tag_name);
        int length = node_list.getLength();
        
        if(length > 0){
            buffer.append(tag_name + ":");

            for(int i = 0; i < length; i ++){
                buffer.append("\n" + node_list.item(i));
            }
        }
    }
}

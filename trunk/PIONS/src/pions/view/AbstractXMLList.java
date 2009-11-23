
package pions.view;

import javax.swing.JPanel;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author George
 */
public abstract class AbstractXMLList extends JPanel {
    protected Element root = null;

    protected void appendElement(StringBuffer buffer, String tag_name){
        NodeList node_list = root.getElementsByTagName(tag_name);

        buffer.append(tag_name + ":\n");

        for(int i = 0; i < node_list.getLength(); i ++){
            buffer.append(node_list.item(i).getNodeValue());
        }
    }
}
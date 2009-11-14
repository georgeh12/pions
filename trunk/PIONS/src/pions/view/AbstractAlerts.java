
package pions.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import org.w3c.dom.Document;
import pions.controller.AlertXMLIterator;

/**
 *
 * @author George
 */
public abstract class AbstractAlerts extends JPanel {

    private AlertXMLIterator iter = null;
    private ArrayList<JCheckBox> check_boxes = null;
    private ArrayList<JTextArea> text_areas = null;
    protected JPanel panel_display = null;

    protected void set(AlertXMLIterator iter){
        this.iter = iter;
        check_boxes = new ArrayList<JCheckBox>();
        text_areas = new ArrayList<JTextArea>();

        display();
    }

    private void display() {
        StringBuffer next;
        GridBagLayout layout = new GridBagLayout();

        while((next = parseNext()) != null){
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.weightx = .5;
            constraints.weighty = 0;
            constraints.insets = new Insets(0,0,10,0);
            constraints.anchor = GridBagConstraints.PAGE_START;
            constraints.gridy = check_boxes.size();

            JCheckBox check_box = new JCheckBox();
            constraints.gridx = 0;
            constraints.fill = GridBagConstraints.BOTH;
            layout.setConstraints(check_box, constraints);

            JTextArea text_area = new JTextArea(next.toString());
            text_area.setLineWrap(true);
            text_area.setWrapStyleWord(true);
            constraints.gridx = 1;
            constraints.fill = GridBagConstraints.HORIZONTAL;
            layout.setConstraints(text_area, constraints);

            panel_display.add(check_box);
            panel_display.add(text_area);

            check_boxes.add(check_box);
            text_areas.add(text_area);
        }

        panel_display.setLayout(layout);
    }

    private boolean hasNext(){
        if(iter != null) return iter.hasNext();

        return false;
    }

    private StringBuffer parseNext(){
        StringBuffer buffer = null;

        if(hasNext()){
            buffer = new StringBuffer();
            Document xml = iter.next();

            buffer.append("Sender: \n");
            buffer.append(xml.getElementById(AlertXMLIterator.SENDER).getNodeValue());

            buffer.append("Type: \n");
            buffer.append(xml.getElementById(AlertXMLIterator.TYPE).getNodeValue());

            buffer.append("Description: \n");
            buffer.append(xml.getElementById(AlertXMLIterator.DESCRIPTION).getNodeValue());
        }

        return buffer;
    }

    protected int getFirstIndex(){
        for(int i = 0; i < check_boxes.size(); i++){
            if(check_boxes.get(i).isSelected()) return i;
        }

        return -1;
    }

    protected ArrayList<Integer> getIndices(){
        ArrayList<Integer> selected = new ArrayList<Integer>();

        for(int i = 0; i < check_boxes.size(); i++){
            if(check_boxes.get(i).isSelected()) selected.add(i);
        }

        return selected;
    }

    protected void selectAll(){
        selectCheckBoxes(true);
    }

    protected void selectNone(){
        selectCheckBoxes(false);
    }

    private void selectCheckBoxes(boolean set){
        for(JCheckBox check_box: check_boxes){
            check_box.setSelected(set);
        }
    }
}


package pions.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import pions.controller.xml.XMLIterator;

/**
 *
 * 
 */
public abstract class AbstractIList extends AbstractXMLList {

    private ArrayList<JCheckBox> check_boxes = null;
    private ArrayList<JTextArea> text_areas = null;
    protected JPanel panel_display = null;
    protected XMLIterator iter = null;

    protected void set(XMLIterator iter){
        this.iter = iter;

        parse();
        display();
    }

    private void parse() {
        StringBuffer next;
        check_boxes = new ArrayList<JCheckBox>();
        text_areas = new ArrayList<JTextArea>();

        while((next = parseNext()) != null){
            JCheckBox check_box = new JCheckBox();
            JTextArea text_area = new JTextArea(next.toString());

            check_boxes.add(check_box);
            text_areas.add(text_area);
        }
    }

    private void display() {
        GridBagLayout layout = new GridBagLayout();
        panel_display.removeAll();

        for(int i = check_boxes.size() - 1; i >= 0; i--){
            JCheckBox check_box = check_boxes.get(i);
            JTextArea text_area = text_areas.get(i);
            
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.weightx = .5;
            constraints.weighty = 0;
            constraints.insets = new Insets(0,0,10,0);
            constraints.anchor = GridBagConstraints.PAGE_START;
            constraints.gridy = GridBagConstraints.RELATIVE;

            constraints.gridx = 0;
            constraints.fill = GridBagConstraints.BOTH;
            layout.setConstraints(check_box, constraints);

            text_area.setLineWrap(true);
            text_area.setWrapStyleWord(true);
            text_area.setEditable(false);
            constraints.gridx = 1;
            constraints.fill = GridBagConstraints.HORIZONTAL;
            layout.setConstraints(text_area, constraints);

            panel_display.add(check_box);
            panel_display.add(text_area);
        }

        panel_display.setLayout(layout);
        panel_display.revalidate();
        panel_display.repaint();
    }

    protected boolean hasNext(){
        if(iter != null) return iter.hasNext();

        return false;
    }

    protected abstract StringBuffer parseNext();

    public int getFirstIndex(){
        for(int i = check_boxes.size() - 1; i >= 0; i--){
            if(check_boxes.get(i).isSelected()) return i;
        }

        return -1;
    }

    public Integer[] getIndices(){
        ArrayList<Integer> selected = new ArrayList<Integer>();

        for(int i = check_boxes.size() - 1; i >= 0; i--){
            if(check_boxes.get(i).isSelected()) selected.add(i);
        }

        return selected.toArray(new Integer[0]);
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

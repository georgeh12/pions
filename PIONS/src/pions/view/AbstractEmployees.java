
package pions.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import pions.controller.Employees;

/**
 *
 * @author George
 */
public class AbstractEmployees extends JPanel {

    private ArrayList<String> names;
    private ArrayList<JRadioButton> radio_buttons = null;
    private ArrayList<JTextArea> text_areas = null;
    private ButtonGroup button_group = new ButtonGroup();
    protected JPanel panel_display = null;

    protected void set(ArrayList<String> names){
        this.names = names;
        radio_buttons = new ArrayList<JRadioButton>();
        text_areas = new ArrayList<JTextArea>();

        display();
    }

    private void display(){
        GridBagLayout layout = new GridBagLayout();

        for(String name: names){
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.weightx = .5;
            constraints.weighty = 0;
            constraints.insets = new Insets(0,0,10,0);
            constraints.anchor = GridBagConstraints.PAGE_START;
            constraints.gridy = radio_buttons.size();

            JRadioButton radio_button = new JRadioButton();
            constraints.gridx = 0;
            constraints.fill = GridBagConstraints.BOTH;
            layout.setConstraints(radio_button, constraints);

            JTextArea text_area = new JTextArea(name);
            text_area.setLineWrap(true);
            text_area.setWrapStyleWord(true);
            constraints.gridx = 1;
            constraints.fill = GridBagConstraints.HORIZONTAL;
            layout.setConstraints(text_area, constraints);

            panel_display.add(radio_button);
            panel_display.add(text_area);

            radio_button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    radio_buttonActionPerformed(evt);
                }
            });
            button_group.add(radio_button);
            radio_buttons.add(radio_button);
            text_areas.add(text_area);
        }

        panel_display.setLayout(layout);
    }

    private void radio_buttonActionPerformed(ActionEvent evt){
        PIONSView.getInstance().setAux(new EmployeeDetails(Employees.getManagerXML(
                radio_buttons.indexOf(evt.getSource()))));
    }

}

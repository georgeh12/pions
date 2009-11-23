
package pions.view.calendars;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import pions.controller.Contacts;
import pions.controller.xml.CalendarIterator;
import pions.controller.xml.CalendarXMLFactory;
import pions.controller.xml.ContactXMLFactory;
import pions.view.AbstractXMLList;
import pions.view.PIONSView;

/**
 *
 * @author George
 */
public abstract class AbstractCalendarList extends AbstractXMLList {

    private JTextField field_name = null;
    private JTextField field_gmail = null;
    private JToggleButton togglebutton_contacts = null;
    private JComboBox combobox_delete = null;
    private JButton button_delete = null;

    protected void init(JTextField field_name, JTextField field_gmail,
            JToggleButton togglebutton_contacts, JComboBox combobox_delete,
            JButton button_delete){
        this.field_name = field_name;
        this.field_gmail = field_gmail;
        this.togglebutton_contacts = togglebutton_contacts;
        this.combobox_delete = combobox_delete;
        this.button_delete = button_delete;

        contactsActionListener();

        eventsActionListener();
    }

    private void eventsActionListener(){
        button_delete.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                if(combobox_delete.getSelectedIndex() == -1){
                    JOptionPane.showConfirmDialog(PIONSView.getInstance().getContactList(),
                            "No events to delete.",
                            "Can't Delete",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.WARNING_MESSAGE);
                }
                else{
                    deleteEvent(combobox_delete.getSelectedIndex());
                }
            }
        });
    }

    protected abstract void deleteEvent(int index);

    private void contactsActionListener(){
        togglebutton_contacts.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                if(togglebutton_contacts.isSelected()){
                    PIONSView.getInstance().getContactList().setVisible(true);
                }
                else {
                    int index = PIONSView.getInstance().getContactList().getFirstIndex();
                    if(index == -1){
                        JOptionPane.showConfirmDialog(PIONSView.getInstance().getContactList(),
                                "Please select a contact from the contact list.",
                                "Select Contact",
                                JOptionPane.DEFAULT_OPTION,
                                JOptionPane.WARNING_MESSAGE);
                    }
                    else{
                        StringBuffer buffer_name = new StringBuffer();
                        StringBuffer buffer_gmail = new StringBuffer();

                        //Gets the contact xml for the given index
                        root = Contacts.getContact(index).getElementById(ContactXMLFactory.CONTACT);

                        appendElement(buffer_name, ContactXMLFactory.PERSONAL);

                        appendElement(buffer_gmail, ContactXMLFactory.EMAIL);

                        field_name.setText(buffer_name.toString());
                        field_gmail.setText(buffer_gmail.toString());
                    }
                }
            }
        });
    }

    protected void display(CalendarIterator iter){
        combobox_delete.removeAllItems();
        
        while(iter.hasNext()){
            Document xml = iter.next();

            root = xml.getElementById(CalendarXMLFactory.CALENDAR);

            StringBuffer buffer = new StringBuffer();
            NodeList node_list = null;
            
            node_list = root.getElementsByTagName(CalendarXMLFactory.TITLE);
            for(int i = 0; i < node_list.getLength(); i ++){
                buffer.append(node_list.item(i).getNodeValue());
            }

            node_list = root.getElementsByTagName(CalendarXMLFactory.EXTENSION);
            for(int i = 0; i < node_list.getLength(); i ++){
                buffer.append(node_list.item(i).getNodeValue());
            }

            combobox_delete.addItem(buffer.toString().replace(":\n", ", "));
        }
    }
}

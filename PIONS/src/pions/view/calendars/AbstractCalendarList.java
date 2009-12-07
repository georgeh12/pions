
package pions.view.calendars;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import pions.controller.Contacts;
import pions.controller.xml.XMLFactory;
import pions.controller.xml.XMLIterator;
import pions.view.AbstractXMLList;
import pions.view.PIONSView;

//TODO Implement time validation, and an error message.
/**
 *
 * 
 */
public abstract class AbstractCalendarList extends AbstractXMLList {

    private JTextField field_name = null;
    private JTextField field_gmail = null;
    private JToggleButton togglebutton_contacts = null;
    private JComboBox combobox_delete = null;
    private JButton button_delete = null;
    private JButton button_add = null;

    protected void init(JTextField field_name, JTextField field_gmail,
            JToggleButton togglebutton_contacts, JComboBox combobox_delete,
            JButton button_delete, JButton button_add){
        this.field_name = field_name;
        this.field_gmail = field_gmail;
        this.togglebutton_contacts = togglebutton_contacts;
        this.combobox_delete = combobox_delete;
        this.button_delete = button_delete;
        this.button_add = button_add;

        contactsActionListener();

        deleteEventsActionListener();

        addEventsActionListener();
    }

    private void addEventsActionListener(){
        button_add.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                addEvent();
            }
        });
    }

    private void deleteEventsActionListener(){
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

    protected abstract void addEvent();

    protected abstract void clear();

    protected abstract void deleteEvent(int index);

    private void contactsActionListener(){
        togglebutton_contacts.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                if(togglebutton_contacts.isSelected()){
                    PIONSView.getInstance().setContactsVisible(true);
                }
                else {
                    int index = PIONSView.getInstance().getContactList().getFirstIndex();
                    if(index != -1){
                        //Gets the contact xml for the given index
                        root = Contacts.getContact(index).getElementById(XMLFactory.CONTACT);

                        field_name.setText(XMLFactory.getAttribute(root,
                                XMLFactory.CONTACT_PERSONAL));
                        field_gmail.setText(XMLFactory.getAttribute(root,
                                XMLFactory.CONTACT_EMAIL));
                    }
                    else{
                        field_name.setText("");
                        field_gmail.setText("");
                    }
                }
            }
        });
    }

    protected void display(XMLIterator<?> iter){
        combobox_delete.removeAllItems();
        
        while(iter.hasNext()){
            Document xml = iter.next();

            root = XMLFactory.getHead(xml, XMLFactory.CALENDAR);

            combobox_delete.addItem(getComboBoxBuffer(root));
        }
    }

    public final static String getComboBoxBuffer(Element root){
        StringBuffer buffer = new StringBuffer();

        buffer.append(XMLFactory.getAttribute(root, XMLFactory.START_TIME).substring(5));
        buffer.append(" - ");
        buffer.append(XMLFactory.getAttribute(root, XMLFactory.END_TIME).substring(11));
        buffer.append(", ");
        buffer.append(XMLFactory.getAttribute(root, XMLFactory.TITLE));

        return buffer.toString();
    }
}


package pions.view.employees;

import javax.swing.JOptionPane;
import org.w3c.dom.Document;
import pions.controller.xml.XMLFactory;
import pions.view.AbstractXMLList;

/**
 *
 * 
 */
public class DisplayEmployee extends AbstractXMLList {

    protected StringBuffer buffer_name = null;
    protected StringBuffer buffer_iscontact = null;
    protected StringBuffer buffer_positions = null;
    protected StringBuffer buffer_gmailaddress = null;
    protected StringBuffer buffer_emailaddresses = null;
    protected StringBuffer buffer_phonenumbers = null;
    protected StringBuffer buffer_address = null;

    /** Creates new form EmployeeDetails */
    public DisplayEmployee(Document xml) {
        initComponents();

        if(xml == null){
            JOptionPane.showConfirmDialog(this,
                    "Unable to retrieve employee information.",
                    "Employee Not Found",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.ERROR_MESSAGE);
        }
        else{
            root = xml.getElementById(XMLFactory.EMPLOYEE);
            set();

            print();
        }
    }

    private void set(){
        buffer_name = new StringBuffer();
        buffer_iscontact = new StringBuffer();
        buffer_positions = new StringBuffer();
        buffer_gmailaddress = new StringBuffer();
        buffer_emailaddresses = new StringBuffer();
        buffer_phonenumbers = new StringBuffer();
        buffer_address = new StringBuffer();

        appendAttribute(buffer_name, XMLFactory.NAME);
        appendAttribute(buffer_iscontact, XMLFactory.IS_CONTACT);
        appendElements(buffer_positions, XMLFactory.POSITION);
        appendAttribute(buffer_gmailaddress, XMLFactory.GMAIL_ADDRESS);
        appendElements(buffer_emailaddresses, XMLFactory.EMAIL_ADDRESS);
        appendElements(buffer_phonenumbers, XMLFactory.PHONE_NUMBER);
        appendAttribute(buffer_address, XMLFactory.ADDRESS);
    }

    /**
     * Parses XML from the document and displays it in the text area.
     */
    private void print(){
        StringBuffer buffer = new StringBuffer();

        if(checkbox_name.isSelected()){
            buffer.append(buffer_name);
            buffer.append("\n");
        }
        if(checkbox_contact.isSelected()){
            buffer.append(buffer_iscontact);
            buffer.append("\n");
        }
        if(checkbox_position.isSelected()){
            buffer.append(buffer_positions);
            buffer.append("\n");
        }
        if(checkbox_gmail.isSelected()){
            buffer.append(buffer_gmailaddress);
            buffer.append("\n");
        }
        if(checkbox_email.isSelected()){
            buffer.append(buffer_emailaddresses);
            buffer.append("\n");
        }
        if(checkbox_phone.isSelected()){
            buffer.append(buffer_phonenumbers);
            buffer.append("\n");
        }
        if(checkbox_address.isSelected()){
            buffer.append(buffer_address);
            buffer.append("\n");
        }
        
        textarea_display.setText(buffer.toString());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollpane_display = new javax.swing.JScrollPane();
        textarea_display = new javax.swing.JTextArea();
        checkbox_address = new javax.swing.JCheckBox();
        checkbox_phone = new javax.swing.JCheckBox();
        checkbox_email = new javax.swing.JCheckBox();
        checkbox_position = new javax.swing.JCheckBox();
        checkbox_gmail = new javax.swing.JCheckBox();
        checkbox_name = new javax.swing.JCheckBox();
        checkbox_contact = new javax.swing.JCheckBox();
        label_title = new javax.swing.JLabel();
        label_directions = new javax.swing.JLabel();

        setName("Form"); // NOI18N

        scrollpane_display.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollpane_display.setName("scrollpane_display"); // NOI18N

        textarea_display.setColumns(20);
        textarea_display.setEditable(false);
        textarea_display.setLineWrap(true);
        textarea_display.setWrapStyleWord(true);
        textarea_display.setName("textarea_display"); // NOI18N
        scrollpane_display.setViewportView(textarea_display);

        checkbox_address.setSelected(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(pions.PIONS.class).getContext().getResourceMap(DisplayEmployee.class);
        checkbox_address.setText(resourceMap.getString("checkbox_address.text")); // NOI18N
        checkbox_address.setToolTipText(resourceMap.getString("checkbox_address.toolTipText")); // NOI18N
        checkbox_address.setName("checkbox_address"); // NOI18N
        checkbox_address.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkbox_addressActionPerformed(evt);
            }
        });

        checkbox_phone.setSelected(true);
        checkbox_phone.setText(resourceMap.getString("checkbox_phone.text")); // NOI18N
        checkbox_phone.setToolTipText(resourceMap.getString("checkbox_phone.toolTipText")); // NOI18N
        checkbox_phone.setName("checkbox_phone"); // NOI18N
        checkbox_phone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkbox_phoneActionPerformed(evt);
            }
        });

        checkbox_email.setSelected(true);
        checkbox_email.setText(resourceMap.getString("checkbox_email.text")); // NOI18N
        checkbox_email.setToolTipText(resourceMap.getString("checkbox_email.toolTipText")); // NOI18N
        checkbox_email.setName("checkbox_email"); // NOI18N
        checkbox_email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkbox_emailActionPerformed(evt);
            }
        });

        checkbox_position.setSelected(true);
        checkbox_position.setText(resourceMap.getString("checkbox_position.text")); // NOI18N
        checkbox_position.setToolTipText(resourceMap.getString("checkbox_position.toolTipText")); // NOI18N
        checkbox_position.setName("checkbox_position"); // NOI18N
        checkbox_position.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkbox_positionActionPerformed(evt);
            }
        });

        checkbox_gmail.setSelected(true);
        checkbox_gmail.setText(resourceMap.getString("checkbox_gmail.text")); // NOI18N
        checkbox_gmail.setToolTipText(resourceMap.getString("checkbox_gmail.toolTipText")); // NOI18N
        checkbox_gmail.setName("checkbox_gmail"); // NOI18N
        checkbox_gmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkbox_gmailActionPerformed(evt);
            }
        });

        checkbox_name.setSelected(true);
        checkbox_name.setText(resourceMap.getString("checkbox_name.text")); // NOI18N
        checkbox_name.setToolTipText(resourceMap.getString("checkbox_name.toolTipText")); // NOI18N
        checkbox_name.setName("checkbox_name"); // NOI18N
        checkbox_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkbox_nameActionPerformed(evt);
            }
        });

        checkbox_contact.setSelected(true);
        checkbox_contact.setText(resourceMap.getString("checkbox_contact.text")); // NOI18N
        checkbox_contact.setToolTipText(resourceMap.getString("checkbox_contact.toolTipText")); // NOI18N
        checkbox_contact.setName("checkbox_contact"); // NOI18N
        checkbox_contact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkbox_contactActionPerformed(evt);
            }
        });

        label_title.setFont(resourceMap.getFont("label_title.font")); // NOI18N
        label_title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_title.setText(resourceMap.getString("label_title.text")); // NOI18N
        label_title.setName("label_title"); // NOI18N

        label_directions.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_directions.setText(resourceMap.getString("label_directions.text")); // NOI18N
        label_directions.setName("label_directions"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label_title, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
            .addComponent(label_directions, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(checkbox_name)
                        .addGap(18, 18, 18)
                        .addComponent(checkbox_contact)
                        .addGap(18, 18, 18)
                        .addComponent(checkbox_position))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(checkbox_gmail)
                        .addGap(18, 18, 18)
                        .addComponent(checkbox_email))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(checkbox_phone)
                        .addGap(18, 18, 18)
                        .addComponent(checkbox_address)))
                .addContainerGap(61, Short.MAX_VALUE))
            .addComponent(scrollpane_display, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(label_title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_directions)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkbox_name)
                    .addComponent(checkbox_contact)
                    .addComponent(checkbox_position))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkbox_email)
                    .addComponent(checkbox_gmail))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkbox_phone)
                    .addComponent(checkbox_address))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollpane_display, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void checkbox_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkbox_nameActionPerformed
        print();
    }//GEN-LAST:event_checkbox_nameActionPerformed

    private void checkbox_contactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkbox_contactActionPerformed
        print();
    }//GEN-LAST:event_checkbox_contactActionPerformed

    private void checkbox_positionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkbox_positionActionPerformed
        print();
    }//GEN-LAST:event_checkbox_positionActionPerformed

    private void checkbox_gmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkbox_gmailActionPerformed
        print();
    }//GEN-LAST:event_checkbox_gmailActionPerformed

    private void checkbox_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkbox_emailActionPerformed
        print();
    }//GEN-LAST:event_checkbox_emailActionPerformed

    private void checkbox_phoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkbox_phoneActionPerformed
        print();
    }//GEN-LAST:event_checkbox_phoneActionPerformed

    private void checkbox_addressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkbox_addressActionPerformed
        print();
    }//GEN-LAST:event_checkbox_addressActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkbox_address;
    private javax.swing.JCheckBox checkbox_contact;
    private javax.swing.JCheckBox checkbox_email;
    private javax.swing.JCheckBox checkbox_gmail;
    private javax.swing.JCheckBox checkbox_name;
    private javax.swing.JCheckBox checkbox_phone;
    private javax.swing.JCheckBox checkbox_position;
    private javax.swing.JLabel label_directions;
    private javax.swing.JLabel label_title;
    private javax.swing.JScrollPane scrollpane_display;
    private javax.swing.JTextArea textarea_display;
    // End of variables declaration//GEN-END:variables

}
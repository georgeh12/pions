
package pions.view.contactinfo;

import pions.view.*;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JOptionPane;
import pions.controller.xml.XMLFactory;
import pions.controller.Contacts;
import pions.controller.Employees;

/**
 * Implements Observer design pattern.
 * 
 */
public class ContactIList extends AbstractIList implements Observer {

    /** Creates new form Contacts */
    public ContactIList() {
        initComponents();

        super.panel_display = panel_display;
    }

    public void init(){
        Contacts.subscribe(this);
        refresh();
    }
    
    public void update(Observable o, Object arg) {
        refresh();
    }

    public void refresh(){
        super.set(Contacts.getContactIterator());
    }

    @Override
    protected StringBuffer parseNext(){
        StringBuffer buffer = null;

        if(hasNext()){
            buffer = new StringBuffer();

            root = XMLFactory.getHead(iter.next(), XMLFactory.CONTACT);

            appendAttribute(buffer, XMLFactory.CONTACT_PERSONAL);
            buffer.append('\n');

            appendAttribute(buffer, XMLFactory.CONTACT_EMAIL);
            buffer.append('\n');
        }

        return buffer;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label_title = new javax.swing.JLabel();
        label_directions = new javax.swing.JLabel();
        button_select_all = new javax.swing.JButton();
        label_select = new javax.swing.JLabel();
        button_select_none = new javax.swing.JButton();
        scrollpane_display = new javax.swing.JScrollPane();
        panel_display = new javax.swing.JPanel();
        button_contactrequest = new javax.swing.JButton();
        button_subordinate = new javax.swing.JButton();
        button_manager = new javax.swing.JButton();
        button_removecontact = new javax.swing.JButton();
        label_employeerequest = new javax.swing.JLabel();
        label_directions_employeerequest = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setName("Form"); // NOI18N
        setPreferredSize(new java.awt.Dimension(300, 400));

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(pions.PIONS.class).getContext().getResourceMap(ContactIList.class);
        label_title.setFont(resourceMap.getFont("label_title.font")); // NOI18N
        label_title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_title.setText(resourceMap.getString("label_title.text")); // NOI18N
        label_title.setName("label_title"); // NOI18N

        label_directions.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_directions.setText(resourceMap.getString("label_directions.text")); // NOI18N
        label_directions.setName("label_directions"); // NOI18N

        button_select_all.setText(resourceMap.getString("button_select_all.text")); // NOI18N
        button_select_all.setName("button_select_all"); // NOI18N
        button_select_all.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_select_allActionPerformed(evt);
            }
        });

        label_select.setText(resourceMap.getString("label_select.text")); // NOI18N
        label_select.setName("label_select"); // NOI18N

        button_select_none.setText(resourceMap.getString("button_select_none.text")); // NOI18N
        button_select_none.setName("button_select_none"); // NOI18N
        button_select_none.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_select_noneActionPerformed(evt);
            }
        });

        scrollpane_display.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollpane_display.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollpane_display.setName("scrollpane_display"); // NOI18N

        panel_display.setName("panel_display"); // NOI18N
        panel_display.setPreferredSize(new java.awt.Dimension(100, 100));

        javax.swing.GroupLayout panel_displayLayout = new javax.swing.GroupLayout(panel_display);
        panel_display.setLayout(panel_displayLayout);
        panel_displayLayout.setHorizontalGroup(
            panel_displayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 261, Short.MAX_VALUE)
        );
        panel_displayLayout.setVerticalGroup(
            panel_displayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 186, Short.MAX_VALUE)
        );

        scrollpane_display.setViewportView(panel_display);

        button_contactrequest.setText(resourceMap.getString("button_contactrequest.text")); // NOI18N
        button_contactrequest.setName("button_contactrequest"); // NOI18N
        button_contactrequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_contactrequestActionPerformed(evt);
            }
        });

        button_subordinate.setText(resourceMap.getString("button_subordinate.text")); // NOI18N
        button_subordinate.setName("button_subordinate"); // NOI18N
        button_subordinate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_subordinateActionPerformed(evt);
            }
        });

        button_manager.setText(resourceMap.getString("button_manager.text")); // NOI18N
        button_manager.setName("button_manager"); // NOI18N
        button_manager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_managerActionPerformed(evt);
            }
        });

        button_removecontact.setText(resourceMap.getString("button_removecontact.text")); // NOI18N
        button_removecontact.setName("button_removecontact"); // NOI18N

        label_employeerequest.setText(resourceMap.getString("label_employeerequest.text")); // NOI18N
        label_employeerequest.setName("label_employeerequest"); // NOI18N

        label_directions_employeerequest.setText(resourceMap.getString("label_directions_employeerequest.text")); // NOI18N
        label_directions_employeerequest.setName("label_directions_employeerequest"); // NOI18N

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label_title, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label_directions, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                .addGap(10, 10, 10))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(label_employeerequest)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button_manager)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button_subordinate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(label_directions_employeerequest, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(label_select)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button_select_all)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button_select_none)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollpane_display, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(button_contactrequest, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button_removecontact)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(label_title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_directions)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button_contactrequest)
                    .addComponent(button_removecontact)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_directions_employeerequest)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_employeerequest)
                    .addComponent(button_manager)
                    .addComponent(button_subordinate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_select)
                    .addComponent(button_select_all)
                    .addComponent(button_select_none))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollpane_display, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void button_select_allActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_select_allActionPerformed
        super.selectAll();
    }//GEN-LAST:event_button_select_allActionPerformed

    private void button_select_noneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_select_noneActionPerformed
        super.selectNone();
    }//GEN-LAST:event_button_select_noneActionPerformed

    private void button_contactrequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_contactrequestActionPerformed
        String gmail = "";

        while("".equals(gmail)){
            gmail = JOptionPane.showInputDialog(this,
                "Enter contact's Gmail address.\nRemember to include the @gmail.com suffix.",
                "Send Contact Request",
                JOptionPane.OK_CANCEL_OPTION);
        }

        if(gmail != null){
            if(!Contacts.sendContactRequest(gmail)){
                JOptionPane.showConfirmDialog(this,
                    "Unable to send a message to your own e-mail address",
                    "Addressed To Self",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_button_contactrequestActionPerformed

    private void button_managerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_managerActionPerformed
        Employees.sendNewManager(super.getIndices());
}//GEN-LAST:event_button_managerActionPerformed

    private void button_subordinateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_subordinateActionPerformed
        Employees.sendNewSubordinate(super.getIndices());
}//GEN-LAST:event_button_subordinateActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Contacts.sendContactResponse(super.getIndices());
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_contactrequest;
    private javax.swing.JButton button_manager;
    private javax.swing.JButton button_removecontact;
    private javax.swing.JButton button_select_all;
    private javax.swing.JButton button_select_none;
    private javax.swing.JButton button_subordinate;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel label_directions;
    private javax.swing.JLabel label_directions_employeerequest;
    private javax.swing.JLabel label_employeerequest;
    private javax.swing.JLabel label_select;
    private javax.swing.JLabel label_title;
    private javax.swing.JPanel panel_display;
    private javax.swing.JScrollPane scrollpane_display;
    // End of variables declaration//GEN-END:variables

}

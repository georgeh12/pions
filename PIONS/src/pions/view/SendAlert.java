
package pions.view;

/**
 *
 * @author George
 */
public class SendAlert extends javax.swing.JPanel {

    /** Creates new form SendAlert */
    public SendAlert() {
        initComponents();
    }

    //TODO add a method to tell if an alert has been modified so the same alert will not be sent twice.

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label_title = new javax.swing.JLabel();
        button_subordinate = new javax.swing.JButton();
        button_request_manager = new javax.swing.JButton();
        button_request_subordinate = new javax.swing.JButton();
        button_request_contact = new javax.swing.JButton();
        label_directions = new javax.swing.JLabel();
        button_request_swapshift = new javax.swing.JButton();

        setName("Form"); // NOI18N
        setPreferredSize(new java.awt.Dimension(300, 400));

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(pions.PIONS.class).getContext().getResourceMap(SendAlert.class);
        label_title.setFont(resourceMap.getFont("label_title.font")); // NOI18N
        label_title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_title.setText(resourceMap.getString("label_title.text")); // NOI18N
        label_title.setName("label_title"); // NOI18N

        button_subordinate.setText(resourceMap.getString("button_subordinate.text")); // NOI18N
        button_subordinate.setName("button_subordinate"); // NOI18N
        button_subordinate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_subordinateActionPerformed(evt);
            }
        });

        button_request_manager.setText(resourceMap.getString("button_request_manager.text")); // NOI18N
        button_request_manager.setName("button_request_manager"); // NOI18N
        button_request_manager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_request_managerActionPerformed(evt);
            }
        });

        button_request_subordinate.setText(resourceMap.getString("button_request_subordinate.text")); // NOI18N
        button_request_subordinate.setName("button_request_subordinate"); // NOI18N
        button_request_subordinate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_request_subordinateActionPerformed(evt);
            }
        });

        button_request_contact.setText(resourceMap.getString("button_request_contact.text")); // NOI18N
        button_request_contact.setName("button_request_contact"); // NOI18N
        button_request_contact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_request_contactActionPerformed(evt);
            }
        });

        label_directions.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_directions.setText(resourceMap.getString("label_directions.text")); // NOI18N
        label_directions.setName("label_directions"); // NOI18N

        button_request_swapshift.setText(resourceMap.getString("button_request_swapshift.text")); // NOI18N
        button_request_swapshift.setName("button_request_swapshift"); // NOI18N
        button_request_swapshift.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_request_swapshiftActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label_title, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
            .addComponent(label_directions, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(button_subordinate, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(button_request_manager, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(button_request_subordinate, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(button_request_contact, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(button_request_swapshift, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(label_title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_directions)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(button_subordinate, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(button_request_manager, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(button_request_subordinate, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(button_request_contact, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(button_request_swapshift, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void button_subordinateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_subordinateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_subordinateActionPerformed

    private void button_request_managerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_request_managerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_request_managerActionPerformed

    private void button_request_subordinateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_request_subordinateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_request_subordinateActionPerformed

    private void button_request_contactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_request_contactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_request_contactActionPerformed

    private void button_request_swapshiftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_request_swapshiftActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_request_swapshiftActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_request_contact;
    private javax.swing.JButton button_request_manager;
    private javax.swing.JButton button_request_subordinate;
    private javax.swing.JButton button_request_swapshift;
    private javax.swing.JButton button_subordinate;
    private javax.swing.JLabel label_directions;
    private javax.swing.JLabel label_title;
    // End of variables declaration//GEN-END:variables

}

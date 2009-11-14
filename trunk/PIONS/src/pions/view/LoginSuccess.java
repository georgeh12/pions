
package pions.view;

import javax.swing.JOptionPane;
import pions.controller.Employees;

/**
 *
 * @author George
 */
public class LoginSuccess extends javax.swing.JPanel {

    /** Creates new form SplashPage */
    public LoginSuccess() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label_welcome = new javax.swing.JLabel();
        label_name = new javax.swing.JLabel();
        label_picture = new javax.swing.JLabel();
        label_directions = new javax.swing.JLabel();
        button_logout = new javax.swing.JButton();

        setName("Form"); // NOI18N
        setPreferredSize(new java.awt.Dimension(300, 400));

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(pions.PIONS.class).getContext().getResourceMap(LoginSuccess.class);
        label_welcome.setFont(resourceMap.getFont("label_welcome.font")); // NOI18N
        label_welcome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_welcome.setText(resourceMap.getString("label_welcome.text")); // NOI18N
        label_welcome.setName("label_welcome"); // NOI18N

        label_name.setFont(resourceMap.getFont("label_name.font")); // NOI18N
        label_name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_name.setText(resourceMap.getString("label_name.text")); // NOI18N
        label_name.setName("label_name"); // NOI18N

        label_picture.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_picture.setIcon(resourceMap.getIcon("label_picture.icon")); // NOI18N
        label_picture.setText(resourceMap.getString("label_picture.text")); // NOI18N
        label_picture.setName("label_picture"); // NOI18N

        label_directions.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_directions.setText(resourceMap.getString("label_directions.text")); // NOI18N
        label_directions.setName("label_directions"); // NOI18N

        button_logout.setText(resourceMap.getString("button_logout.text")); // NOI18N
        button_logout.setName("button_logout"); // NOI18N
        button_logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_logoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label_welcome, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
            .addComponent(label_name, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label_directions, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label_picture, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(button_logout, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(label_welcome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_name)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(label_directions)
                .addGap(18, 18, 18)
                .addComponent(label_picture)
                .addGap(18, 18, 18)
                .addComponent(button_logout, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );

        String name = Employees.getDisplayName();
        label_name.setText(name);
        label_name.setToolTipText(name);
    }// </editor-fold>//GEN-END:initComponents

    private void button_logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_logoutActionPerformed
        if(Employees.logout()){
            PIONSView.getInstance().setMain(new Login());
        }
        else{
            JOptionPane.showMessageDialog(this, "Logout was unsuccessful.", "Logout Failed", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_button_logoutActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_logout;
    private javax.swing.JLabel label_directions;
    private javax.swing.JLabel label_name;
    private javax.swing.JLabel label_picture;
    private javax.swing.JLabel label_welcome;
    // End of variables declaration//GEN-END:variables

}


package pions.view;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import pions.controller.Employees;

/**
 *
 * @author George
 */
public class CreateEmployee extends JPanel {

    /** Creates new form CreateEmployee */
    public CreateEmployee() {
        initComponents();
    }

    private void clear(){
        clearFields();
        resetLabels();
    }

    private void clearFields(){
        JTextField[] fields = {field_name,
            field_username,
            field_gmail_username,
            field_password,
            field_password2,
            field_gmail_password,
            field_gmail_password2};

        for(JTextField field: fields){
            clearField(field);
        }
    }

    private void resetLabels(){
        JLabel[] labels = {label_name,
            label_username,
            label_gmail_username,
            label_password,
            label_password2,
            label_gmail_password,
            label_gmail_password2};

        for(JLabel label: labels){
            resetLabel(label);
        }
    }

    private void clearField(JTextField field){
        field.setText("");
    }

    private void resetLabel(JLabel label){
        label.setForeground(Color.BLACK);
    }

    private void markLabel(JLabel label){
        label.setForeground(Color.RED);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        field_name = new javax.swing.JTextField();
        field_username = new javax.swing.JTextField();
        field_gmail_username = new javax.swing.JTextField();
        label_name = new javax.swing.JLabel();
        label_username = new javax.swing.JLabel();
        label_password = new javax.swing.JLabel();
        label_gmail_username = new javax.swing.JLabel();
        label_gmail_password = new javax.swing.JLabel();
        field_password = new javax.swing.JPasswordField();
        field_gmail_password = new javax.swing.JPasswordField();
        button_create_account = new javax.swing.JButton();
        label_directions = new javax.swing.JLabel();
        label_directions2 = new javax.swing.JLabel();
        button_clear = new javax.swing.JButton();
        label_gmail_directions = new javax.swing.JLabel();
        field_password2 = new javax.swing.JPasswordField();
        label_password2 = new javax.swing.JLabel();
        label_gmail_password2 = new javax.swing.JLabel();
        field_gmail_password2 = new javax.swing.JPasswordField();

        setName("Form"); // NOI18N
        setPreferredSize(new java.awt.Dimension(300, 400));

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(pions.PIONS.class).getContext().getResourceMap(CreateEmployee.class);
        field_name.setText(resourceMap.getString("field_name.text")); // NOI18N
        field_name.setName("field_name"); // NOI18N

        field_username.setText(resourceMap.getString("field_username.text")); // NOI18N
        field_username.setName("field_username"); // NOI18N

        field_gmail_username.setText(resourceMap.getString("field_gmail_username.text")); // NOI18N
        field_gmail_username.setName("field_gmail_username"); // NOI18N

        label_name.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        label_name.setText(resourceMap.getString("label_name.text")); // NOI18N
        label_name.setName("label_name"); // NOI18N

        label_username.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        label_username.setText(resourceMap.getString("label_username.text")); // NOI18N
        label_username.setName("label_username"); // NOI18N

        label_password.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        label_password.setText(resourceMap.getString("label_password.text")); // NOI18N
        label_password.setName("label_password"); // NOI18N

        label_gmail_username.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        label_gmail_username.setText(resourceMap.getString("label_gmail_username.text")); // NOI18N
        label_gmail_username.setName("label_gmail_username"); // NOI18N

        label_gmail_password.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        label_gmail_password.setText(resourceMap.getString("label_gmail_password.text")); // NOI18N
        label_gmail_password.setName("label_gmail_password"); // NOI18N

        field_password.setText(resourceMap.getString("field_password.text")); // NOI18N
        field_password.setName("field_password"); // NOI18N

        field_gmail_password.setText(resourceMap.getString("field_gmail_password.text")); // NOI18N
        field_gmail_password.setName("field_gmail_password"); // NOI18N

        button_create_account.setText(resourceMap.getString("button_create_account.text")); // NOI18N
        button_create_account.setName("button_create_account"); // NOI18N
        button_create_account.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_create_accountActionPerformed(evt);
            }
        });

        label_directions.setText(resourceMap.getString("label_directions.text")); // NOI18N
        label_directions.setName("label_directions"); // NOI18N

        label_directions2.setFont(resourceMap.getFont("label_directions2.font")); // NOI18N
        label_directions2.setText(resourceMap.getString("label_directions2.text")); // NOI18N
        label_directions2.setName("label_directions2"); // NOI18N

        button_clear.setText(resourceMap.getString("button_clear.text")); // NOI18N
        button_clear.setName("button_clear"); // NOI18N
        button_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_clearActionPerformed(evt);
            }
        });

        label_gmail_directions.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_gmail_directions.setText(resourceMap.getString("label_gmail_directions.text")); // NOI18N
        label_gmail_directions.setName("label_gmail_directions"); // NOI18N

        field_password2.setName("field_password2"); // NOI18N

        label_password2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        label_password2.setText(resourceMap.getString("label_password2.text")); // NOI18N
        label_password2.setName("label_password2"); // NOI18N

        label_gmail_password2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        label_gmail_password2.setText(resourceMap.getString("label_gmail_password2.text")); // NOI18N
        label_gmail_password2.setName("label_gmail_password2"); // NOI18N

        field_gmail_password2.setName("field_gmail_password2"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label_gmail_directions, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                    .addComponent(label_directions2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                    .addComponent(label_directions, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(label_name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label_username, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label_password, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label_password2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label_gmail_username, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label_gmail_password, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label_gmail_password2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(field_name, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                            .addComponent(field_gmail_password, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                            .addComponent(field_gmail_username, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                            .addComponent(field_username, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                            .addComponent(field_password, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                            .addComponent(field_password2, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                            .addComponent(field_gmail_password2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)))
                    .addComponent(button_create_account, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                    .addComponent(button_clear, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label_directions)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_directions2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_name)
                    .addComponent(field_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(field_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_username))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_password)
                    .addComponent(field_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_password2)
                    .addComponent(field_password2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_gmail_username)
                    .addComponent(field_gmail_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(field_gmail_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_gmail_password))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_gmail_password2)
                    .addComponent(field_gmail_password2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(label_gmail_directions)
                .addGap(11, 11, 11)
                .addComponent(button_create_account, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(button_clear)
                .addContainerGap(34, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void button_create_accountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_create_accountActionPerformed
        boolean error = false;
        resetLabels();

        //Input checking, name can't be blank
        if(field_name.getText().length() == 0){
            JOptionPane.showMessageDialog(this,
                    "Please enter your name.",
                    "Missing Name",
                    JOptionPane.ERROR_MESSAGE);
            markLabel(label_name);
            error = true;
        }

        //Input checking, username can't be blank
        if(field_username.getText().length() == 0){
            JOptionPane.showMessageDialog(this,
                    "Please enter a username.",
                    "Missing Username",
                    JOptionPane.ERROR_MESSAGE);
            markLabel(label_username);
            error = true;
        }

        //Input checking, password can't be blank
        if(field_password.getPassword().length == 0){
            JOptionPane.showMessageDialog(this,
                    "Please enter a password.",
                    "Missing Password",
                    JOptionPane.ERROR_MESSAGE);
            markLabel(label_password);
            error = true;
        }

        //Input checking, password and confirmation must match
        if(!String.valueOf(field_password.getPassword())
                .equals(String.valueOf(field_password2.getPassword()))){
            JOptionPane.showMessageDialog(this,
                    "Your passwords do not match.",
                    "Confirm Password",
                    JOptionPane.ERROR_MESSAGE);
            markLabel(label_password2);
            error = true;
        }

        //Input checking, gmail username can't be blank
        if(field_gmail_username.getText().length() == 0){
            JOptionPane.showMessageDialog(this,
                    "Please enter a Gmail username.",
                    "Missing Gmail Username",
                    JOptionPane.ERROR_MESSAGE);
            markLabel(label_gmail_username);
            error = true;
        }

        //Input checking, gmail password can't be blank
        if(field_gmail_password.getPassword().length == 0){
            JOptionPane.showMessageDialog(this,
                    "Please enter a Gmail password.",
                    "Missing Gmail Password",
                    JOptionPane.ERROR_MESSAGE);
            markLabel(label_gmail_password);
            error = true;
        }

        //Input checking, gmail password and confirmation must match
        if(!String.valueOf(field_gmail_password.getPassword())
                .equals(String.valueOf(field_gmail_password2.getPassword()))){
            JOptionPane.showMessageDialog(this,
                    "Your Gmail passwords do not match.",
                    "Confirm Password",
                    JOptionPane.ERROR_MESSAGE);
            markLabel(label_gmail_password2);
            error = true;
        }

        if(error){
            return;
        }

        if(Employees.createEmployee(field_name.getText(),
                field_username.getText(),
                String.valueOf(field_password.getPassword()),
                field_gmail_username.getText(),
                String.valueOf(field_gmail_password.getPassword()))){
            JOptionPane.showMessageDialog(this, "Employee created successfully.");
            clearFields();
            PIONSView.getInstance().setOtherPanel(this, new LoginSuccess());
            PIONSView.getInstance().setThisPanel(this, new IdleScreen());
        }
        else{
            JOptionPane.showMessageDialog(this,
                    "Employee with that username already exists.",
                    "Duplicate User",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_button_create_accountActionPerformed

    private void button_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_clearActionPerformed
        clear();
    }//GEN-LAST:event_button_clearActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_clear;
    private javax.swing.JButton button_create_account;
    private javax.swing.JPasswordField field_gmail_password;
    private javax.swing.JPasswordField field_gmail_password2;
    private javax.swing.JTextField field_gmail_username;
    private javax.swing.JTextField field_name;
    private javax.swing.JPasswordField field_password;
    private javax.swing.JPasswordField field_password2;
    private javax.swing.JTextField field_username;
    private javax.swing.JLabel label_directions;
    private javax.swing.JLabel label_directions2;
    private javax.swing.JLabel label_gmail_directions;
    private javax.swing.JLabel label_gmail_password;
    private javax.swing.JLabel label_gmail_password2;
    private javax.swing.JLabel label_gmail_username;
    private javax.swing.JLabel label_name;
    private javax.swing.JLabel label_password;
    private javax.swing.JLabel label_password2;
    private javax.swing.JLabel label_username;
    // End of variables declaration//GEN-END:variables

}

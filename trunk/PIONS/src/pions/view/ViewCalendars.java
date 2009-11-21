
package pions.view;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import pions.controller.Calendars;
import pions.controller.Calendars.CalendarType;
import pions.controller.SwapShifts;

/**
 *
 * @author George
 */
public class ViewCalendars extends javax.swing.JPanel {

    private JToggleButton button_auxpanel = null;

    /** Creates new form Calendars */
    public ViewCalendars() {
        initComponents();

        setAllVisible(false);
    }

    private void setAllVisible(boolean visible){
        setAvailabilityVisible(visible);
        setWorkVisible(visible);
        setSubordinateVisible(visible);
        setSwapShiftVisible(visible);
    }

    private void setAvailabilityVisible(boolean visible){
        button_availability_view.setVisible(visible);
        togglebutton_availability_edit.setVisible(visible);
    }

    private void setWorkVisible(boolean visible){
        button_work_view.setVisible(visible);
    }

    private void setSubordinateVisible(boolean visible){
        button_subordinate_view.setVisible(visible);
        togglebutton_subordinate_edit.setVisible(visible);
    }

    private void setSwapShiftVisible(boolean visible){
        button_swapshift_create.setVisible(visible && !SwapShifts.isInit());
        togglebutton_swapshift_edit.setVisible(visible && SwapShifts.isInit());
        button_swapshift_delete.setVisible(visible && SwapShifts.isInit());
    }

    private boolean browserDialog(){
        return JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(this,
                "This operation will open a new internet browser window.",
                "Continue?",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void setAuxPanel(JPanel panel, JToggleButton button){
        if(button_auxpanel != null) button_auxpanel.setSelected(false);
        button_auxpanel = button;

        PIONSView.getInstance().setAux(panel);
    }

    private void resetAuxPanel(){
        if(button_auxpanel != null) button_auxpanel.setSelected(false);
        PIONSView.getInstance().setAux(new IdleScreen());
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
        button_work_view = new javax.swing.JButton();
        button_availability_view = new javax.swing.JButton();
        button_subordinate_view = new javax.swing.JButton();
        button_swapshift_create = new javax.swing.JToggleButton();
        button_work = new javax.swing.JRadioButton();
        button_availability = new javax.swing.JRadioButton();
        button_subordinate = new javax.swing.JRadioButton();
        button_swapshift = new javax.swing.JRadioButton();
        button_swapshift_delete = new javax.swing.JButton();
        togglebutton_availability_edit = new javax.swing.JToggleButton();
        togglebutton_swapshift_edit = new javax.swing.JToggleButton();
        togglebutton_subordinate_edit = new javax.swing.JToggleButton();

        setName("Form"); // NOI18N
        setPreferredSize(new java.awt.Dimension(300, 400));

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(pions.PIONS.class).getContext().getResourceMap(ViewCalendars.class);
        label_title.setFont(resourceMap.getFont("label_title.font")); // NOI18N
        label_title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_title.setText(resourceMap.getString("label_title.text")); // NOI18N
        label_title.setName("label_title"); // NOI18N

        label_directions.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_directions.setText(resourceMap.getString("label_directions.text")); // NOI18N
        label_directions.setName("label_directions"); // NOI18N

        button_work_view.setText(resourceMap.getString("button_work_view.text")); // NOI18N
        button_work_view.setName("button_work_view"); // NOI18N
        button_work_view.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_work_viewActionPerformed(evt);
            }
        });

        button_availability_view.setText(resourceMap.getString("button_availability_view.text")); // NOI18N
        button_availability_view.setName("button_availability_view"); // NOI18N
        button_availability_view.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_availability_viewActionPerformed(evt);
            }
        });

        button_subordinate_view.setText(resourceMap.getString("button_subordinate_view.text")); // NOI18N
        button_subordinate_view.setName("button_subordinate_view"); // NOI18N
        button_subordinate_view.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_subordinate_viewActionPerformed(evt);
            }
        });

        button_swapshift_create.setText(resourceMap.getString("button_swapshift_create.text")); // NOI18N
        button_swapshift_create.setName("button_swapshift_create"); // NOI18N
        button_swapshift_create.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_swapshift_createActionPerformed(evt);
            }
        });

        button_work.setFont(resourceMap.getFont("jRadioButton3.font")); // NOI18N
        button_work.setText(resourceMap.getString("button_work.text")); // NOI18N
        button_work.setName("button_work"); // NOI18N
        button_work.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_workActionPerformed(evt);
            }
        });

        button_availability.setFont(resourceMap.getFont("jRadioButton3.font")); // NOI18N
        button_availability.setText(resourceMap.getString("button_availability.text")); // NOI18N
        button_availability.setName("button_availability"); // NOI18N
        button_availability.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_availabilityActionPerformed(evt);
            }
        });

        button_subordinate.setFont(resourceMap.getFont("jRadioButton3.font")); // NOI18N
        button_subordinate.setText(resourceMap.getString("button_subordinate.text")); // NOI18N
        button_subordinate.setName("button_subordinate"); // NOI18N
        button_subordinate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_subordinateActionPerformed(evt);
            }
        });

        button_swapshift.setFont(resourceMap.getFont("jRadioButton3.font")); // NOI18N
        button_swapshift.setText(resourceMap.getString("button_swapshift.text")); // NOI18N
        button_swapshift.setName("button_swapshift"); // NOI18N
        button_swapshift.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_swapshiftActionPerformed(evt);
            }
        });

        button_swapshift_delete.setText(resourceMap.getString("button_swapshift_delete.text")); // NOI18N
        button_swapshift_delete.setName("button_swapshift_delete"); // NOI18N
        button_swapshift_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_swapshift_deleteActionPerformed(evt);
            }
        });

        togglebutton_availability_edit.setText(resourceMap.getString("togglebutton_availability_edit.text")); // NOI18N
        togglebutton_availability_edit.setName("togglebutton_availability_edit"); // NOI18N
        togglebutton_availability_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                togglebutton_availability_editActionPerformed(evt);
            }
        });

        togglebutton_swapshift_edit.setText(resourceMap.getString("togglebutton_swapshift_edit.text")); // NOI18N
        togglebutton_swapshift_edit.setName("togglebutton_swapshift_edit"); // NOI18N
        togglebutton_swapshift_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                togglebutton_swapshift_editActionPerformed(evt);
            }
        });

        togglebutton_subordinate_edit.setText(resourceMap.getString("togglebutton_subordinate_edit.text")); // NOI18N
        togglebutton_subordinate_edit.setName("togglebutton_subordinate_edit"); // NOI18N
        togglebutton_subordinate_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                togglebutton_subordinate_editActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label_title, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
            .addComponent(label_directions, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(button_swapshift_create)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(togglebutton_swapshift_edit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(button_swapshift_delete))
                    .addComponent(button_swapshift))
                .addContainerGap(74, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(button_subordinate_view)
                        .addGap(18, 18, 18)
                        .addComponent(togglebutton_subordinate_edit))
                    .addComponent(button_subordinate))
                .addContainerGap(87, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(button_work_view))
                    .addComponent(button_work))
                .addContainerGap(151, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(button_availability_view)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(togglebutton_availability_edit))
                    .addComponent(button_availability))
                .addContainerGap(157, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(label_title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_directions)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(button_availability, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button_availability_view)
                    .addComponent(togglebutton_availability_edit))
                .addGap(7, 7, 7)
                .addComponent(button_work, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button_work_view)
                .addGap(7, 7, 7)
                .addComponent(button_subordinate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(togglebutton_subordinate_edit)
                    .addComponent(button_subordinate_view))
                .addGap(7, 7, 7)
                .addComponent(button_swapshift)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button_swapshift_create)
                    .addComponent(togglebutton_swapshift_edit)
                    .addComponent(button_swapshift_delete))
                .addContainerGap(70, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void button_work_viewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_work_viewActionPerformed
        try{
            URI link = Calendars.getReadLink(CalendarType.WorkSchedule);
            if(link != null && browserDialog()) Desktop.getDesktop().browse(link);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_button_work_viewActionPerformed

    private void button_availability_viewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_availability_viewActionPerformed
        if(browserDialog()){
            try{
                URI link = Calendars.getReadLink(CalendarType.Availability);
                if(link != null && browserDialog()) Desktop.getDesktop().browse(link);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_button_availability_viewActionPerformed

    private void button_subordinate_viewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_subordinate_viewActionPerformed
        try{
            URI link = Calendars.getReadLink(CalendarType.SubordinateSchedule);
            if(link != null && browserDialog()) Desktop.getDesktop().browse(link);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_button_subordinate_viewActionPerformed

    private void button_swapshift_createActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_swapshift_createActionPerformed
        //TODO create swap shifts
        throw new UnsupportedOperationException("Not supported yet.");
    }//GEN-LAST:event_button_swapshift_createActionPerformed

    private void button_availabilityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_availabilityActionPerformed
        setAvailabilityVisible(button_availability.isSelected());
    }//GEN-LAST:event_button_availabilityActionPerformed

    private void button_workActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_workActionPerformed
        setWorkVisible(button_work.isSelected());
    }//GEN-LAST:event_button_workActionPerformed

    private void button_subordinateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_subordinateActionPerformed
        setSubordinateVisible(button_subordinate.isSelected());
    }//GEN-LAST:event_button_subordinateActionPerformed

    private void button_swapshiftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_swapshiftActionPerformed
        setSwapShiftVisible(button_swapshift.isSelected());
    }//GEN-LAST:event_button_swapshiftActionPerformed

    private void togglebutton_availability_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_togglebutton_availability_editActionPerformed
        if(togglebutton_availability_edit.isSelected()){
            setAuxPanel(new EditAvailability(), togglebutton_availability_edit);
        }
        else {
            resetAuxPanel();
        }
    }//GEN-LAST:event_togglebutton_availability_editActionPerformed

    private void togglebutton_subordinate_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_togglebutton_subordinate_editActionPerformed
        if(togglebutton_subordinate_edit.isSelected()){
            setAuxPanel(new EditSchedule(), togglebutton_subordinate_edit);
        }
        else {
            resetAuxPanel();
        }
    }//GEN-LAST:event_togglebutton_subordinate_editActionPerformed

    private void togglebutton_swapshift_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_togglebutton_swapshift_editActionPerformed
        if(togglebutton_swapshift_edit.isSelected()){
            setAuxPanel(new EditSwapShift(), togglebutton_swapshift_edit);
        }
        else {
            resetAuxPanel();
        }
    }//GEN-LAST:event_togglebutton_swapshift_editActionPerformed

    private void button_swapshift_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_swapshift_deleteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_swapshift_deleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton button_availability;
    private javax.swing.JButton button_availability_view;
    private javax.swing.JRadioButton button_subordinate;
    private javax.swing.JButton button_subordinate_view;
    private javax.swing.JRadioButton button_swapshift;
    private javax.swing.JToggleButton button_swapshift_create;
    private javax.swing.JButton button_swapshift_delete;
    private javax.swing.JRadioButton button_work;
    private javax.swing.JButton button_work_view;
    private javax.swing.JLabel label_directions;
    private javax.swing.JLabel label_title;
    private javax.swing.JToggleButton togglebutton_availability_edit;
    private javax.swing.JToggleButton togglebutton_subordinate_edit;
    private javax.swing.JToggleButton togglebutton_swapshift_edit;
    // End of variables declaration//GEN-END:variables

}

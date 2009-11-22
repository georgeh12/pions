
package pions.view.alerts;

import pions.view.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import pions.controller.xml.AlertIterator;
import pions.controller.Alerts;

/**
 *
 * @author George
 */
public class SavedAlertIList extends AbstractAlertsIList {

    /** Creates new form SavedAlerts */
    public SavedAlertIList(AlertIterator iter) {
        initComponents();

        super.panel_display = panel_display;
        super.set(iter);
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
        button_view = new javax.swing.JButton();
        button_delete = new javax.swing.JButton();
        button_select_all = new javax.swing.JButton();
        label_select = new javax.swing.JLabel();
        button_select_none = new javax.swing.JButton();
        scrollpane_display = new javax.swing.JScrollPane();
        panel_display = new javax.swing.JPanel();

        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(pions.PIONS.class).getContext().getResourceMap(SavedAlertIList.class);
        label_title.setFont(resourceMap.getFont("label_title.font")); // NOI18N
        label_title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_title.setText(resourceMap.getString("label_title.text")); // NOI18N
        label_title.setName("label_title"); // NOI18N

        label_directions.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_directions.setText(resourceMap.getString("label_directions.text")); // NOI18N
        label_directions.setName("label_directions"); // NOI18N

        button_view.setText(resourceMap.getString("button_view.text")); // NOI18N
        button_view.setName("button_view"); // NOI18N
        button_view.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_viewActionPerformed(evt);
            }
        });

        button_delete.setText(resourceMap.getString("button_delete.text")); // NOI18N
        button_delete.setName("button_delete"); // NOI18N
        button_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_deleteActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout panel_displayLayout = new javax.swing.GroupLayout(panel_display);
        panel_display.setLayout(panel_displayLayout);
        panel_displayLayout.setHorizontalGroup(
            panel_displayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 278, Short.MAX_VALUE)
        );
        panel_displayLayout.setVerticalGroup(
            panel_displayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
        );

        scrollpane_display.setViewportView(panel_display);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label_title, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label_directions, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(button_view)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(button_delete))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(label_select)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button_select_all)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(button_select_none)))
                .addContainerGap(143, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollpane_display, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(label_title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(label_directions)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button_view)
                    .addComponent(button_delete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_select)
                    .addComponent(button_select_all)
                    .addComponent(button_select_none))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollpane_display, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void button_viewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_viewActionPerformed
        int index = super.getFirstIndex();

        if(index != -1){
            new DisplayAlert(Alerts.getSavedAlert(index));
        }
        else{
            JOptionPane.showMessageDialog(this, "Please select an index.", "No Index Selected", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_button_viewActionPerformed

    private void button_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_deleteActionPerformed
        ArrayList<Integer> indices = super.getIndices();

        for(int i: indices){
            Alerts.deleteSavedAlert(i);
        }
    }//GEN-LAST:event_button_deleteActionPerformed

    private void button_select_allActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_select_allActionPerformed
        super.selectAll();
    }//GEN-LAST:event_button_select_allActionPerformed

    private void button_select_noneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_select_noneActionPerformed
        super.selectNone();
    }//GEN-LAST:event_button_select_noneActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_delete;
    private javax.swing.JButton button_select_all;
    private javax.swing.JButton button_select_none;
    private javax.swing.JButton button_view;
    private javax.swing.JLabel label_directions;
    private javax.swing.JLabel label_select;
    private javax.swing.JLabel label_title;
    private javax.swing.JPanel panel_display;
    private javax.swing.JScrollPane scrollpane_display;
    // End of variables declaration//GEN-END:variables

}

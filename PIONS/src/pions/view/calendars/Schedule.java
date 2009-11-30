
package pions.view.calendars;

import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.SpinnerDateModel;
import pions.controller.Calendars;

/**
 *
 * 
 */
public class Schedule extends AbstractCalendarList {

    /** Creates new form EditAvailabilityCalendar */
    public Schedule() {
        initComponents();

        init(field_name, field_gmail, togglebutton_contacts,
                combobox_delete, button_delete,
                button_add);
    }

    @Override
    protected final void clear(){
        field_name.setText("");
        field_gmail.setText("");
        field_position.setText("");
    }

    @Override
    protected final void addEvent() {
        Date start_date = (Date)((SpinnerDateModel)spinner_start.getModel()).getValue();
        Date end_date = (Date)((SpinnerDateModel)spinner_end.getModel()).getValue();
        Calendars.addScheduleShift(field_gmail.getText(),
                field_name.getText(),
                field_position.getText(),
                start_date,
                end_date);

        display(Calendars.getScheduleShifts());
    }

    @Override
    protected final void deleteEvent(int index) {
        Calendars.deleteScheduleShift(index);
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
        button_add = new javax.swing.JButton();
        spinner_start = new javax.swing.JSpinner();
        spinner_end = new javax.swing.JSpinner();
        togglebutton_contacts = new javax.swing.JToggleButton();
        field_name = new javax.swing.JTextField();
        field_gmail = new javax.swing.JTextField();
        label_name = new javax.swing.JLabel();
        label_gmail = new javax.swing.JLabel();
        label_add_directions = new javax.swing.JLabel();
        label_start = new javax.swing.JLabel();
        label_end = new javax.swing.JLabel();
        combobox_delete = new javax.swing.JComboBox();
        button_delete = new javax.swing.JButton();
        label_delete_directions = new javax.swing.JLabel();
        label_position = new javax.swing.JLabel();
        field_position = new javax.swing.JTextField();
        button_send = new javax.swing.JButton();

        setName("Form"); // NOI18N
        setPreferredSize(new java.awt.Dimension(300, 400));

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(pions.PIONS.class).getContext().getResourceMap(Schedule.class);
        label_title.setFont(resourceMap.getFont("label_title.font")); // NOI18N
        label_title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_title.setText(resourceMap.getString("label_title.text")); // NOI18N
        label_title.setName("label_title"); // NOI18N

        button_add.setText(resourceMap.getString("button_add.text")); // NOI18N
        button_add.setName("button_add"); // NOI18N

        spinner_start.setModel(new javax.swing.SpinnerDateModel());
        spinner_start.setName("spinner_start"); // NOI18N

        spinner_end.setModel(new javax.swing.SpinnerDateModel());
        spinner_end.setName("spinner_end"); // NOI18N

        togglebutton_contacts.setText(resourceMap.getString("togglebutton_contacts.text")); // NOI18N
        togglebutton_contacts.setName("togglebutton_contacts"); // NOI18N

        field_name.setEditable(false);
        field_name.setName("field_name"); // NOI18N

        field_gmail.setEditable(false);
        field_gmail.setName("field_gmail"); // NOI18N

        label_name.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        label_name.setText(resourceMap.getString("label_name.text")); // NOI18N
        label_name.setName("label_name"); // NOI18N

        label_gmail.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        label_gmail.setText(resourceMap.getString("label_gmail.text")); // NOI18N
        label_gmail.setName("label_gmail"); // NOI18N

        label_add_directions.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_add_directions.setText(resourceMap.getString("label_add_directions.text")); // NOI18N
        label_add_directions.setName("label_add_directions"); // NOI18N

        label_start.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        label_start.setText(resourceMap.getString("label_start.text")); // NOI18N
        label_start.setName("label_start"); // NOI18N

        label_end.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        label_end.setText(resourceMap.getString("label_end.text")); // NOI18N
        label_end.setName("label_end"); // NOI18N

        combobox_delete.setMaximumRowCount(10);
        combobox_delete.setName("combobox_delete"); // NOI18N

        button_delete.setText(resourceMap.getString("button_delete.text")); // NOI18N
        button_delete.setName("button_delete"); // NOI18N

        label_delete_directions.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_delete_directions.setText(resourceMap.getString("label_delete_directions.text")); // NOI18N
        label_delete_directions.setName("label_delete_directions"); // NOI18N

        label_position.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        label_position.setText(resourceMap.getString("label_position.text")); // NOI18N
        label_position.setName("label_position"); // NOI18N

        field_position.setText(resourceMap.getString("field_position.text")); // NOI18N
        field_position.setName("field_position"); // NOI18N

        button_send.setText(resourceMap.getString("button_send.text")); // NOI18N
        button_send.setName("button_send"); // NOI18N
        button_send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_sendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(label_add_directions, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(togglebutton_contacts, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(label_gmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(label_name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(field_gmail, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                                    .addComponent(field_name, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)))))
                    .addComponent(label_title, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(label_position, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(field_position, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(label_end, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label_start, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spinner_end, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                            .addComponent(spinner_start, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button_add))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(label_delete_directions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(combobox_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(button_delete))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(button_send, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(label_title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(label_add_directions)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(field_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_name))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_gmail)
                    .addComponent(field_gmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(togglebutton_contacts)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(field_position, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_position))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_start)
                            .addComponent(spinner_start, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_end)
                            .addComponent(spinner_end, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(button_add, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(label_delete_directions)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combobox_delete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_delete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(button_send, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void button_sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_sendActionPerformed
        Calendars.sendWorkSchedule();
        JOptionPane.showConfirmDialog(this,
                "A Work Schedule Alert has been sent to your subordinates.",
                "Work Schedule Sent",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_button_sendActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_add;
    private javax.swing.JButton button_delete;
    private javax.swing.JButton button_send;
    private javax.swing.JComboBox combobox_delete;
    private javax.swing.JTextField field_gmail;
    private javax.swing.JTextField field_name;
    private javax.swing.JTextField field_position;
    private javax.swing.JLabel label_add_directions;
    private javax.swing.JLabel label_delete_directions;
    private javax.swing.JLabel label_end;
    private javax.swing.JLabel label_gmail;
    private javax.swing.JLabel label_name;
    private javax.swing.JLabel label_position;
    private javax.swing.JLabel label_start;
    private javax.swing.JLabel label_title;
    private javax.swing.JSpinner spinner_end;
    private javax.swing.JSpinner spinner_start;
    private javax.swing.JToggleButton togglebutton_contacts;
    // End of variables declaration//GEN-END:variables

}


package pions.view;

/**
 *
 * @author George
 */
public class AboutUsAux extends javax.swing.JPanel {

    /** Creates new form AbousUsAux */
    public AboutUsAux() {
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

        label_picture = new javax.swing.JLabel();

        setName("Form"); // NOI18N
        setPreferredSize(new java.awt.Dimension(300, 400));

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(pions.PIONS.class).getContext().getResourceMap(AboutUsAux.class);
        label_picture.setFont(resourceMap.getFont("label_picture.font")); // NOI18N
        label_picture.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_picture.setIcon(resourceMap.getIcon("label_picture.icon")); // NOI18N
        label_picture.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        label_picture.setIconTextGap(0);
        label_picture.setName("label_picture"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label_picture, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label_picture, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel label_picture;
    // End of variables declaration//GEN-END:variables

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othr_jframes;
/**
 *
 * @author dvchinx_
 */
public class Settings_DA extends javax.swing.JFrame {
    
    public String URLBtn10;
    public String URLBtn20;
    public String URLBtn30;
    public String URLBtn40;
    
    private int xMouse;
    private int yMouse;
    
    public Settings_DA() {
        initComponents();
        setAlwaysOnTop(true);
    }

    public String getqueBusco(String queBusco){
        return queBusco;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtSettDA = new javax.swing.JLabel();
        cmbSettDA = new javax.swing.JComboBox<>();
        txt_Url = new javax.swing.JTextField();
        btnSettDA = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel2MouseDragged(evt);
            }
        });
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel2MousePressed(evt);
            }
        });
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("X");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 23, 28));

        txtSettDA.setFont(new java.awt.Font("Corbel", 1, 18)); // NOI18N
        txtSettDA.setForeground(new java.awt.Color(0, 0, 0));
        txtSettDA.setText("Configurar Botón");
        txtSettDA.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel2.add(txtSettDA, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 40));

        cmbSettDA.setBackground(new java.awt.Color(240, 240, 240));
        cmbSettDA.setForeground(new java.awt.Color(0, 0, 0));
        cmbSettDA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Botón 1", "Botón 2", "Botón 3", "Botón 4" }));
        cmbSettDA.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(cmbSettDA, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, 110, 40));

        txt_Url.setBackground(new java.awt.Color(255, 255, 255));
        txt_Url.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        txt_Url.setForeground(new java.awt.Color(102, 102, 102));
        txt_Url.setText("Url");
        txt_Url.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_Url.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_UrlMouseClicked(evt);
            }
        });
        jPanel1.add(txt_Url, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 180, 30));

        btnSettDA.setBackground(new java.awt.Color(204, 204, 204));
        btnSettDA.setForeground(new java.awt.Color(0, 0, 0));
        btnSettDA.setText("Guardar");
        btnSettDA.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnSettDA.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSettDA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSettDAActionPerformed(evt);
            }
        });
        jPanel1.add(btnSettDA, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 230, 110, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 310));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_UrlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_UrlMouseClicked
        this.txt_Url.setText("");
    }//GEN-LAST:event_txt_UrlMouseClicked

    private void btnSettDAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSettDAActionPerformed
        String TomarComboSearch = (String) cmbSettDA.getSelectedItem();
        System.out.println("Se selecciono: " + TomarComboSearch);

        switch (TomarComboSearch) {
            case "Boton 1":
            URLBtn10 = txt_Url.getText();
            break;
            case "Boton 2":
            URLBtn20 = txt_Url.getText();
            break;
            case "Boton 3":
            URLBtn30 = txt_Url.getText();
            break;
            case "Boton 4":
            URLBtn40 = txt_Url.getText();
            break;
        }
    }//GEN-LAST:event_btnSettDAActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jPanel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_jPanel2MousePressed

    private void jPanel2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();

        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_jPanel2MouseDragged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btnSettDA;
    public static javax.swing.JComboBox<String> cmbSettDA;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    public static javax.swing.JLabel txtSettDA;
    public static javax.swing.JTextField txt_Url;
    // End of variables declaration//GEN-END:variables
}

package othr_jframes;

import java.awt.Color;
import principal.Interfaz;
/**
 *
 * @author dvchinx_
 */
public class Settings_Calc extends javax.swing.JFrame {

    private int xMouse, yMouse;
    private final Color Blanco = new Color(255, 255, 255);
    private final Color BlancoOscuro = new Color(230, 230, 230);
    private final Color Gris = new Color(204,204,204);
    private final Color GrisOscuro = new Color(153,153,153);
    private final Color Negro = new Color(0, 0, 0);
    private final Color NegroClaro = new Color(50, 50, 50);

    public Settings_Calc() {
        initComponents();
        setAlwaysOnTop(true);
    }

    public void CambiarTema(Color colorBg, Color colorBg1, Color colorTxt) {
        Interfaz.btnC.setBackground(colorBg);
        Interfaz.btnPI.setBackground(colorBg);
        Interfaz.btnExp.setBackground(colorBg);
        Interfaz.btn0.setBackground(colorBg);
        Interfaz.btn1.setBackground(colorBg);
        Interfaz.btn2.setBackground(colorBg);
        Interfaz.btn3.setBackground(colorBg);
        Interfaz.btn4.setBackground(colorBg);
        Interfaz.btn5.setBackground(colorBg);
        Interfaz.btn6.setBackground(colorBg);
        Interfaz.btn7.setBackground(colorBg);
        Interfaz.btn8.setBackground(colorBg);
        Interfaz.btn9.setBackground(colorBg);
        Interfaz.btnDiv.setBackground(colorBg);
        Interfaz.btnNeg.setBackground(colorBg);
        Interfaz.btnFor.setBackground(colorBg);
        Interfaz.btnSum.setBackground(colorBg);
        Interfaz.btnRest.setBackground(colorBg);
        Interfaz.btnSqr2.setBackground(colorBg);
        Interfaz.btnSqr3.setBackground(colorBg);
        Interfaz.btnDot.setBackground(colorBg);
        Interfaz.btnEqual.setBackground(colorBg);
        
        Interfaz.valsTxt.setBackground(colorBg1);
        Interfaz.txtNumSqrt.setBackground(colorBg1);
        Interfaz.txtNumRaiz.setBackground(colorBg1);
        Interfaz.btnSqrPrz.setBackground(colorBg);
        
        Interfaz.valsTxt.setForeground(colorTxt);
        Interfaz.txtNumSqrt.setForeground(colorTxt);
        Interfaz.txtNumRaiz.setForeground(colorTxt);
        Interfaz.btnSqrPrz.setForeground(colorTxt);

        Interfaz.btnC.setForeground(colorTxt);
        Interfaz.btnPI.setForeground(colorTxt);
        Interfaz.btnExp.setForeground(colorTxt);
        Interfaz.btn0.setForeground(colorTxt);
        Interfaz.btn1.setForeground(colorTxt);
        Interfaz.btn2.setForeground(colorTxt);
        Interfaz.btn3.setForeground(colorTxt);
        Interfaz.btn4.setForeground(colorTxt);
        Interfaz.btn5.setForeground(colorTxt);
        Interfaz.btn6.setForeground(colorTxt);
        Interfaz.btn7.setForeground(colorTxt);
        Interfaz.btn8.setForeground(colorTxt);
        Interfaz.btn9.setForeground(colorTxt);
        Interfaz.btnDiv.setForeground(colorTxt);
        Interfaz.btnNeg.setForeground(colorTxt);
        Interfaz.btnFor.setForeground(colorTxt);
        Interfaz.btnSum.setForeground(colorTxt);
        Interfaz.btnRest.setForeground(colorTxt);
        Interfaz.btnSqr2.setForeground(colorTxt);
        Interfaz.btnSqr3.setForeground(colorTxt);
        Interfaz.btnDot.setForeground(colorTxt);
        Interfaz.btnEqual.setForeground(colorTxt);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cmbSettCalc = new javax.swing.JComboBox<>();
        btnSettCalc = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtSettCalc = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cmbSettCalc.setBackground(new java.awt.Color(240, 240, 240));
        cmbSettCalc.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        cmbSettCalc.setForeground(new java.awt.Color(0, 0, 0));
        cmbSettCalc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Claro", "Gris", "Oscuro" }));
        cmbSettCalc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(cmbSettCalc, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 190, 40));

        btnSettCalc.setBackground(new java.awt.Color(204, 204, 204));
        btnSettCalc.setForeground(new java.awt.Color(0, 0, 0));
        btnSettCalc.setText("Guardar");
        btnSettCalc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnSettCalc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSettCalc.setFocusPainted(false);
        btnSettCalc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSettCalcActionPerformed(evt);
            }
        });
        jPanel1.add(btnSettCalc, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 190, 110, 30));

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

        txtSettCalc.setFont(new java.awt.Font("Corbel", 1, 18)); // NOI18N
        txtSettCalc.setForeground(new java.awt.Color(0, 0, 0));
        txtSettCalc.setText("Configurar Tema");
        txtSettCalc.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel2.add(txtSettCalc, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 310));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jPanel2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();

        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_jPanel2MouseDragged

    private void jPanel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_jPanel2MousePressed

    private void btnSettCalcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSettCalcActionPerformed
        int TomarComboTemas = cmbSettCalc.getSelectedIndex();

        switch (TomarComboTemas) {
            case 0:
                CambiarTema(Blanco,BlancoOscuro,Negro);
                break;
            case 1:
                CambiarTema(Gris,GrisOscuro,Negro);
                break;
            case 2:
                CambiarTema(Negro,NegroClaro, Blanco);

        }

    }//GEN-LAST:event_btnSettCalcActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btnSettCalc;
    public static javax.swing.JComboBox<String> cmbSettCalc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    public static javax.swing.JLabel txtSettCalc;
    // End of variables declaration//GEN-END:variables
}

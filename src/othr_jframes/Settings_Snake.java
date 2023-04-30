package othr_jframes;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import principal.GamePanel;

import principal.Interfaz;

/**
 *
 * @author dvchinx_
 */
public class Settings_Snake extends javax.swing.JFrame {

    private int xMouse, yMouse;
    private int btnPressed;
    private int pts;
    private static int aVal, bVal, cVal;

    public static String lang = Interfaz.lang;
    public static String Act = "Actualizado Correctamente.", NoAct = "No tienes suficiente dinero.";

    public Settings_Snake() {
        initComponents();
        setAlwaysOnTop(false);

        pts = GamePanel.leerValor("pts.txt");
        System.out.println("pts = " + pts);

//        crearArchivo("RegSkins.txt");
        leerArchivo("RegSkins.txt");
        verfTexts();
    }

    public static void verfTexts() {
        if (aVal == 1) {
            langTxts(1);
        }
        if (bVal == 1) {
            langTxts(2);
        }
        if (cVal == 1) {
            langTxts(3);
        }
        if (aVal == 0) {
            langTxts(0);
        }
        if (bVal == 0) {
            langTxts(0);
        }
        if (cVal == 0) {
            langTxts(0);
        }
    }

    private void crearArchivo(String filename) {
        try {
            FileWriter fileWriter = new FileWriter(filename);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(0 + "\n");
            bufferedWriter.write(0 + "\n");
            bufferedWriter.write(0 + "\n");
            bufferedWriter.close();
            System.out.println("Los enteros han sido escritos en el archivo.");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo.");
            e.printStackTrace();
        }
    }

    private void leerArchivo(String filename) {
        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            aVal = Integer.parseInt(bufferedReader.readLine());
            bVal = Integer.parseInt(bufferedReader.readLine());
            cVal = Integer.parseInt(bufferedReader.readLine());
            bufferedReader.close();
            System.out.println("Los enteros leídos desde el archivo son: " + aVal + ", " + bVal + ", " + cVal);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo.");
            e.printStackTrace();
        }
    }

    private void escribirArchivo(String filename) {
        try {
            FileWriter fileWriter = new FileWriter(filename);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(aVal + "\n");
            bufferedWriter.write(bVal + "\n");
            bufferedWriter.write(cVal + "\n");
            bufferedWriter.close();
            System.out.println("Los enteros han sido actualizados en el archivo: " + aVal + bVal + cVal);
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo.");
            e.printStackTrace();
        }
    }

    private static void langTxts(int btnPressed) {
        switch (lang) {
            case "es":
                switch (btnPressed) {
                    case 1:
                        txtSnakeBlue.setText("Clásico");
                        break;
                    case 2:
                        txtSnakeWorm.setText("Gusano");
                        break;
                    case 3:
                        txtSnakeRGB.setText("RGB");
                        break;
                    default:
                        txtSnakeBasic.setText("Básico");
                        break;
                }
                break;

            case "en":
                switch (btnPressed) {
                    case 1:
                        txtSnakeBlue.setText("Classic");
                        break;
                    case 2:
                        txtSnakeWorm.setText("Worm");
                        break;
                    case 3:
                        txtSnakeRGB.setText("RGB");
                        break;
                    default:
                        txtSnakeBasic.setText("Basic");
                        break;
                }
                break;

            case "pg":
                switch (btnPressed) {
                    case 1:
                        txtSnakeBlue.setText("Clássico");
                        break;
                    case 2:
                        txtSnakeWorm.setText("Minhoca");
                        break;
                    case 3:
                        txtSnakeRGB.setText("RGB");
                        break;
                    default:
                        txtSnakeBasic.setText("Básico");
                        break;
                }
                break;
        }
    }

    private void btnSelected(JLabel lblBtn) {
        txtBtnDefaultSkin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtBtnDefaultSkin1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtBtnDefaultSkin2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtBtnDefaultSkin3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtBtnDefaultSkin4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtBtnDefaultSkin5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnSkinSnake = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtSettCalc = new javax.swing.JLabel();
        txtBtnDefaultSkin = new javax.swing.JLabel();
        txtBtnDefaultSkin1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtSnakeWorm = new javax.swing.JLabel();
        txtBtnDefaultSkin2 = new javax.swing.JLabel();
        txtSnakeBlue = new javax.swing.JLabel();
        txtBtnDefaultSkin3 = new javax.swing.JLabel();
        txtBtnDefaultSkin4 = new javax.swing.JLabel();
        txtBtnDefaultSkin5 = new javax.swing.JLabel();
        txtSnakeBasic = new javax.swing.JLabel();
        txtSnakeRGB = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnSkinSnake.setBackground(new java.awt.Color(204, 204, 204));
        btnSkinSnake.setForeground(new java.awt.Color(0, 0, 0));
        btnSkinSnake.setText("Actualizar");
        btnSkinSnake.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnSkinSnake.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSkinSnake.setFocusPainted(false);
        btnSkinSnake.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSkinSnakeActionPerformed(evt);
            }
        });
        jPanel1.add(btnSkinSnake, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 270, 110, 30));

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
        txtSettCalc.setText("Textura de la Serpiente");
        txtSettCalc.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel2.add(txtSettCalc, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 220, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 40));

        txtBtnDefaultSkin.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtBtnDefaultSkin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs_Snake/skins/textureSnakeBasic.png"))); // NOI18N
        txtBtnDefaultSkin.setText(".");
        txtBtnDefaultSkin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtBtnDefaultSkin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtBtnDefaultSkin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtBtnDefaultSkinMouseClicked(evt);
            }
        });
        jPanel1.add(txtBtnDefaultSkin, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 48, 48));

        txtBtnDefaultSkin1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtBtnDefaultSkin1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs_Snake/skins/textureSnakeBasicBlue.png"))); // NOI18N
        txtBtnDefaultSkin1.setText(".");
        txtBtnDefaultSkin1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtBtnDefaultSkin1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtBtnDefaultSkin1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtBtnDefaultSkin1MouseClicked(evt);
            }
        });
        jPanel1.add(txtBtnDefaultSkin1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 48, 48));

        jLabel2.setFont(new java.awt.Font("Lucida Sans", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("(NoN)");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 210, 70, -1));

        txtSnakeWorm.setFont(new java.awt.Font("Lucida Sans", 0, 12)); // NOI18N
        txtSnakeWorm.setForeground(new java.awt.Color(0, 0, 0));
        txtSnakeWorm.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtSnakeWorm.setText("120 pts");
        jPanel1.add(txtSnakeWorm, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 120, 70, -1));

        txtBtnDefaultSkin2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtBtnDefaultSkin2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs_Snake/skins/textureSnakeBasicWorm.png"))); // NOI18N
        txtBtnDefaultSkin2.setText(".");
        txtBtnDefaultSkin2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtBtnDefaultSkin2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtBtnDefaultSkin2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtBtnDefaultSkin2MouseClicked(evt);
            }
        });
        jPanel1.add(txtBtnDefaultSkin2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, 48, 48));

        txtSnakeBlue.setFont(new java.awt.Font("Lucida Sans", 0, 12)); // NOI18N
        txtSnakeBlue.setForeground(new java.awt.Color(0, 0, 0));
        txtSnakeBlue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtSnakeBlue.setText("120 pts");
        jPanel1.add(txtSnakeBlue, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 70, -1));

        txtBtnDefaultSkin3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtBtnDefaultSkin3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs_Snake/skins/textureSnakeRGB.png"))); // NOI18N
        txtBtnDefaultSkin3.setText(".");
        txtBtnDefaultSkin3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtBtnDefaultSkin3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtBtnDefaultSkin3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtBtnDefaultSkin3MouseClicked(evt);
            }
        });
        jPanel1.add(txtBtnDefaultSkin3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 48, 48));

        txtBtnDefaultSkin4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtBtnDefaultSkin4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs_Snake/skins/textureSnakeNon.png"))); // NOI18N
        txtBtnDefaultSkin4.setText(".");
        txtBtnDefaultSkin4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtBtnDefaultSkin4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtBtnDefaultSkin4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtBtnDefaultSkin4MouseClicked(evt);
            }
        });
        jPanel1.add(txtBtnDefaultSkin4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, 48, 48));

        txtBtnDefaultSkin5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtBtnDefaultSkin5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs_Snake/skins/textureSnakeNon.png"))); // NOI18N
        txtBtnDefaultSkin5.setText(".");
        txtBtnDefaultSkin5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtBtnDefaultSkin5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtBtnDefaultSkin5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtBtnDefaultSkin5MouseClicked(evt);
            }
        });
        jPanel1.add(txtBtnDefaultSkin5, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 160, 48, 48));

        txtSnakeBasic.setFont(new java.awt.Font("Lucida Sans", 0, 12)); // NOI18N
        txtSnakeBasic.setForeground(new java.awt.Color(0, 0, 0));
        txtSnakeBasic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtSnakeBasic.setText("Básico");
        jPanel1.add(txtSnakeBasic, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 70, -1));

        txtSnakeRGB.setFont(new java.awt.Font("Lucida Sans", 0, 12)); // NOI18N
        txtSnakeRGB.setForeground(new java.awt.Color(0, 0, 0));
        txtSnakeRGB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtSnakeRGB.setText("300 pts");
        jPanel1.add(txtSnakeRGB, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 70, -1));

        jLabel7.setFont(new java.awt.Font("Lucida Sans", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("(NoN)");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 210, 70, -1));

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

    private void btnSkinSnakeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSkinSnakeActionPerformed
        pts = GamePanel.leerValor("pts.txt");
        switch (btnPressed) {
            case 0: //Default
                GamePanel.ColorHead = new Color(25, 156, 0);
                GamePanel.ColorBody = new Color(43, 255, 0);
                
                GamePanel.rgb = false;

                Interfaz.btnSnakeRestart.doClick();
                JOptionPane.showMessageDialog(null, Act);
                break;

            case 1: //Azul
                if (pts >= 120 || aVal == 1) {
                    System.out.println("Azul");
                    langTxts(btnPressed);
                    GamePanel.ColorHead = new Color(28, 71, 139);
                    GamePanel.ColorBody = new Color(51, 218, 255);

                    GamePanel.rgb = false;
                    Interfaz.btnSnakeRestart.doClick();

                    if (aVal == 0) {
                        aVal = 1;
                        escribirArchivo("RegSkins.txt");
                        GamePanel.guardarValor(pts - 120, "pts.txt");
                        Interfaz.btnSnakeRestart.doClick();
                    }
                    JOptionPane.showMessageDialog(null, Act);
                } else {
                    JOptionPane.showMessageDialog(null, NoAct);
                }
                break;

            case 2: //Worm
                if (pts >= 120 || bVal == 1) {
                    System.out.println("Worm");
                    langTxts(btnPressed);
                    GamePanel.ColorHead = new Color(133, 0, 0);
                    GamePanel.ColorBody = new Color(255, 162, 99);

                    GamePanel.rgb = false;
                    Interfaz.btnSnakeRestart.doClick();

                    if (bVal == 0) {
                        bVal = 1;
                        escribirArchivo("RegSkins.txt");
                        GamePanel.guardarValor(pts - 120, "pts.txt");
                        Interfaz.btnSnakeRestart.doClick();
                    }
                    JOptionPane.showMessageDialog(null, Act);
                } else {
                    JOptionPane.showMessageDialog(null, NoAct);
                }
                break;

            case 3: //RGB
                if (pts >= 300 || cVal == 1) {
                    System.out.println("RGB");
                    langTxts(btnPressed);

                    GamePanel.rgb = true;
                    Interfaz.btnSnakeRestart.doClick();

                    if (cVal == 0) {
                        cVal = 1;
                        escribirArchivo("RegSkins.txt");
                        GamePanel.guardarValor(pts - 120, "pts.txt");
                        Interfaz.btnSnakeRestart.doClick();
                    }
                    JOptionPane.showMessageDialog(null, Act);
                } else {
                    JOptionPane.showMessageDialog(null, NoAct);
                }
                break;
            case 4: //NON
                break;
            case 5: //NON
                break;
            default:
                break;
        }
        this.dispose();
    }//GEN-LAST:event_btnSkinSnakeActionPerformed

    private void txtBtnDefaultSkin1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBtnDefaultSkin1MouseClicked
        btnPressed = 1;
        btnSelected(txtBtnDefaultSkin1);
    }//GEN-LAST:event_txtBtnDefaultSkin1MouseClicked

    private void txtBtnDefaultSkin2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBtnDefaultSkin2MouseClicked
        btnPressed = 2;
        btnSelected(txtBtnDefaultSkin2);
    }//GEN-LAST:event_txtBtnDefaultSkin2MouseClicked

    private void txtBtnDefaultSkin3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBtnDefaultSkin3MouseClicked
        btnPressed = 3;
        btnSelected(txtBtnDefaultSkin3);
    }//GEN-LAST:event_txtBtnDefaultSkin3MouseClicked

    private void txtBtnDefaultSkin4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBtnDefaultSkin4MouseClicked
        btnPressed = 4;
        btnSelected(txtBtnDefaultSkin4);
    }//GEN-LAST:event_txtBtnDefaultSkin4MouseClicked

    private void txtBtnDefaultSkin5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBtnDefaultSkin5MouseClicked
        btnPressed = 5;
        btnSelected(txtBtnDefaultSkin5);
    }//GEN-LAST:event_txtBtnDefaultSkin5MouseClicked

    private void txtBtnDefaultSkinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBtnDefaultSkinMouseClicked
        btnPressed = 0;
        btnSelected(txtBtnDefaultSkin);
    }//GEN-LAST:event_txtBtnDefaultSkinMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btnSkinSnake;
    private javax.swing.JLabel jLabel1;
    private static javax.swing.JLabel jLabel2;
    private static javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel txtBtnDefaultSkin;
    private javax.swing.JLabel txtBtnDefaultSkin1;
    private javax.swing.JLabel txtBtnDefaultSkin2;
    private javax.swing.JLabel txtBtnDefaultSkin3;
    private javax.swing.JLabel txtBtnDefaultSkin4;
    private javax.swing.JLabel txtBtnDefaultSkin5;
    public static javax.swing.JLabel txtSettCalc;
    private static javax.swing.JLabel txtSnakeBasic;
    private static javax.swing.JLabel txtSnakeBlue;
    private static javax.swing.JLabel txtSnakeRGB;
    private static javax.swing.JLabel txtSnakeWorm;
    // End of variables declaration//GEN-END:variables
}

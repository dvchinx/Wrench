package othr_jframes;

import static othr_jframes.Settings_DA.*;
import principal.GamePanel;
import principal.Interfaz;

/**
 *
 * @author dvchinx_
 */
public class Settings_Main extends javax.swing.JFrame {

    private int xMouse, yMouse;

    public Settings_Main() {
        initComponents();
        setAlwaysOnTop(true);
    }

    public void Es() {
        int valApartado = Interfaz.jTabbedPane1.getSelectedIndex();
        Interfaz.lang = "es";
        Settings_Snake.lang = "es";
        Settings_Snake.verfTexts();
        
        Interfaz.txtDA.setText("Accesos Directos");
        Interfaz.txtAhorcado.setText("Ahorcado");
        Interfaz.txtNotes.setText("Notas");
        Interfaz.txtCC.setText("Calculadora");
        Interfaz.txtNav.setText("Navegador");
        Interfaz.txtGame.setText("Serpiente");
        
        Settings_Snake.Act = "Actualizado Correctamente.";
        Settings_Snake.NoAct="No tienes suficiente dinero.";

        Interfaz.txtTamaño.setText("Tamaño:");
        Interfaz.txtColor.setText("Color:");
        Interfaz.txtFuente.setText("Fuente:");
        Interfaz.cmbNotes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Negro", "Rojo", "Azul", "Verde"}));
        Interfaz.btnSaveNotes.setText("Guardar");
        Interfaz.btnSaveDocNotes.setText("Guardar Documento");
        Interfaz.btnClearNotes.setText("Limpiar");
        Interfaz.btnOpenNotes.setText("Abrir Documento");

        Interfaz.txtNumRaiz.setText("Raíz");
        
        Interfaz.txtVidas.setText("Vidas:");
        Interfaz.txtLetras.setText("Letras:");
        
        Interfaz.txtScoreSnake.setText("Puntaje:");
        Interfaz.txtScoreSnake.setFont(new java.awt.Font("Lucida Sans", 0, 20));
        Interfaz.txtRecordScore.setText("Récord:");
        Interfaz.txtSnakeStart.setText("Iniciar");
        Interfaz.txtSnakeStop.setText("Pausar/Reanudar");
        Interfaz.txtSnakeRestart.setText("Reiniciar");
        Interfaz.txtSnakeDifficult.setText("Dificultad");
        
        GamePanel.txtScore = "Puntaje: ";
        GamePanel.txtGameOver = "Fin del Juego";

        Settings_Calc.txtSettCalc.setText("Configurar Tema");
        Settings_Calc.btnSettCalc.setText("Guardar");
        Settings_Calc.cmbSettCalc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Claro", "Gris", "Oscuro"}));

        txtSettMain.setText("Configurar Idioma");
        btnSettMain.setText("Guardar");
        cmbSettMain.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Español", "Inglés", "Portugués"}));

        txtSettDA.setText("Configurar Botón");
        btnSettDA.setText("Guardar");
        cmbSettDA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Botón 1", "Botón 2", "Botón 3", "Botón 4"}));

        jfrCopyInfo.txtArea_copy.setText("\n"
                + " Derechos de Autor:\n"
                + "\n"
                + "   Imagénes:\n"
                + "    - Alessio Atzeni\n"
                + "    - benjsperry\n"
                + "    - Bryn Taylor\n"
                + "    - CoreUI\n"
                + "    - dvchinx_\n"
                + "    - erix subyarko\n"
                + "    - Rebin Infotech\n"
                + "    - phospore\n"
                + "\n"
                + "    Código:\n"
                + "     - dvchinx_\n"
                + "\n"
                + "    Front-end | Back-end\n"
                + "     - dvchinx_\n"
                + "");

        switch (valApartado) {
            case 0:
                Interfaz.txtTop.setText("Accesos Directos");
                break;
            case 1:
                Interfaz.txtTop.setText("Ahorcado");
                break;
            case 2:
                Interfaz.txtTop.setText("Calculadora");
                break;
            case 3:
                Interfaz.txtTop.setText("Navegador");
                break;
            case 4:
                Interfaz.txtTop.setText("Notas");
                break;
            case 5:
                Interfaz.txtTop.setText("Serpiente");
                break;
            default:

                break;
        }
    }

    public void En() {
        int valApartado = Interfaz.jTabbedPane1.getSelectedIndex();
        Interfaz.lang = "en";
        Settings_Snake.lang = "en";
        Settings_Snake.verfTexts();
        
        Interfaz.txtDA.setText("Direct Access");
        Interfaz.txtAhorcado.setText("Hangman");
        Interfaz.txtNotes.setText("Notes");
        Interfaz.txtCC.setText("Calculator");
        Interfaz.txtNav.setText("Browser");
        Interfaz.txtGame.setText("Snake");
        
        Settings_Snake.Act = "Updated successfully.";
        Settings_Snake.NoAct="You don't have enough money.";

        Interfaz.txtTamaño.setText("Size:");
        Interfaz.txtColor.setText("Color:");
        Interfaz.txtFuente.setText("Font:");
        Interfaz.cmbNotes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Black", "Red", "Blue", "Green"}));
        Interfaz.btnSaveNotes.setText("Save");
        Interfaz.btnSaveDocNotes.setText("Save Document");
        Interfaz.btnClearNotes.setText("Clear");
        Interfaz.btnOpenNotes.setText("Open Document");

        Interfaz.txtNumRaiz.setText("Root");
        
        Interfaz.txtVidas.setText("Lives:");
        Interfaz.txtLetras.setText("Letters:");
        
        Interfaz.txtScoreSnake.setText("Score:");
        Interfaz.txtScoreSnake.setFont(new java.awt.Font("Lucida Sans", 0, 20));
        Interfaz.txtRecordScore.setText("Record:");
        Interfaz.txtSnakeStart.setText("Start");
        Interfaz.txtSnakeStop.setText("Stop/Continue");
        Interfaz.txtSnakeRestart.setText("Restart");
        Interfaz.txtSnakeDifficult.setText("Difficulty");
        
        GamePanel.txtScore = "Score: ";
        GamePanel.txtGameOver = "Game Over";

        Settings_Calc.txtSettCalc.setText("Configure Theme");
        Settings_Calc.btnSettCalc.setText("Save");
        Settings_Calc.cmbSettCalc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Light", "Gray", "Dark"}));

        txtSettMain.setText("Configure Lang");
        btnSettMain.setText("Save");
        cmbSettMain.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Spanish", "English", "Portuguese"}));

        txtSettDA.setText("Configure Button");
        btnSettDA.setText("Save");
        cmbSettDA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Button 1", "Button 2", "Button 3", "Button 4"}));
        
        jfrCopyInfo.txtArea_copy.setText("\n"
                + " Author's Rights:\n"
                + "\n"
                + "   Images:\n"
                + "    - Alessio Atzeni\n"
                + "    - benjsperry\n"
                + "    - Bryn Taylor\n"
                + "    - CoreUI\n"
                + "    - dvchinx_\n"
                + "    - erix subyarko\n"
                + "    - Rebin Infotech\n"
                + "    - phospore\n"
                + "\n"
                + "    Code:\n"
                + "     - dvchinx_\n"
                + "\n"
                + "    Front-end | Back-end\n"
                + "     - dvchinx_\n"
                + "");

        switch (valApartado) {
            case 0:
                Interfaz.txtTop.setText("Direct Access");
                break;
            case 1:
                Interfaz.txtTop.setText("Hangman");
                break;
            case 2:
                Interfaz.txtTop.setText("Calculator");
                break;
            case 3:
                Interfaz.txtTop.setText("Browser");
                break;
            case 4:
                Interfaz.txtTop.setText("Notes");
                break;
            case 5:
                Interfaz.txtTop.setText("Snake");
                break;
            default:

                break;
        }
    }

    public void Pg() {
        int valApartado = Interfaz.jTabbedPane1.getSelectedIndex();
        Interfaz.lang = "pg";
        Settings_Snake.lang = "pg";
        Settings_Snake.verfTexts();
        
        Interfaz.txtDA.setText("Acesso Direto");
        Interfaz.txtAhorcado.setText("Enforcado");
        Interfaz.txtNotes.setText("Notas");
        Interfaz.txtCC.setText("Calculadora");
        Interfaz.txtNav.setText("Navegador");
        Interfaz.txtGame.setText("Cobra");
        
        Settings_Snake.Act = "Atualizado com sucesso.";
        Settings_Snake.NoAct="Você não tem dinheiro suficiente.";

        Interfaz.txtTamaño.setText("Tamanho:");
        Interfaz.txtColor.setText("Cor:");
        Interfaz.txtFuente.setText("Fonte:");
        Interfaz.cmbNotes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Negro", "Vermelho", "Azul", "Verde"}));
        Interfaz.btnSaveNotes.setText("Salve");
        Interfaz.btnSaveDocNotes.setText("Salve Documento");
        Interfaz.btnClearNotes.setText("Limpar");
        Interfaz.btnOpenNotes.setText("Abrir Documento");

        Interfaz.txtNumRaiz.setText("Raiz");
        
        Interfaz.txtVidas.setText("Vidas:");
        Interfaz.txtLetras.setText("Cartas:");
        
        Interfaz.txtScoreSnake.setText("Pontuação:");
        Interfaz.txtScoreSnake.setFont(new java.awt.Font("Lucida Sans", 0, 17));
        Interfaz.txtRecordScore.setText("Registro:");
        Interfaz.txtSnakeStart.setText("Começar");
        Interfaz.txtSnakeStop.setText("Parar/Continuar");
        Interfaz.txtSnakeRestart.setText("Reiniciar");
        Interfaz.txtSnakeDifficult.setText("Dificuldade");
        
        GamePanel.txtScore = "Puntuação: ";
        GamePanel.txtGameOver = "Fim de Jogo";

        Settings_Calc.txtSettCalc.setText("Configurar Tema");
        Settings_Calc.btnSettCalc.setText("Salve");
        Settings_Calc.cmbSettCalc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Leve", "Gris", "Escuro"}));

        txtSettMain.setText("Configurar Idioma");
        btnSettMain.setText("Salve");
        cmbSettMain.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Espanhol", "Inglês", "Português"}));

        txtSettDA.setText("Configurar Botão");
        btnSettDA.setText("Salve");
        cmbSettDA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Botão 1", "Botão 2", "Botão 3", "Botão 4"}));
        
        jfrCopyInfo.txtArea_copy.setText("\n"
                + " Direitos Autorais:\n"
                + "\n"
                + "   Imagens:\n"
                + "    - Alessio Atzeni\n"
                + "    - benjsperry\n"
                + "    - Bryn Taylor\n"
                + "    - CoreUI\n"
                + "    - dvchinx_\n"
                + "    - erix subyarko\n"
                + "    - Rebin Infotech\n"
                + "    - phospore\n"
                + "\n"
                + "    Código:\n"
                + "     - dvchinx_\n"
                + "\n"
                + "    Front-end | Back-end\n"
                + "     - dvchinx_\n"
                + "");

        switch (valApartado) {
            case 0:
                Interfaz.txtTop.setText("Acesso Direto");
                break;
            case 1:
                Interfaz.txtTop.setText("Enforcado");
                break;
            case 2:
                Interfaz.txtTop.setText("Calculadora");
                break;
            case 3:
                Interfaz.txtTop.setText("Navegador");
                break;
            case 4:
                Interfaz.txtTop.setText("Notas");
                break;
            case 5:
                Interfaz.txtTop.setText("Cobra");
                break;
            default:

                break;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cmbSettMain = new javax.swing.JComboBox<>();
        btnSettMain = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtSettMain = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cmbSettMain.setBackground(new java.awt.Color(240, 240, 240));
        cmbSettMain.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        cmbSettMain.setForeground(new java.awt.Color(0, 0, 0));
        cmbSettMain.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Español", "Inglés", "Portugués" }));
        cmbSettMain.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(cmbSettMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 190, 40));

        btnSettMain.setBackground(new java.awt.Color(204, 204, 204));
        btnSettMain.setForeground(new java.awt.Color(0, 0, 0));
        btnSettMain.setText("Guardar");
        btnSettMain.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnSettMain.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSettMain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSettMainActionPerformed(evt);
            }
        });
        jPanel1.add(btnSettMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 190, 110, 30));

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

        txtSettMain.setFont(new java.awt.Font("Corbel", 1, 18)); // NOI18N
        txtSettMain.setForeground(new java.awt.Color(0, 0, 0));
        txtSettMain.setText("Configurar Idioma");
        txtSettMain.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel2.add(txtSettMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 310));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSettMainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSettMainActionPerformed
        int TomarCombo = cmbSettMain.getSelectedIndex();
        
        System.out.println("Se selecciono: " + TomarCombo);
        switch (TomarCombo) {
            case 0:
                Es();
                break;
            case 1:
                En();
                break;
            case 2:
                Pg();
                break;
        }
    }//GEN-LAST:event_btnSettMainActionPerformed

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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btnSettMain;
    public static javax.swing.JComboBox<String> cmbSettMain;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    public static javax.swing.JLabel txtSettMain;
    // End of variables declaration//GEN-END:variables
}

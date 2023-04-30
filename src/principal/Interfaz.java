package principal;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Random;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import othr_jframes.Settings_Calc;
import othr_jframes.Settings_DA;
import othr_jframes.Settings_Main;
import othr_jframes.jfrCopyInfo;

import metodos.Nav.BuscarCon;
import othr_jframes.Settings_Snake;

/**
 *
 * @author dvchinx_
 */
public class Interfaz extends javax.swing.JFrame {

    private int xMouse;
    private int yMouse;

    private StringBuilder keySequence = new StringBuilder();

    private String URLBtn1;
    private String URLBtn2;
    private String URLBtn3;
    private String URLBtn4;
    private final Settings_Snake settings_sn = new Settings_Snake();
    private final Settings_DA settings_da = new Settings_DA();
    private final Settings_Calc settings_cc = new Settings_Calc();
    private final Settings_Main settings_mn = new Settings_Main();
    private final jfrCopyInfo jfr_Copyrgt = new jfrCopyInfo();

    private GamePanel SnakePanel = new GamePanel();
    private boolean blnSnakePlay = true;
    private boolean blnSnakeStop = true;

    private int value;

    private String queBusco;

    private String backUpText;
    private char operat;
    private float val1, val2;
    private float result;
    private int turno = 0;

    private int Font;
    private final JScrollPane scroll;

    public static String lang = "es";

    private boolean PoderJugar = false;
    private String PalabraOculta;
    private int Vida = 6;
    private char[] palabraGuiones;
    private String PalabraOcultaEnGuiones;
    private final boolean juegoGanado = false;
    private String palabraSecreta;
    private StringBuilder palabraOculta1;
    private int Coords;
    private char Letra;
    private String palabraOculta2;

    private int ANMAhorcado = 0;

    public Interfaz() {
        scroll = new JScrollPane(txtarea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBounds(0, 0, 340, 340);
        initComponents();
        lblAhorcado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs_Ahorcado/Ahorcado.png")));
    }
    
    public void keyboardCalc(KeyEvent evt){  // THIS FUNCTION = RECIBE TECLADO w/ KeyEvent (Teclado Númerico y Sección Númerica)
        int keyCode = evt.getKeyCode();

        if (jTabbedPane1.getSelectedIndex() == 2) {
            if (keyCode == KeyEvent.VK_NUMPAD0 || keyCode == KeyEvent.VK_0) {
                btn0.doClick();
            } else if (keyCode == KeyEvent.VK_NUMPAD1 || keyCode == KeyEvent.VK_1) {
                btn1.doClick();
            } else if (keyCode == KeyEvent.VK_NUMPAD2 || keyCode == KeyEvent.VK_2) {
                btn2.doClick();
            } else if (keyCode == KeyEvent.VK_NUMPAD3 || keyCode == KeyEvent.VK_3) {
                btn3.doClick();
            } else if (keyCode == KeyEvent.VK_NUMPAD4 || keyCode == KeyEvent.VK_4) {
                btn4.doClick();
            } else if (keyCode == KeyEvent.VK_NUMPAD5 || keyCode == KeyEvent.VK_5) {
                btn5.doClick();
            } else if (keyCode == KeyEvent.VK_NUMPAD6 || keyCode == KeyEvent.VK_6) {
                btn6.doClick();
            } else if (keyCode == KeyEvent.VK_NUMPAD7 || keyCode == KeyEvent.VK_7) {
                btn7.doClick();
            } else if (keyCode == KeyEvent.VK_NUMPAD8 || keyCode == KeyEvent.VK_8) {
                btn8.doClick();
            } else if (keyCode == KeyEvent.VK_NUMPAD9 || keyCode == KeyEvent.VK_9) {
                btn9.doClick();
            } else if (keyCode == KeyEvent.VK_ADD || keyCode == KeyEvent.VK_PLUS) {
                btnSum.doClick();
            } else if (keyCode == 109 || keyCode == 45) {
                btnRest.doClick();
            } else if (keyCode == 106) {
                btnFor.doClick();
            } else if (keyCode == 111) {
                btnDiv.doClick();
            } else if (keyCode == 110 || keyCode == 46 || keyCode == 44) {
                btnDot.doClick();
            } else if (keyCode == 10) {
                btnEqual.doClick();
            } else if (keyCode == 27) {
                btnC.doClick();
            }
        }
    }

    public void MouseAnimPanel(JPanel jp, int num) { // THIS FUNCTION = ANIMACIÓN - Entrada/Salida Mouse Barra Lateral
        if (num == 0) {
            jp.setBackground(new Color(124, 155, 155));
        } else if (num == 1) {
            jp.setBackground(new Color(160, 193, 192));
        }
    }

    public void DrawNums(String num) { // THIS FUNCTION = Escribe en Calculadora => "ValsTxt" números y los concatena sucesivamente
        if (valsTxt.getText().equals("")) {
            valsTxt.setText(num);
        } else {
            backUpText = valsTxt.getText();
            valsTxt.setText(backUpText + num);
        }
    }

    public void buscar() { 
        if (java.awt.Desktop.isDesktopSupported()) {
            java.awt.Desktop desktop = java.awt.Desktop.getDesktop();

            if (desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
                try {
                    java.net.URI uri = new java.net.URI(queBusco);
                    desktop.browse(uri);
                } catch (URISyntaxException | IOException ex) {

                }
            }
        }
    }

    JFileChooser seleccionar = new JFileChooser();
    File archivo;
    FileInputStream entrada;
    FileOutputStream salida;

    public String AbrirArchivo(File archivo) {
        String documento = "";
        try {
            entrada = new FileInputStream(archivo);
            int ascci;
            while ((ascci = entrada.read()) != -1) {
                char caracter = (char) ascci;
                documento += caracter;

            }

        } catch (IOException e) {
        }
        return documento;
    }

    public String GuardarArchivo(File archivo, String documento) {
        String mensaje = null;
        try {
            salida = new FileOutputStream(archivo);
            byte[] bytxt = documento.getBytes();
            salida.write(bytxt);
            mensaje = "Archivo Guardado";

        } catch (IOException e) {
        }
        return mensaje;
    }

    String[] palabras = {"ZANAHORIA", "LIBELULA", "PRESIDENTE", "JESUCRISTO", "RINOCERONTE"};

    public void GenerarNuevaPalabra() {
        Random r = new Random();
        int n = r.nextInt(palabras.length);

        palabraSecreta = palabras[n];
    }

    public void ConvertirPalabraAGuiones() {

        int nLetrasPalabraSecreta = palabraSecreta.length();
        palabraGuiones = new char[nLetrasPalabraSecreta];

        for (int i = 0; i < palabraGuiones.length; i++) {
            palabraGuiones[i] = '_';

        }
        System.out.println(palabraGuiones);
        PalabraOcultaEnGuiones = String.valueOf(palabraGuiones);
    }

    public void BtnsLetras() {

        palabraOculta1 = new StringBuilder(PalabraOcultaEnGuiones);
        palabraOculta1.setCharAt(Coords, Letra);
        System.out.println("PalabraOculta es: " + PalabraOcultaEnGuiones);
        System.out.println("PalabraOculta1 es: " + palabraOculta1);

        PalabraOcultaEnGuiones = palabraOculta2;
    }

    public void VerificarSiGane(String Palabra) {

        if (palabraOculta2.equals(Palabra)) {
            JOptionPane.showMessageDialog(null, "Felicidades, Has Ganado!");
            PoderJugar = false;
        }
    }

    public void PintarJlbl(JLabel jlbl, String urlImg) {
        jlbl.setIcon(new javax.swing.ImageIcon(getClass().getResource(urlImg)));
    }

    public void AhorcadoAnim(int ANMAhorcado) {
        switch (ANMAhorcado) {
            case 0:
                this.PintarJlbl(this.lblAhorcado, "/imgs_Ahorcado/Ahorcado.png");
                break;
            case 1:
                this.PintarJlbl(this.lblAhorcado, "/imgs_Ahorcado/Ahorcado0.png");
                break;
            case 2:
                this.PintarJlbl(this.lblAhorcado, "/imgs_Ahorcado/Ahorcado1.png");
                break;
            case 3:
                this.PintarJlbl(this.lblAhorcado, "/imgs_Ahorcado/Ahorcado2.png");
                break;
            case 4:
                this.PintarJlbl(this.lblAhorcado, "/imgs_Ahorcado/Ahorcado3.png");
                break;
            case 5:
                this.PintarJlbl(this.lblAhorcado, "/imgs_Ahorcado/Ahorcado4.png");
                break;
            case 6:
                this.PintarJlbl(this.lblAhorcado, "/imgs_Ahorcado/Ahorcado5.png");
                ANMAhorcado = 0;
                break;
            default:
                ANMAhorcado = 0;
                break;
        }
    }

    public void limpiarBotones() {
        Letra_A.setVisible(true);
        Letra_B.setVisible(true);
        Letra_C.setVisible(true);
        Letra_D.setVisible(true);
        Letra_E.setVisible(true);
        Letra_F.setVisible(true);
        Letra_G.setVisible(true);
        Letra_H.setVisible(true);
        Letra_I.setVisible(true);
        Letra_J.setVisible(true);
        Letra_K.setVisible(true);
        Letra_L.setVisible(true);
        Letra_M.setVisible(true);
        Letra_N.setVisible(true);
        Letra_O.setVisible(true);
        Letra_P.setVisible(true);
        Letra_Q.setVisible(true);
        Letra_R.setVisible(true);
        Letra_S.setVisible(true);
        Letra_T.setVisible(true);
        Letra_U.setVisible(true);
        Letra_V.setVisible(true);
        Letra_W.setVisible(true);
        Letra_X.setVisible(true);
        Letra_Y.setVisible(true);
        Letra_Z.setVisible(true);
    }

    public void btnSnakeRestartFunc() {
        pnl_Snake.remove(SnakePanel);

        SnakePanel = new GamePanel();
        pnl_Snake.add(SnakePanel);
        SnakePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        SnakePanel.requestFocus(true);
        SnakePanel.setBounds(70, 15, 540, 540);
        SnakePanel.highScore = SnakePanel.leerValor("highScore.txt");

        value = sldrDificultad.getValue();

        switch (value) {
            case 1:
                GamePanel.DELAY = 200;
                System.out.println("value = " + value);
                break;
            case 2:
                GamePanel.DELAY = 120;
                System.out.println("value = " + value);
                break;
            case 3:
                GamePanel.DELAY = 80;
                System.out.println("value = " + value);
                break;
            default:
                break;
        }

        blnSnakePlay = true;
        blnSnakeStop = true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPasswordField1 = new javax.swing.JPasswordField();
        jp_TopMov = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtTop = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pnl_BtnDA = new javax.swing.JPanel();
        txtDA = new javax.swing.JLabel();
        pnl_BtnAhorcado = new javax.swing.JPanel();
        txtAhorcado = new javax.swing.JLabel();
        pnl_BtnCalc = new javax.swing.JPanel();
        txtCC = new javax.swing.JLabel();
        pnl_BtnNav = new javax.swing.JPanel();
        txtNav = new javax.swing.JLabel();
        pnl_BtnNotes = new javax.swing.JPanel();
        txtNotes = new javax.swing.JLabel();
        pnl_BtnGame = new javax.swing.JPanel();
        txtGame = new javax.swing.JLabel();
        pnl_BtnSettings = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        pnl_Copy = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnl_DA = new javax.swing.JPanel();
        btnFacebook = new javax.swing.JButton();
        btnWhatsApp = new javax.swing.JButton();
        btnReddit = new javax.swing.JButton();
        btnYT1 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        btnPrz1 = new javax.swing.JButton();
        btnPrz2 = new javax.swing.JButton();
        btnPrz3 = new javax.swing.JButton();
        btnPrz4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btnGitHub = new javax.swing.JButton();
        btnGmail = new javax.swing.JButton();
        btnPintrst = new javax.swing.JButton();
        btnTwitter = new javax.swing.JButton();
        pnl_Ahorcado = new javax.swing.JPanel();
        btn_Reiniciar = new javax.swing.JButton();
        txtVidas = new javax.swing.JLabel();
        txt_nVida = new javax.swing.JLabel();
        txt_PalabraOculta = new javax.swing.JLabel();
        txtLetras = new javax.swing.JLabel();
        txt_nLetras = new javax.swing.JLabel();
        lblAhorcado = new javax.swing.JLabel();
        Letra_A = new javax.swing.JButton();
        Letra_B = new javax.swing.JButton();
        Letra_C = new javax.swing.JButton();
        Letra_D = new javax.swing.JButton();
        Letra_E = new javax.swing.JButton();
        Letra_F = new javax.swing.JButton();
        Letra_G = new javax.swing.JButton();
        Letra_H = new javax.swing.JButton();
        Letra_I = new javax.swing.JButton();
        Letra_J = new javax.swing.JButton();
        Letra_K = new javax.swing.JButton();
        Letra_L = new javax.swing.JButton();
        Letra_M = new javax.swing.JButton();
        Letra_N = new javax.swing.JButton();
        Letra_O = new javax.swing.JButton();
        Letra_P = new javax.swing.JButton();
        Letra_Q = new javax.swing.JButton();
        Letra_R = new javax.swing.JButton();
        Letra_S = new javax.swing.JButton();
        Letra_T = new javax.swing.JButton();
        Letra_U = new javax.swing.JButton();
        Letra_V = new javax.swing.JButton();
        Letra_W = new javax.swing.JButton();
        Letra_X = new javax.swing.JButton();
        Letra_Y = new javax.swing.JButton();
        Letra_Z = new javax.swing.JButton();
        pnl_Calc = new javax.swing.JPanel();
        btn9 = new javax.swing.JButton();
        btn7 = new javax.swing.JButton();
        btn8 = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btn5 = new javax.swing.JButton();
        btn6 = new javax.swing.JButton();
        btn1 = new javax.swing.JButton();
        btnDot = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn0 = new javax.swing.JButton();
        btnEqual = new javax.swing.JButton();
        btnDiv = new javax.swing.JButton();
        btnRest = new javax.swing.JButton();
        btnSum = new javax.swing.JButton();
        btnFor = new javax.swing.JButton();
        btnC = new javax.swing.JButton();
        btnPI = new javax.swing.JButton();
        btnExp = new javax.swing.JButton();
        valsTxt = new javax.swing.JTextField();
        btnSqr2 = new javax.swing.JButton();
        btnSqr3 = new javax.swing.JButton();
        btnSqrPrz = new javax.swing.JButton();
        txtNumRaiz = new javax.swing.JTextField();
        txtNumSqrt = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        btnNeg = new javax.swing.JButton();
        pnl_Nav = new javax.swing.JPanel();
        combo_Nav = new javax.swing.JComboBox<>();
        txt_Buscar = new javax.swing.JTextField();
        btnBuscarNav = new javax.swing.JButton();
        btnDDGo = new javax.swing.JButton();
        btnGoogle = new javax.swing.JButton();
        btnBing = new javax.swing.JButton();
        btnYahoo = new javax.swing.JButton();
        pnl_Notes = new javax.swing.JPanel();
        btnOpenNotes = new javax.swing.JButton();
        btnSaveDocNotes = new javax.swing.JButton();
        btnClearNotes = new javax.swing.JButton();
        txtTamaño = new javax.swing.JLabel();
        jcomboFont = new javax.swing.JComboBox<>();
        txtFuente = new javax.swing.JLabel();
        jComboStyle = new javax.swing.JComboBox<>();
        txtColor = new javax.swing.JLabel();
        cmbNotes = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtarea = new javax.swing.JTextArea();
        btnSaveNotes = new javax.swing.JButton();
        pnl_Snake = new javax.swing.JPanel();
        btnSnakePlay = new javax.swing.JButton();
        btnSnakeStop = new javax.swing.JButton();
        btnSnakeRestart = new javax.swing.JButton();
        txtSnakeScore = new javax.swing.JLabel();
        txtSnakeHighScore = new javax.swing.JLabel();
        txtScoreSnake = new javax.swing.JLabel();
        txtRecordScore = new javax.swing.JLabel();
        sldrDificultad = new javax.swing.JSlider();
        txtSnakeStart = new javax.swing.JLabel();
        txtSnakeStop = new javax.swing.JLabel();
        txtSnakeRestart = new javax.swing.JLabel();
        txtSnakeDifficult = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtSnakePts = new javax.swing.JLabel();

        jPasswordField1.setText("jPasswordField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jp_TopMov.setOpaque(false);
        jp_TopMov.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jp_TopMovMouseDragged(evt);
            }
        });
        jp_TopMov.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jp_TopMovMousePressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Microsoft Yi Baiti", 0, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("X");
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Microsoft Yi Baiti", 0, 38)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("-");
        jLabel13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        txtTop.setFont(new java.awt.Font("Corbel", 0, 24)); // NOI18N
        txtTop.setForeground(new java.awt.Color(0, 0, 0));
        txtTop.setText("Accesos Directos");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs_Main/menu.png"))); // NOI18N

        javax.swing.GroupLayout jp_TopMovLayout = new javax.swing.GroupLayout(jp_TopMov);
        jp_TopMov.setLayout(jp_TopMovLayout);
        jp_TopMovLayout.setHorizontalGroup(
            jp_TopMovLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_TopMovLayout.createSequentialGroup()
                .addContainerGap(440, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTop, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(116, 116, 116)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jp_TopMovLayout.setVerticalGroup(
            jp_TopMovLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_TopMovLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_TopMovLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_TopMovLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jp_TopMovLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTop, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(jp_TopMov, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, -1));

        jPanel1.setBackground(new java.awt.Color(180, 216, 216));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs_Main/wrench.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, 110, 110));

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Corbel", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("v2.1");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 340, -1));

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Corbel", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Wrench Assistant");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 340, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs_Main/BgLogo3.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 350, 230));

        pnl_BtnDA.setBackground(new java.awt.Color(160, 193, 192));
        pnl_BtnDA.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnl_BtnDA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnl_BtnDAMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnl_BtnDAMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnl_BtnDAMouseExited(evt);
            }
        });

        txtDA.setFont(new java.awt.Font("Corbel", 0, 24)); // NOI18N
        txtDA.setForeground(new java.awt.Color(255, 255, 255));
        txtDA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtDA.setText("Accesos Directos");

        javax.swing.GroupLayout pnl_BtnDALayout = new javax.swing.GroupLayout(pnl_BtnDA);
        pnl_BtnDA.setLayout(pnl_BtnDALayout);
        pnl_BtnDALayout.setHorizontalGroup(
            pnl_BtnDALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_BtnDALayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtDA, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnl_BtnDALayout.setVerticalGroup(
            pnl_BtnDALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_BtnDALayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtDA, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(pnl_BtnDA, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 350, 50));

        pnl_BtnAhorcado.setBackground(new java.awt.Color(160, 193, 192));
        pnl_BtnAhorcado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnl_BtnAhorcado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnl_BtnAhorcadoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnl_BtnAhorcadoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnl_BtnAhorcadoMouseExited(evt);
            }
        });

        txtAhorcado.setFont(new java.awt.Font("Corbel", 0, 24)); // NOI18N
        txtAhorcado.setForeground(new java.awt.Color(255, 255, 255));
        txtAhorcado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtAhorcado.setText("Ahorcado");

        javax.swing.GroupLayout pnl_BtnAhorcadoLayout = new javax.swing.GroupLayout(pnl_BtnAhorcado);
        pnl_BtnAhorcado.setLayout(pnl_BtnAhorcadoLayout);
        pnl_BtnAhorcadoLayout.setHorizontalGroup(
            pnl_BtnAhorcadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_BtnAhorcadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtAhorcado, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnl_BtnAhorcadoLayout.setVerticalGroup(
            pnl_BtnAhorcadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_BtnAhorcadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtAhorcado, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(pnl_BtnAhorcado, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 350, 50));

        pnl_BtnCalc.setBackground(new java.awt.Color(160, 193, 192));
        pnl_BtnCalc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnl_BtnCalc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnl_BtnCalcMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnl_BtnCalcMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnl_BtnCalcMouseExited(evt);
            }
        });

        txtCC.setFont(new java.awt.Font("Corbel", 0, 24)); // NOI18N
        txtCC.setForeground(new java.awt.Color(255, 255, 255));
        txtCC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtCC.setText("Calculadora");
        txtCC.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        txtCC.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pnl_BtnCalcLayout = new javax.swing.GroupLayout(pnl_BtnCalc);
        pnl_BtnCalc.setLayout(pnl_BtnCalcLayout);
        pnl_BtnCalcLayout.setHorizontalGroup(
            pnl_BtnCalcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_BtnCalcLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtCC, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnl_BtnCalcLayout.setVerticalGroup(
            pnl_BtnCalcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_BtnCalcLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtCC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(pnl_BtnCalc, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 350, 50));

        pnl_BtnNav.setBackground(new java.awt.Color(160, 193, 192));
        pnl_BtnNav.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnl_BtnNav.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnl_BtnNavMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnl_BtnNavMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnl_BtnNavMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnl_BtnNavMousePressed(evt);
            }
        });

        txtNav.setFont(new java.awt.Font("Corbel", 0, 24)); // NOI18N
        txtNav.setForeground(new java.awt.Color(255, 255, 255));
        txtNav.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtNav.setText("Navegador");
        txtNav.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout pnl_BtnNavLayout = new javax.swing.GroupLayout(pnl_BtnNav);
        pnl_BtnNav.setLayout(pnl_BtnNavLayout);
        pnl_BtnNavLayout.setHorizontalGroup(
            pnl_BtnNavLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_BtnNavLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtNav, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnl_BtnNavLayout.setVerticalGroup(
            pnl_BtnNavLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_BtnNavLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtNav, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(pnl_BtnNav, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, 350, 50));

        pnl_BtnNotes.setBackground(new java.awt.Color(160, 193, 192));
        pnl_BtnNotes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnl_BtnNotes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnl_BtnNotesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnl_BtnNotesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnl_BtnNotesMouseExited(evt);
            }
        });

        txtNotes.setFont(new java.awt.Font("Corbel", 0, 24)); // NOI18N
        txtNotes.setForeground(new java.awt.Color(255, 255, 255));
        txtNotes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtNotes.setText("Notas");
        txtNotes.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout pnl_BtnNotesLayout = new javax.swing.GroupLayout(pnl_BtnNotes);
        pnl_BtnNotes.setLayout(pnl_BtnNotesLayout);
        pnl_BtnNotesLayout.setHorizontalGroup(
            pnl_BtnNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_BtnNotesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtNotes, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnl_BtnNotesLayout.setVerticalGroup(
            pnl_BtnNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_BtnNotesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtNotes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(pnl_BtnNotes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, -1, 50));

        pnl_BtnGame.setBackground(new java.awt.Color(160, 193, 192));
        pnl_BtnGame.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnl_BtnGame.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnl_BtnGameMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnl_BtnGameMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnl_BtnGameMouseExited(evt);
            }
        });

        txtGame.setBackground(new java.awt.Color(255, 255, 255));
        txtGame.setFont(new java.awt.Font("Corbel", 0, 24)); // NOI18N
        txtGame.setForeground(new java.awt.Color(255, 255, 255));
        txtGame.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtGame.setText("Serpiente");
        txtGame.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout pnl_BtnGameLayout = new javax.swing.GroupLayout(pnl_BtnGame);
        pnl_BtnGame.setLayout(pnl_BtnGameLayout);
        pnl_BtnGameLayout.setHorizontalGroup(
            pnl_BtnGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_BtnGameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtGame, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnl_BtnGameLayout.setVerticalGroup(
            pnl_BtnGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_BtnGameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtGame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(pnl_BtnGame, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 550, 350, 50));

        pnl_BtnSettings.setBackground(new java.awt.Color(180, 216, 216));
        pnl_BtnSettings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnl_BtnSettingsMouseClicked(evt);
            }
        });

        jLabel17.setBackground(new java.awt.Color(180, 216, 216));
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs_Main/options0.png"))); // NOI18N
        jLabel17.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnl_BtnSettingsLayout = new javax.swing.GroupLayout(pnl_BtnSettings);
        pnl_BtnSettings.setLayout(pnl_BtnSettingsLayout);
        pnl_BtnSettingsLayout.setHorizontalGroup(
            pnl_BtnSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_BtnSettingsLayout.createSequentialGroup()
                .addComponent(jLabel17)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnl_BtnSettingsLayout.setVerticalGroup(
            pnl_BtnSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_BtnSettingsLayout.createSequentialGroup()
                .addComponent(jLabel17)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.add(pnl_BtnSettings, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 610, 30, 30));

        pnl_Copy.setBackground(new java.awt.Color(180, 216, 216));
        pnl_Copy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnl_CopyMouseClicked(evt);
            }
        });

        jLabel18.setBackground(new java.awt.Color(180, 216, 216));
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs_Main/copyright.png"))); // NOI18N
        jLabel18.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel18MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnl_CopyLayout = new javax.swing.GroupLayout(pnl_Copy);
        pnl_Copy.setLayout(pnl_CopyLayout);
        pnl_CopyLayout.setHorizontalGroup(
            pnl_CopyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_CopyLayout.createSequentialGroup()
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnl_CopyLayout.setVerticalGroup(
            pnl_CopyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_CopyLayout.createSequentialGroup()
                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(pnl_Copy, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 610, 30, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 350, 650));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs_Main/Banner2.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, 750, 80));

        jPanel8.setBackground(new java.awt.Color(180, 216, 216));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 606, 40, 40));

        jTabbedPane1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTabbedPane1KeyPressed(evt);
            }
        });

        pnl_DA.setBackground(new java.awt.Color(255, 255, 255));
        pnl_DA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pnl_DAKeyPressed(evt);
            }
        });
        pnl_DA.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnFacebook.setBackground(new java.awt.Color(255, 255, 255));
        btnFacebook.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs_DA/instagram01.png"))); // NOI18N
        btnFacebook.setBorder(null);
        btnFacebook.setContentAreaFilled(false);
        btnFacebook.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFacebook.setFocusPainted(false);
        btnFacebook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFacebookActionPerformed(evt);
            }
        });
        pnl_DA.add(btnFacebook, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 230, 70, 70));

        btnWhatsApp.setBackground(new java.awt.Color(255, 255, 255));
        btnWhatsApp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs_DA/whatsapp01.png"))); // NOI18N
        btnWhatsApp.setBorder(null);
        btnWhatsApp.setBorderPainted(false);
        btnWhatsApp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnWhatsApp.setFocusPainted(false);
        btnWhatsApp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWhatsAppActionPerformed(evt);
            }
        });
        pnl_DA.add(btnWhatsApp, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 230, 70, 70));

        btnReddit.setBackground(new java.awt.Color(255, 255, 255));
        btnReddit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs_DA/youtube0.png"))); // NOI18N
        btnReddit.setBorder(null);
        btnReddit.setContentAreaFilled(false);
        btnReddit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReddit.setFocusPainted(false);
        btnReddit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRedditActionPerformed(evt);
            }
        });
        pnl_DA.add(btnReddit, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 230, 70, 70));

        btnYT1.setBackground(new java.awt.Color(255, 255, 255));
        btnYT1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs_DA/facebook0.png"))); // NOI18N
        btnYT1.setBorder(null);
        btnYT1.setContentAreaFilled(false);
        btnYT1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnYT1.setFocusPainted(false);
        btnYT1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnYT1ActionPerformed(evt);
            }
        });
        pnl_DA.add(btnYT1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, 70, 70));

        jLabel15.setBackground(new java.awt.Color(153, 204, 255));
        jLabel15.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("X");
        jLabel15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel15MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel15MouseExited(evt);
            }
        });
        pnl_DA.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 0, 40, 40));

        btnPrz1.setBackground(new java.awt.Color(255, 255, 255));
        btnPrz1.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        btnPrz1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs_DA/clip1.png"))); // NOI18N
        btnPrz1.setText("1");
        btnPrz1.setBorder(null);
        btnPrz1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPrz1.setFocusPainted(false);
        btnPrz1.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        btnPrz1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrz1ActionPerformed(evt);
            }
        });
        pnl_DA.add(btnPrz1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 330, 70, 70));

        btnPrz2.setBackground(new java.awt.Color(255, 255, 255));
        btnPrz2.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        btnPrz2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs_DA/clip1.png"))); // NOI18N
        btnPrz2.setText("2");
        btnPrz2.setBorder(null);
        btnPrz2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPrz2.setFocusPainted(false);
        btnPrz2.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        btnPrz2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrz2ActionPerformed(evt);
            }
        });
        pnl_DA.add(btnPrz2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 330, 70, 70));

        btnPrz3.setBackground(new java.awt.Color(255, 255, 255));
        btnPrz3.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        btnPrz3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs_DA/clip1.png"))); // NOI18N
        btnPrz3.setText("3");
        btnPrz3.setBorder(null);
        btnPrz3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPrz3.setFocusPainted(false);
        btnPrz3.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        btnPrz3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrz3ActionPerformed(evt);
            }
        });
        pnl_DA.add(btnPrz3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 330, 70, 70));

        btnPrz4.setBackground(new java.awt.Color(255, 255, 255));
        btnPrz4.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        btnPrz4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs_DA/clip1.png"))); // NOI18N
        btnPrz4.setText("4");
        btnPrz4.setBorder(null);
        btnPrz4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPrz4.setFocusPainted(false);
        btnPrz4.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        btnPrz4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrz4ActionPerformed(evt);
            }
        });
        pnl_DA.add(btnPrz4, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 330, 70, 70));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs_DA/settings.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        pnl_DA.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(675, 500, 70, 70));

        btnGitHub.setBackground(new java.awt.Color(255, 255, 255));
        btnGitHub.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs_DA/github1.png"))); // NOI18N
        btnGitHub.setBorder(null);
        btnGitHub.setContentAreaFilled(false);
        btnGitHub.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGitHub.setFocusPainted(false);
        btnGitHub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGitHubActionPerformed(evt);
            }
        });
        pnl_DA.add(btnGitHub, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, 70, 70));

        btnGmail.setBackground(new java.awt.Color(255, 255, 255));
        btnGmail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs_DA/gmail0.png"))); // NOI18N
        btnGmail.setBorder(null);
        btnGmail.setContentAreaFilled(false);
        btnGmail.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGmail.setFocusPainted(false);
        btnGmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGmailActionPerformed(evt);
            }
        });
        pnl_DA.add(btnGmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 130, 70, 70));

        btnPintrst.setBackground(new java.awt.Color(255, 255, 255));
        btnPintrst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs_DA/pinterest0.png"))); // NOI18N
        btnPintrst.setBorder(null);
        btnPintrst.setBorderPainted(false);
        btnPintrst.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPintrst.setFocusPainted(false);
        btnPintrst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPintrstActionPerformed(evt);
            }
        });
        pnl_DA.add(btnPintrst, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 130, 70, 70));

        btnTwitter.setBackground(new java.awt.Color(255, 255, 255));
        btnTwitter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs_DA/twitter0.png"))); // NOI18N
        btnTwitter.setBorder(null);
        btnTwitter.setContentAreaFilled(false);
        btnTwitter.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTwitter.setFocusPainted(false);
        btnTwitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTwitterActionPerformed(evt);
            }
        });
        pnl_DA.add(btnTwitter, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 130, 70, 70));

        jTabbedPane1.addTab("Accesos Directos", pnl_DA);

        pnl_Ahorcado.setBackground(new java.awt.Color(255, 255, 255));
        pnl_Ahorcado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_Reiniciar.setBackground(new java.awt.Color(255, 255, 255));
        btn_Reiniciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs_Ahorcado/restart0.png"))); // NOI18N
        btn_Reiniciar.setBorder(null);
        btn_Reiniciar.setBorderPainted(false);
        btn_Reiniciar.setContentAreaFilled(false);
        btn_Reiniciar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Reiniciar.setFocusPainted(false);
        btn_Reiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ReiniciarActionPerformed(evt);
            }
        });
        pnl_Ahorcado.add(btn_Reiniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 500, 50, 50));

        txtVidas.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        txtVidas.setText("Vidas:");
        pnl_Ahorcado.add(txtVidas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, -1));

        txt_nVida.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        txt_nVida.setText("6");
        pnl_Ahorcado.add(txt_nVida, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 240, -1, -1));

        txt_PalabraOculta.setFont(new java.awt.Font("Roboto", 1, 36)); // NOI18N
        txt_PalabraOculta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_PalabraOculta.setText("??????????");
        pnl_Ahorcado.add(txt_PalabraOculta, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, 260, -1));

        txtLetras.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        txtLetras.setText("Letras:");
        pnl_Ahorcado.add(txtLetras, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, -1, -1));

        txt_nLetras.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        txt_nLetras.setText("??");
        pnl_Ahorcado.add(txt_nLetras, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 310, -1, -1));

        lblAhorcado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnl_Ahorcado.add(lblAhorcado, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 180, 210, 210));

        Letra_A.setBackground(new java.awt.Color(255, 255, 255));
        Letra_A.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        Letra_A.setText("A");
        Letra_A.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Letra_A.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Letra_AActionPerformed(evt);
            }
        });
        pnl_Ahorcado.add(Letra_A, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 90, 50, 50));

        Letra_B.setBackground(new java.awt.Color(255, 255, 255));
        Letra_B.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        Letra_B.setText("B");
        Letra_B.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Letra_B.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Letra_BActionPerformed(evt);
            }
        });
        pnl_Ahorcado.add(Letra_B, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 90, 50, 50));

        Letra_C.setBackground(new java.awt.Color(255, 255, 255));
        Letra_C.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        Letra_C.setText("C");
        Letra_C.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Letra_C.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Letra_CActionPerformed(evt);
            }
        });
        pnl_Ahorcado.add(Letra_C, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 90, 50, 50));

        Letra_D.setBackground(new java.awt.Color(255, 255, 255));
        Letra_D.setText("D");
        Letra_D.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Letra_D.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Letra_DActionPerformed(evt);
            }
        });
        pnl_Ahorcado.add(Letra_D, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 90, 50, 50));

        Letra_E.setBackground(new java.awt.Color(255, 255, 255));
        Letra_E.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        Letra_E.setText("E");
        Letra_E.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Letra_E.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Letra_EActionPerformed(evt);
            }
        });
        pnl_Ahorcado.add(Letra_E, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 90, 50, 50));

        Letra_F.setBackground(new java.awt.Color(255, 255, 255));
        Letra_F.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        Letra_F.setText("F");
        Letra_F.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Letra_F.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Letra_FActionPerformed(evt);
            }
        });
        pnl_Ahorcado.add(Letra_F, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 160, 50, 50));

        Letra_G.setBackground(new java.awt.Color(255, 255, 255));
        Letra_G.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        Letra_G.setText("G");
        Letra_G.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Letra_G.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Letra_GActionPerformed(evt);
            }
        });
        pnl_Ahorcado.add(Letra_G, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 160, 50, 50));

        Letra_H.setBackground(new java.awt.Color(255, 255, 255));
        Letra_H.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        Letra_H.setText("H");
        Letra_H.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Letra_H.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Letra_HActionPerformed(evt);
            }
        });
        pnl_Ahorcado.add(Letra_H, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 160, 50, 50));

        Letra_I.setBackground(new java.awt.Color(255, 255, 255));
        Letra_I.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        Letra_I.setText("I");
        Letra_I.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Letra_I.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Letra_IActionPerformed(evt);
            }
        });
        pnl_Ahorcado.add(Letra_I, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 160, 50, 50));

        Letra_J.setBackground(new java.awt.Color(255, 255, 255));
        Letra_J.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        Letra_J.setText("J");
        Letra_J.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Letra_J.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Letra_JActionPerformed(evt);
            }
        });
        pnl_Ahorcado.add(Letra_J, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 160, 50, 50));

        Letra_K.setBackground(new java.awt.Color(255, 255, 255));
        Letra_K.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        Letra_K.setText("K");
        Letra_K.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Letra_K.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Letra_KActionPerformed(evt);
            }
        });
        pnl_Ahorcado.add(Letra_K, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 230, 50, 50));

        Letra_L.setBackground(new java.awt.Color(255, 255, 255));
        Letra_L.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        Letra_L.setText("L");
        Letra_L.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Letra_L.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Letra_LActionPerformed(evt);
            }
        });
        pnl_Ahorcado.add(Letra_L, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 230, 50, 50));

        Letra_M.setBackground(new java.awt.Color(255, 255, 255));
        Letra_M.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        Letra_M.setText("M");
        Letra_M.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Letra_M.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Letra_MActionPerformed(evt);
            }
        });
        pnl_Ahorcado.add(Letra_M, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 230, 50, 50));

        Letra_N.setBackground(new java.awt.Color(255, 255, 255));
        Letra_N.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        Letra_N.setText("N");
        Letra_N.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Letra_N.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Letra_NActionPerformed(evt);
            }
        });
        pnl_Ahorcado.add(Letra_N, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 230, 50, 50));

        Letra_O.setBackground(new java.awt.Color(255, 255, 255));
        Letra_O.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        Letra_O.setText("O");
        Letra_O.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Letra_O.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Letra_OActionPerformed(evt);
            }
        });
        pnl_Ahorcado.add(Letra_O, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 230, 50, 50));

        Letra_P.setBackground(new java.awt.Color(255, 255, 255));
        Letra_P.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        Letra_P.setText("P");
        Letra_P.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Letra_P.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Letra_PActionPerformed(evt);
            }
        });
        pnl_Ahorcado.add(Letra_P, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 300, 50, 50));

        Letra_Q.setBackground(new java.awt.Color(255, 255, 255));
        Letra_Q.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        Letra_Q.setText("Q");
        Letra_Q.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Letra_Q.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Letra_QActionPerformed(evt);
            }
        });
        pnl_Ahorcado.add(Letra_Q, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 300, 50, 50));

        Letra_R.setBackground(new java.awt.Color(255, 255, 255));
        Letra_R.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        Letra_R.setText("R");
        Letra_R.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Letra_R.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Letra_RActionPerformed(evt);
            }
        });
        pnl_Ahorcado.add(Letra_R, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 300, 50, 50));

        Letra_S.setBackground(new java.awt.Color(255, 255, 255));
        Letra_S.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        Letra_S.setText("S");
        Letra_S.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Letra_S.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Letra_SActionPerformed(evt);
            }
        });
        pnl_Ahorcado.add(Letra_S, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 300, 50, 50));

        Letra_T.setBackground(new java.awt.Color(255, 255, 255));
        Letra_T.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        Letra_T.setText("T");
        Letra_T.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Letra_T.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Letra_TActionPerformed(evt);
            }
        });
        pnl_Ahorcado.add(Letra_T, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 300, 50, 50));

        Letra_U.setBackground(new java.awt.Color(255, 255, 255));
        Letra_U.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        Letra_U.setText("U");
        Letra_U.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Letra_U.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Letra_UActionPerformed(evt);
            }
        });
        pnl_Ahorcado.add(Letra_U, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 370, 50, 50));

        Letra_V.setBackground(new java.awt.Color(255, 255, 255));
        Letra_V.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        Letra_V.setText("V");
        Letra_V.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Letra_V.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Letra_VActionPerformed(evt);
            }
        });
        pnl_Ahorcado.add(Letra_V, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 370, 50, 50));

        Letra_W.setBackground(new java.awt.Color(255, 255, 255));
        Letra_W.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        Letra_W.setText("W");
        Letra_W.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Letra_W.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Letra_WActionPerformed(evt);
            }
        });
        pnl_Ahorcado.add(Letra_W, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 370, 50, 50));

        Letra_X.setBackground(new java.awt.Color(255, 255, 255));
        Letra_X.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        Letra_X.setText("X");
        Letra_X.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Letra_X.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Letra_XActionPerformed(evt);
            }
        });
        pnl_Ahorcado.add(Letra_X, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 370, 50, 50));

        Letra_Y.setBackground(new java.awt.Color(255, 255, 255));
        Letra_Y.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        Letra_Y.setText("Y");
        Letra_Y.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Letra_Y.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Letra_YActionPerformed(evt);
            }
        });
        pnl_Ahorcado.add(Letra_Y, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 370, 50, 50));

        Letra_Z.setBackground(new java.awt.Color(255, 255, 255));
        Letra_Z.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        Letra_Z.setText("Z");
        Letra_Z.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Letra_Z.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Letra_ZActionPerformed(evt);
            }
        });
        pnl_Ahorcado.add(Letra_Z, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 430, 50, 50));

        jTabbedPane1.addTab("Ahorcado", pnl_Ahorcado);

        pnl_Calc.setBackground(new java.awt.Color(255, 255, 255));
        pnl_Calc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pnl_CalcKeyPressed(evt);
            }
        });
        pnl_Calc.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn9.setBackground(new java.awt.Color(204, 204, 204));
        btn9.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        btn9.setText("9");
        btn9.setFocusable(false);
        btn9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn9ActionPerformed(evt);
            }
        });
        pnl_Calc.add(btn9, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 210, 70, 70));

        btn7.setBackground(new java.awt.Color(204, 204, 204));
        btn7.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        btn7.setText("7");
        btn7.setFocusable(false);
        btn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn7ActionPerformed(evt);
            }
        });
        pnl_Calc.add(btn7, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 210, 70, 70));

        btn8.setBackground(new java.awt.Color(204, 204, 204));
        btn8.setFont(new java.awt.Font("Montserrat Light", 1, 14)); // NOI18N
        btn8.setText("8");
        btn8.setFocusable(false);
        btn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn8ActionPerformed(evt);
            }
        });
        pnl_Calc.add(btn8, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 210, 70, 70));

        btn4.setBackground(new java.awt.Color(204, 204, 204));
        btn4.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        btn4.setText("4");
        btn4.setFocusable(false);
        btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn4ActionPerformed(evt);
            }
        });
        pnl_Calc.add(btn4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 280, 70, 70));

        btn5.setBackground(new java.awt.Color(204, 204, 204));
        btn5.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        btn5.setText("5");
        btn5.setFocusable(false);
        btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5ActionPerformed(evt);
            }
        });
        pnl_Calc.add(btn5, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 280, 70, 70));

        btn6.setBackground(new java.awt.Color(204, 204, 204));
        btn6.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        btn6.setText("6");
        btn6.setFocusable(false);
        btn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn6ActionPerformed(evt);
            }
        });
        pnl_Calc.add(btn6, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 280, 70, 70));

        btn1.setBackground(new java.awt.Color(204, 204, 204));
        btn1.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        btn1.setText("1");
        btn1.setFocusable(false);
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });
        pnl_Calc.add(btn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 350, 70, 70));

        btnDot.setBackground(new java.awt.Color(204, 204, 204));
        btnDot.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        btnDot.setText(",");
        btnDot.setFocusable(false);
        btnDot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDotActionPerformed(evt);
            }
        });
        pnl_Calc.add(btnDot, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 420, 70, 70));

        btn3.setBackground(new java.awt.Color(204, 204, 204));
        btn3.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        btn3.setText("3");
        btn3.setFocusable(false);
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3ActionPerformed(evt);
            }
        });
        pnl_Calc.add(btn3, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 350, 70, 70));

        btn2.setBackground(new java.awt.Color(204, 204, 204));
        btn2.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        btn2.setText("2");
        btn2.setFocusable(false);
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2ActionPerformed(evt);
            }
        });
        pnl_Calc.add(btn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 350, 70, 70));

        btn0.setBackground(new java.awt.Color(204, 204, 204));
        btn0.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        btn0.setText("0");
        btn0.setFocusable(false);
        btn0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn0ActionPerformed(evt);
            }
        });
        pnl_Calc.add(btn0, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 420, 70, 70));

        btnEqual.setBackground(new java.awt.Color(204, 204, 204));
        btnEqual.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnEqual.setText("=");
        btnEqual.setFocusable(false);
        btnEqual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEqualActionPerformed(evt);
            }
        });
        pnl_Calc.add(btnEqual, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 420, 70, 70));

        btnDiv.setBackground(new java.awt.Color(204, 204, 204));
        btnDiv.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnDiv.setText("/");
        btnDiv.setFocusable(false);
        btnDiv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDivActionPerformed(evt);
            }
        });
        pnl_Calc.add(btnDiv, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 210, 70, 70));

        btnRest.setBackground(new java.awt.Color(204, 204, 204));
        btnRest.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnRest.setText("-");
        btnRest.setFocusable(false);
        btnRest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestActionPerformed(evt);
            }
        });
        pnl_Calc.add(btnRest, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 350, 70, 70));

        btnSum.setBackground(new java.awt.Color(204, 204, 204));
        btnSum.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnSum.setText("+");
        btnSum.setFocusable(false);
        btnSum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSumActionPerformed(evt);
            }
        });
        pnl_Calc.add(btnSum, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 420, 70, 70));

        btnFor.setBackground(new java.awt.Color(204, 204, 204));
        btnFor.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnFor.setText("x");
        btnFor.setFocusable(false);
        btnFor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnForActionPerformed(evt);
            }
        });
        pnl_Calc.add(btnFor, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 280, 70, 70));

        btnC.setBackground(new java.awt.Color(204, 204, 204));
        btnC.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        btnC.setText("C");
        btnC.setFocusable(false);
        btnC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCActionPerformed(evt);
            }
        });
        btnC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnCKeyPressed(evt);
            }
        });
        pnl_Calc.add(btnC, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 210, 70, 70));

        btnPI.setBackground(new java.awt.Color(204, 204, 204));
        btnPI.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        btnPI.setText("π");
        btnPI.setFocusable(false);
        btnPI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPIActionPerformed(evt);
            }
        });
        pnl_Calc.add(btnPI, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 280, 70, 70));

        btnExp.setBackground(new java.awt.Color(204, 204, 204));
        btnExp.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        btnExp.setText("^");
        btnExp.setFocusable(false);
        btnExp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExpActionPerformed(evt);
            }
        });
        pnl_Calc.add(btnExp, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 350, 70, 70));

        valsTxt.setEditable(false);
        valsTxt.setBackground(new java.awt.Color(153, 153, 153));
        valsTxt.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        valsTxt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        valsTxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        valsTxt.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        valsTxt.setFocusable(false);
        valsTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                valsTxtKeyPressed(evt);
            }
        });
        pnl_Calc.add(valsTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 90, 280, 120));

        btnSqr2.setBackground(new java.awt.Color(204, 204, 204));
        btnSqr2.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnSqr2.setText("²√");
        btnSqr2.setFocusable(false);
        btnSqr2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSqr2ActionPerformed(evt);
            }
        });
        pnl_Calc.add(btnSqr2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 210, 70, 70));

        btnSqr3.setBackground(new java.awt.Color(204, 204, 204));
        btnSqr3.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnSqr3.setText("³√");
        btnSqr3.setFocusable(false);
        btnSqr3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSqr3ActionPerformed(evt);
            }
        });
        pnl_Calc.add(btnSqr3, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 280, 70, 70));

        btnSqrPrz.setBackground(new java.awt.Color(204, 204, 204));
        btnSqrPrz.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnSqrPrz.setText("√");
        btnSqrPrz.setFocusable(false);
        btnSqrPrz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSqrPrzActionPerformed(evt);
            }
        });
        pnl_Calc.add(btnSqrPrz, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 280, 70, 70));

        txtNumRaiz.setBackground(new java.awt.Color(153, 153, 153));
        txtNumRaiz.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        txtNumRaiz.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNumRaiz.setText("Raíz");
        txtNumRaiz.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtNumRaiz.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtNumRaiz.setFocusable(false);
        txtNumRaiz.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNumRaizMouseClicked(evt);
            }
        });
        pnl_Calc.add(txtNumRaiz, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 250, 70, 30));

        txtNumSqrt.setBackground(new java.awt.Color(153, 153, 153));
        txtNumSqrt.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        txtNumSqrt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNumSqrt.setText("Num");
        txtNumSqrt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtNumSqrt.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtNumSqrt.setFocusable(false);
        txtNumSqrt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNumSqrtMouseClicked(evt);
            }
        });
        pnl_Calc.add(txtNumSqrt, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 220, 70, 30));

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs_DA/settings.png"))); // NOI18N
        jButton2.setBorder(null);
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setFocusPainted(false);
        jButton2.setFocusable(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        pnl_Calc.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(675, 500, 70, 70));

        btnNeg.setBackground(new java.awt.Color(204, 204, 204));
        btnNeg.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        btnNeg.setText("+/-");
        btnNeg.setFocusable(false);
        btnNeg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNegActionPerformed(evt);
            }
        });
        pnl_Calc.add(btnNeg, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 420, 70, 70));

        jTabbedPane1.addTab("Calculadora", pnl_Calc);

        pnl_Nav.setBackground(new java.awt.Color(255, 255, 255));
        pnl_Nav.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        combo_Nav.setBackground(new java.awt.Color(255, 255, 255));
        combo_Nav.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        combo_Nav.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Google", "Bing", "Yahoo", "DuckDuckGo" }));
        combo_Nav.setBorder(null);
        combo_Nav.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_NavActionPerformed(evt);
            }
        });
        pnl_Nav.add(combo_Nav, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, 160, 50));

        txt_Buscar.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        pnl_Nav.add(txt_Buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 210, 340, 50));

        btnBuscarNav.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscarNav.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs_Nav/buscar0.png"))); // NOI18N
        btnBuscarNav.setBorder(null);
        btnBuscarNav.setBorderPainted(false);
        btnBuscarNav.setContentAreaFilled(false);
        btnBuscarNav.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarNav.setFocusPainted(false);
        btnBuscarNav.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarNavActionPerformed(evt);
            }
        });
        pnl_Nav.add(btnBuscarNav, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 210, 50, 50));

        btnDDGo.setBackground(new java.awt.Color(255, 255, 255));
        btnDDGo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs_Nav/duckduckgo.png"))); // NOI18N
        btnDDGo.setBorder(null);
        btnDDGo.setContentAreaFilled(false);
        btnDDGo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDDGo.setFocusPainted(false);
        btnDDGo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDDGoActionPerformed(evt);
            }
        });
        pnl_Nav.add(btnDDGo, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 320, 70, 70));

        btnGoogle.setBackground(new java.awt.Color(255, 255, 255));
        btnGoogle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs_Nav/google.png"))); // NOI18N
        btnGoogle.setBorder(null);
        btnGoogle.setContentAreaFilled(false);
        btnGoogle.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGoogle.setFocusPainted(false);
        btnGoogle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoogleActionPerformed(evt);
            }
        });
        pnl_Nav.add(btnGoogle, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 320, 70, 70));

        btnBing.setBackground(new java.awt.Color(255, 255, 255));
        btnBing.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs_Nav/bing.png"))); // NOI18N
        btnBing.setBorder(null);
        btnBing.setContentAreaFilled(false);
        btnBing.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBing.setFocusPainted(false);
        btnBing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBingActionPerformed(evt);
            }
        });
        pnl_Nav.add(btnBing, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 320, 70, 70));

        btnYahoo.setBackground(new java.awt.Color(255, 255, 255));
        btnYahoo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs_Nav/yahoo.png"))); // NOI18N
        btnYahoo.setBorder(null);
        btnYahoo.setContentAreaFilled(false);
        btnYahoo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnYahoo.setFocusPainted(false);
        btnYahoo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnYahooActionPerformed(evt);
            }
        });
        pnl_Nav.add(btnYahoo, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 320, 70, 70));

        jTabbedPane1.addTab("Navegador", pnl_Nav);

        pnl_Notes.setBackground(new java.awt.Color(255, 255, 255));
        pnl_Notes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnOpenNotes.setBackground(new java.awt.Color(231, 231, 231));
        btnOpenNotes.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btnOpenNotes.setText("Abrir Documento");
        btnOpenNotes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOpenNotes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenNotesActionPerformed(evt);
            }
        });
        pnl_Notes.add(btnOpenNotes, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 490, 148, -1));

        btnSaveDocNotes.setBackground(new java.awt.Color(231, 231, 231));
        btnSaveDocNotes.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btnSaveDocNotes.setText("Guardar Documento");
        btnSaveDocNotes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSaveDocNotes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveDocNotesActionPerformed(evt);
            }
        });
        pnl_Notes.add(btnSaveDocNotes, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 490, -1, -1));

        btnClearNotes.setBackground(new java.awt.Color(231, 231, 231));
        btnClearNotes.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btnClearNotes.setText("Limpiar");
        btnClearNotes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClearNotes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearNotesActionPerformed(evt);
            }
        });
        pnl_Notes.add(btnClearNotes, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 490, -1, -1));

        txtTamaño.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        txtTamaño.setText("Tamaño:");
        pnl_Notes.add(txtTamaño, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 120, 100, -1));

        jcomboFont.setBackground(new java.awt.Color(231, 231, 231));
        jcomboFont.setForeground(new java.awt.Color(51, 51, 51));
        jcomboFont.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "10", "12", "14", "18", "24" }));
        jcomboFont.setBorder(null);
        pnl_Notes.add(jcomboFont, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 150, 100, -1));

        txtFuente.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        txtFuente.setText("Fuente:");
        pnl_Notes.add(txtFuente, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 310, -1, -1));

        jComboStyle.setBackground(new java.awt.Color(231, 231, 231));
        jComboStyle.setForeground(new java.awt.Color(51, 51, 51));
        jComboStyle.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Plain", "Bold", "Italic", "Bold Italic" }));
        jComboStyle.setBorder(null);
        pnl_Notes.add(jComboStyle, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 340, -1, -1));

        txtColor.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        txtColor.setText("Color:");
        pnl_Notes.add(txtColor, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 210, -1, -1));

        cmbNotes.setBackground(new java.awt.Color(231, 231, 231));
        cmbNotes.setForeground(new java.awt.Color(51, 51, 51));
        cmbNotes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Negro", "Rojo", "Azul", "Verde" }));
        cmbNotes.setBorder(null);
        pnl_Notes.add(cmbNotes, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 240, 85, -1));

        txtarea.setColumns(20);
        txtarea.setRows(5);
        jScrollPane1.setViewportView(txtarea);

        pnl_Notes.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 570, 360));

        btnSaveNotes.setBackground(new java.awt.Color(231, 231, 231));
        btnSaveNotes.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btnSaveNotes.setText("Guardar");
        btnSaveNotes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveNotesActionPerformed(evt);
            }
        });
        pnl_Notes.add(btnSaveNotes, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 400, -1, 30));

        jTabbedPane1.addTab("Apuntes", pnl_Notes);

        pnl_Snake.setBackground(new java.awt.Color(255, 255, 255));
        pnl_Snake.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pnl_SnakeKeyPressed(evt);
            }
        });

        btnSnakePlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs_Snake/play0.png"))); // NOI18N
        btnSnakePlay.setBorderPainted(false);
        btnSnakePlay.setContentAreaFilled(false);
        btnSnakePlay.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSnakePlay.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSnakePlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSnakePlayActionPerformed(evt);
            }
        });

        btnSnakeStop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs_Snake/stop0.png"))); // NOI18N
        btnSnakeStop.setBorderPainted(false);
        btnSnakeStop.setContentAreaFilled(false);
        btnSnakeStop.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSnakeStop.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSnakeStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSnakeStopActionPerformed(evt);
            }
        });

        btnSnakeRestart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs_Snake/restart0.png"))); // NOI18N
        btnSnakeRestart.setBorderPainted(false);
        btnSnakeRestart.setContentAreaFilled(false);
        btnSnakeRestart.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSnakeRestart.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSnakeRestart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSnakeRestartActionPerformed(evt);
            }
        });

        txtSnakeScore.setFont(new java.awt.Font("Lucida Sans", 0, 22)); // NOI18N
        txtSnakeScore.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtSnakeScore.setText("0");

        txtSnakeHighScore.setFont(new java.awt.Font("Lucida Sans", 0, 22)); // NOI18N
        txtSnakeHighScore.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtSnakeHighScore.setText("0");
        txtSnakeHighScore.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtSnakeHighScore.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSnakeHighScoreMouseClicked(evt);
            }
        });

        txtScoreSnake.setFont(new java.awt.Font("Lucida Sans", 0, 20)); // NOI18N
        txtScoreSnake.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtScoreSnake.setText("Puntaje:");

        txtRecordScore.setFont(new java.awt.Font("Lucida Sans", 0, 20)); // NOI18N
        txtRecordScore.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRecordScore.setText("Récord:");

        sldrDificultad.setBackground(new java.awt.Color(204, 204, 204));
        sldrDificultad.setForeground(new java.awt.Color(0, 0, 0));
        sldrDificultad.setMajorTickSpacing(1);
        sldrDificultad.setMaximum(3);
        sldrDificultad.setMinimum(1);
        sldrDificultad.setMinorTickSpacing(1);
        sldrDificultad.setPaintLabels(true);
        sldrDificultad.setPaintTicks(true);
        sldrDificultad.setValue(2);
        sldrDificultad.setOpaque(false);
        sldrDificultad.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sldrDificultadStateChanged(evt);
            }
        });

        txtSnakeStart.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        txtSnakeStart.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtSnakeStart.setText("Iniciar");

        txtSnakeStop.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        txtSnakeStop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtSnakeStop.setText("Pausar/Reanudar");

        txtSnakeRestart.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        txtSnakeRestart.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtSnakeRestart.setText("Reiniciar");

        txtSnakeDifficult.setFont(new java.awt.Font("Lucida Sans", 0, 18)); // NOI18N
        txtSnakeDifficult.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtSnakeDifficult.setText("Dificultad");

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs_Snake/drawSnake0.png"))); // NOI18N
        jLabel8.setText(".");
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        txtSnakePts.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        txtSnakePts.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtSnakePts.setText("Pts: 0");

        javax.swing.GroupLayout pnl_SnakeLayout = new javax.swing.GroupLayout(pnl_Snake);
        pnl_Snake.setLayout(pnl_SnakeLayout);
        pnl_SnakeLayout.setHorizontalGroup(
            pnl_SnakeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_SnakeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_SnakeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_SnakeLayout.createSequentialGroup()
                        .addGroup(pnl_SnakeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSnakePts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 564, Short.MAX_VALUE)
                        .addGroup(pnl_SnakeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_SnakeLayout.createSequentialGroup()
                                .addComponent(btnSnakeStop, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_SnakeLayout.createSequentialGroup()
                                .addComponent(btnSnakePlay, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34))
                            .addComponent(txtSnakeStop, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_SnakeLayout.createSequentialGroup()
                                .addGroup(pnl_SnakeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtRecordScore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtScoreSnake, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnl_SnakeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtSnakeScore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtSnakeHighScore, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_SnakeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnl_SnakeLayout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addGroup(pnl_SnakeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtSnakeDifficult, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(sldrDificultad, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(txtSnakeRestart, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_SnakeLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtSnakeStart, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_SnakeLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSnakeRestart, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );
        pnl_SnakeLayout.setVerticalGroup(
            pnl_SnakeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_SnakeLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(pnl_SnakeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSnakeScore, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtScoreSnake, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(pnl_SnakeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtSnakeHighScore, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRecordScore, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnl_SnakeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnl_SnakeLayout.createSequentialGroup()
                        .addComponent(txtSnakePts)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnl_SnakeLayout.createSequentialGroup()
                        .addComponent(btnSnakePlay, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSnakeStart, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSnakeStop, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSnakeStop, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSnakeRestart, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSnakeRestart, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sldrDificultad, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSnakeDifficult)))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab5", pnl_Snake);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, 750, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        this.setVisible(false);
        System.exit(0);
    }//GEN-LAST:event_jLabel6MouseClicked

    private void pnl_BtnDAMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_BtnDAMouseEntered
        MouseAnimPanel(pnl_BtnDA, 0);
    }//GEN-LAST:event_pnl_BtnDAMouseEntered

    private void pnl_BtnDAMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_BtnDAMouseExited
        MouseAnimPanel(pnl_BtnDA, 1);
    }//GEN-LAST:event_pnl_BtnDAMouseExited

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_formMouseClicked

    private void pnl_BtnAhorcadoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_BtnAhorcadoMouseEntered
        MouseAnimPanel(pnl_BtnAhorcado, 0);
    }//GEN-LAST:event_pnl_BtnAhorcadoMouseEntered

    private void pnl_BtnAhorcadoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_BtnAhorcadoMouseExited
        MouseAnimPanel(pnl_BtnAhorcado, 1);
    }//GEN-LAST:event_pnl_BtnAhorcadoMouseExited

    private void jp_TopMovMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jp_TopMovMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_jp_TopMovMousePressed

    private void jp_TopMovMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jp_TopMovMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();

        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_jp_TopMovMouseDragged

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        this.setExtendedState(1);
    }//GEN-LAST:event_jLabel13MouseClicked

    private void pnl_BtnNotesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_BtnNotesMouseEntered
        MouseAnimPanel(pnl_BtnNotes, 0);
    }//GEN-LAST:event_pnl_BtnNotesMouseEntered

    private void pnl_BtnNotesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_BtnNotesMouseExited
        MouseAnimPanel(pnl_BtnNotes, 1);
    }//GEN-LAST:event_pnl_BtnNotesMouseExited

    private void pnl_BtnCalcMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_BtnCalcMouseEntered
        MouseAnimPanel(pnl_BtnCalc, 0);
    }//GEN-LAST:event_pnl_BtnCalcMouseEntered

    private void pnl_BtnCalcMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_BtnCalcMouseExited
        MouseAnimPanel(pnl_BtnCalc, 1);
    }//GEN-LAST:event_pnl_BtnCalcMouseExited

    private void pnl_BtnNavMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_BtnNavMouseEntered
        MouseAnimPanel(pnl_BtnNav, 0);
    }//GEN-LAST:event_pnl_BtnNavMouseEntered

    private void pnl_BtnNavMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_BtnNavMouseExited
        MouseAnimPanel(pnl_BtnNav, 1);
    }//GEN-LAST:event_pnl_BtnNavMouseExited

    private void pnl_BtnGameMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_BtnGameMouseEntered
        MouseAnimPanel(pnl_BtnGame, 0);
    }//GEN-LAST:event_pnl_BtnGameMouseEntered

    private void pnl_BtnGameMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_BtnGameMouseExited
        MouseAnimPanel(pnl_BtnGame, 1);
    }//GEN-LAST:event_pnl_BtnGameMouseExited

    private void btnFacebookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacebookActionPerformed
        queBusco = "https://instagram.com";
        buscar();
    }//GEN-LAST:event_btnFacebookActionPerformed

    private void btnWhatsAppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWhatsAppActionPerformed
        queBusco = "https://web.whatsapp.com";
        buscar();
    }//GEN-LAST:event_btnWhatsAppActionPerformed

    private void btnRedditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRedditActionPerformed
        queBusco = "https://youtube.com";
        buscar();
    }//GEN-LAST:event_btnRedditActionPerformed

    private void btnYT1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnYT1ActionPerformed
        queBusco = "https://facebook.com";
        buscar();
    }//GEN-LAST:event_btnYT1ActionPerformed

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        this.dispose();
    }//GEN-LAST:event_jLabel15MouseClicked

    private void jLabel15MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel15MouseEntered

    private void jLabel15MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseExited

    }//GEN-LAST:event_jLabel15MouseExited

    private void btnPrz1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrz1ActionPerformed
        try {
            URLBtn1 = settings_da.URLBtn10;
            queBusco = settings_da.txt_Url.getText();
            buscar();
        } catch (Exception e) {
            System.out.println(e);
        }

    }//GEN-LAST:event_btnPrz1ActionPerformed

    private void btnPrz2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrz2ActionPerformed
        try {
            URLBtn2 = settings_da.URLBtn20;
            queBusco = settings_da.txt_Url.getText();
            buscar();
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btnPrz2ActionPerformed

    private void btnPrz3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrz3ActionPerformed
        try {
            URLBtn3 = settings_da.URLBtn30;
            queBusco = settings_da.txt_Url.getText();
            buscar();
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btnPrz3ActionPerformed

    private void btnPrz4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrz4ActionPerformed
        try {
            URLBtn4 = settings_da.URLBtn40;
            queBusco = settings_da.txt_Url.getText();
            buscar();
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btnPrz4ActionPerformed

    private void pnl_BtnAhorcadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_BtnAhorcadoMouseClicked
        jTabbedPane1.setSelectedIndex(1);
        switch (lang) {
            case "es":
                txtTop.setText("Ahorcado");
                break;
            case "en":
                txtTop.setText("Hangman");
                break;
            case "pg":
                txtTop.setText("Enforcado");
                break;
            default:
                break;
        }
    }//GEN-LAST:event_pnl_BtnAhorcadoMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        settings_da.setVisible(true);
        settings_da.setLocationRelativeTo(null);
        settings_da.setResizable(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
        settings_mn.setVisible(true);
        settings_mn.setLocationRelativeTo(null);
        settings_mn.setResizable(false);
    }//GEN-LAST:event_jLabel17MouseClicked

    private void btn9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn9ActionPerformed
        DrawNums("9");
    }//GEN-LAST:event_btn9ActionPerformed

    private void btn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn7ActionPerformed
        DrawNums("7");
    }//GEN-LAST:event_btn7ActionPerformed

    private void btn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn8ActionPerformed
        DrawNums("8");
    }//GEN-LAST:event_btn8ActionPerformed

    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn4ActionPerformed
        DrawNums("4");
    }//GEN-LAST:event_btn4ActionPerformed

    private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5ActionPerformed
        DrawNums("5");
    }//GEN-LAST:event_btn5ActionPerformed

    private void btn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn6ActionPerformed
        DrawNums("6");
    }//GEN-LAST:event_btn6ActionPerformed

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        DrawNums("1");
    }//GEN-LAST:event_btn1ActionPerformed

    private void btnDotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDotActionPerformed
        DrawNums(".");
    }//GEN-LAST:event_btnDotActionPerformed

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3ActionPerformed
        DrawNums("3");
    }//GEN-LAST:event_btn3ActionPerformed

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
        DrawNums("2");
    }//GEN-LAST:event_btn2ActionPerformed

    private void btn0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn0ActionPerformed
        DrawNums("0");
    }//GEN-LAST:event_btn0ActionPerformed

    private void btnEqualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEqualActionPerformed
        try {
            String result0;
            val2 = Float.parseFloat(valsTxt.getText());
            switch (operat) {
                case '+':
                    System.out.println("Suma");
                    result = val1 + val2;
                    result0 = "" + result;
                    valsTxt.setText(result0);
                    break;
                case '-':
                    System.out.println("Resta");
                    result = val1 - val2;
                    result0 = "" + result;
                    valsTxt.setText(result0);
                    break;
                case '*':
                    System.out.println("Multiplicacion");
                    result = val1 * val2;
                    result0 = "" + result;
                    valsTxt.setText(result0);
                    break;
                case '/':
                    System.out.println("Division");
                    result = val1 / val2;
                    result0 = "" + result;
                    valsTxt.setText(result0);
                    break;
                case '^':
                    System.out.println("Exponenciacion");
                    result = (float) Math.pow(val1, val2);
                    result0 = "" + result;
                    valsTxt.setText(result0);
                    break;
                default:
                    System.out.println("Error.");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

    }//GEN-LAST:event_btnEqualActionPerformed

    private void btnDivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDivActionPerformed
        try {
            if (turno == 0 && !(valsTxt.getText().equals(""))) {
                val1 = Float.parseFloat(valsTxt.getText());
                valsTxt.setText("");
                operat = '/';
                turno = 1;
            } else {
                val1 = result;
                valsTxt.setText("");
                operat = '/';
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

    }//GEN-LAST:event_btnDivActionPerformed

    private void btnRestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestActionPerformed
        try {
            if (turno == 0 && !(valsTxt.getText().equals(""))) {
                val1 = Float.parseFloat(valsTxt.getText());
                valsTxt.setText("");
                operat = '-';
                turno = 1;
            } else {
                val1 = result;
                valsTxt.setText("");
                operat = '-';
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

    }//GEN-LAST:event_btnRestActionPerformed

    private void btnSumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSumActionPerformed
        try {
            if (turno == 0 && !(valsTxt.getText().equals(""))) {
                val1 = Float.parseFloat(valsTxt.getText());
                valsTxt.setText("");
                operat = '+';
                turno = 1;
            } else {
                val1 = result;
                valsTxt.setText("");
                operat = '+';
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

    }//GEN-LAST:event_btnSumActionPerformed

    private void btnForActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnForActionPerformed
        try {
            if (turno == 0 && !(valsTxt.getText().equals(""))) {
                val1 = Float.parseFloat(valsTxt.getText());
                valsTxt.setText("");
                operat = '*';
                turno = 1;
            } else {
                val1 = result;
                valsTxt.setText("");
                operat = '*';
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

    }//GEN-LAST:event_btnForActionPerformed

    private void btnCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCActionPerformed
        valsTxt.setText("");
        val1 = 0;
        val2 = 0;
        result = 0;
        operat = 'n';
        turno = 0;
    }//GEN-LAST:event_btnCActionPerformed

    private void btnPIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPIActionPerformed
        DrawNums(("3.1415926"));
    }//GEN-LAST:event_btnPIActionPerformed

    private void btnExpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExpActionPerformed
        if (turno == 0 && !(valsTxt.getText().equals(""))) {
            val1 = Float.parseFloat(valsTxt.getText());
            valsTxt.setText("");
            operat = '^';
            turno = 1;
        } else {
            val1 = result;
            valsTxt.setText("");
            operat = '^';
        }
    }//GEN-LAST:event_btnExpActionPerformed

    private void btnSqr2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSqr2ActionPerformed
        String result0;
        if (!(valsTxt.getText().equals(""))) {
            val1 = Float.parseFloat(valsTxt.getText());
            operat = 'n';

            System.out.println("Raiz de 2");
            result = (float) Math.sqrt(val1);
            result0 = "" + result;
            valsTxt.setText(result0);
        }
    }//GEN-LAST:event_btnSqr2ActionPerformed

    private void btnSqr3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSqr3ActionPerformed
        String result0;
        if (!(valsTxt.getText().equals(""))) {
            val1 = Float.parseFloat(valsTxt.getText());
            operat = 'n';

            System.out.println("Raiz de 2");
            result = (float) Math.cbrt(val1);
            result0 = "" + result;
            valsTxt.setText(result0);
        }
    }//GEN-LAST:event_btnSqr3ActionPerformed

    private void btnSqrPrzActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSqrPrzActionPerformed
        try {
            String result0;

            float valNum = Float.parseFloat(txtNumSqrt.getText());
            float valSqrt = Float.parseFloat(txtNumRaiz.getText());

            operat = 'n';

            System.out.println("Raiz Personalizada");
            System.out.println("Num: " + valNum);
            System.out.println("Sqrt: " + valSqrt);

            result = (float) Math.pow(valNum, (float) 1 / valSqrt);
            System.out.println("Resultado = " + result);

            result0 = "" + result;
            valsTxt.setText(result0);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

    }//GEN-LAST:event_btnSqrPrzActionPerformed

    private void txtNumRaizMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNumRaizMouseClicked
        txtNumRaiz.setText("");
    }//GEN-LAST:event_txtNumRaizMouseClicked

    private void txtNumSqrtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNumSqrtMouseClicked
        txtNumSqrt.setText("");
    }//GEN-LAST:event_txtNumSqrtMouseClicked

    private void pnl_BtnCalcMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_BtnCalcMouseClicked
        jTabbedPane1.setSelectedIndex(2);
        switch (lang) {
            case "es":
                txtTop.setText("Calculadora");
                break;
            case "en":
                txtTop.setText("Calculator");
                break;
            case "pg":
                txtTop.setText("Calculadora");
                break;
            default:
                break;
        }
    }//GEN-LAST:event_pnl_BtnCalcMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        settings_cc.setVisible(true);
        settings_cc.setResizable(false);
        settings_cc.setLocationRelativeTo(null);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void pnl_CalcKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pnl_CalcKeyPressed
        keyboardCalc(evt);
    }//GEN-LAST:event_pnl_CalcKeyPressed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
    }//GEN-LAST:event_formKeyPressed

    private void valsTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_valsTxtKeyPressed
    }//GEN-LAST:event_valsTxtKeyPressed

    private void btnCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCKeyPressed
    }//GEN-LAST:event_btnCKeyPressed

    private void btnOpenNotesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenNotesActionPerformed
        if (seleccionar.showDialog(null, "Abrir") == JFileChooser.APPROVE_OPTION) {
            archivo = seleccionar.getSelectedFile();
            if (archivo.canRead()) {
                if (archivo.getName().endsWith("txt")) {
                    String documento = AbrirArchivo(archivo);
                    txtarea.setText(documento);
                } else {
                    JOptionPane.showMessageDialog(null, "Archivo no compatible.");
                }
            }
        }
    }//GEN-LAST:event_btnOpenNotesActionPerformed

    private void btnSaveDocNotesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveDocNotesActionPerformed
        if (seleccionar.showDialog(null, "Guardar") == JFileChooser.APPROVE_OPTION) {
            archivo = seleccionar.getSelectedFile();
            if (archivo.getName().endsWith("txt")) {
                String Documento = txtarea.getText();
                String mensaje = GuardarArchivo(archivo, Documento);
                if (mensaje != null) {
                    JOptionPane.showMessageDialog(null, mensaje);
                } else {
                    JOptionPane.showMessageDialog(null, "Archivo no compatible");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error, falta agregar extension '.txt'");
            }
        }
    }//GEN-LAST:event_btnSaveDocNotesActionPerformed

    private void btnClearNotesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearNotesActionPerformed
        txtarea.setText("");
    }//GEN-LAST:event_btnClearNotesActionPerformed

    private void pnl_BtnNotesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_BtnNotesMouseClicked
        jTabbedPane1.setSelectedIndex(4);
        switch (lang) {
            case "es":
                txtTop.setText("Notas");
                break;
            case "en":
                txtTop.setText("Notes");
                break;
            case "pg":
                txtTop.setText("Notas");
                break;
            default:
                break;
        }
    }//GEN-LAST:event_pnl_BtnNotesMouseClicked

    private void pnl_BtnDAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_BtnDAMouseClicked
        jTabbedPane1.setSelectedIndex(0);
        switch (lang) {
            case "es":
                txtTop.setText("Accesos Directos");
                break;
            case "en":
                txtTop.setText("Direct Access");
                break;
            case "pg":
                txtTop.setText("Acceso Direto");
                break;
            default:
                break;
        }
    }//GEN-LAST:event_pnl_BtnDAMouseClicked

    private void pnl_BtnSettingsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_BtnSettingsMouseClicked
    }//GEN-LAST:event_pnl_BtnSettingsMouseClicked

    private void btnSaveNotesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveNotesActionPerformed
        String jcomboFuente = (String) jcomboFont.getSelectedItem();
        String jcomboStyle = (String) jComboStyle.getSelectedItem();
        String jcomboColor = (String) cmbNotes.getSelectedItem();

        switch (jcomboFuente) {
            case "10":
                txtarea.setFont(new java.awt.Font("Dialog", 1, 10));
                Font = 10;
                break;
            case "12":
                txtarea.setFont(new java.awt.Font("Dialog", 2, 12));
                Font = 12;
                break;
            case "14":
                txtarea.setFont(new java.awt.Font("Dialog", 3, 14));
                Font = 14;
                break;
            case "18":
                txtarea.setFont(new java.awt.Font("Dialog", 0, 18));
                Font = 18;
                break;
            case "24":
                txtarea.setFont(new java.awt.Font("Dialog", 0, 24));
                Font = 24;
                break;
            default:
                break;
        }

        switch (jcomboStyle) {
            case "Plain":
                txtarea.setFont(new java.awt.Font("Dialog", 0, Font));
                break;
            case "Bold":
                txtarea.setFont(new java.awt.Font("Dialog", 1, Font));
                break;
            case "Italic":
                txtarea.setFont(new java.awt.Font("Dialog", 2, Font));
                break;
            case "Bold Italic":
                txtarea.setFont(new java.awt.Font("Dialog", 3, Font));
                break;
        }

        switch (jcomboColor) {
            case "Negro":
                txtarea.setForeground(new java.awt.Color(0, 0, 0));
                break;
            case "Rojo":
                txtarea.setForeground(new java.awt.Color(255, 0, 51));
                break;
            case "Azul":
                txtarea.setForeground(new java.awt.Color(0, 102, 255));
                break;
            case "Verde":
                txtarea.setForeground(new java.awt.Color(51, 204, 0));
                break;
        }
    }//GEN-LAST:event_btnSaveNotesActionPerformed

    private void combo_NavActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_NavActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_NavActionPerformed

    private void btnBuscarNavActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarNavActionPerformed
        String TomarComboSearch = (String) combo_Nav.getSelectedItem();
        System.out.println("Se ha seleccionado: " + TomarComboSearch);

        String txt_buscar1 = txt_Buscar.getText();

        int asd = txt_Buscar.getText().split("\\s+|\n|,").length;

        switch (TomarComboSearch) {
            case "Google":
                if (txt_buscar1.equals("")) {
                    JOptionPane.showMessageDialog(null, "Has dejado un espacio en blanco");
                } else if (asd > 1) {
                    String val3 = txt_buscar1.replace(" ", "+");
                    BuscarCon.buscarGoogle = val3;
                    BuscarCon.buscarConGoogle();
                } else if (asd == 1) {
                    BuscarCon.buscarGoogle = txt_buscar1;
                    BuscarCon.buscarConGoogle();
                }

                break;

            case "Bing":
                if (txt_buscar1.equals("")) {
                    JOptionPane.showMessageDialog(null, "Has dejado un espacio en blanco");
                } else if (asd > 1) {
                    String val3 = txt_buscar1.replace(" ", "+");
                    BuscarCon.buscarBing = val3;
                    BuscarCon.buscarConBing();
                } else if (asd == 1) {
                    BuscarCon.buscarBing = txt_buscar1;
                    BuscarCon.buscarConBing();
                }

                break;
            case "DuckDuckGo":
                if (txt_Buscar.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Has dejado un espacio en blanco");
                } else if (asd > 1) {
                    String val3 = txt_buscar1.replace(" ", "+");
                    BuscarCon.buscarDuck = val3;
                    BuscarCon.buscarConDuck();
                } else if (asd == 1) {
                    BuscarCon.buscarDuck = txt_buscar1;
                    BuscarCon.buscarConDuck();
                }

                break;

            case "Yahoo":
                if (txt_Buscar.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Has dejado un espacio en blanco");
                } else if (asd > 1) {
                    String val3 = txt_buscar1.replace(" ", "+");
                    BuscarCon.buscarYahoo = val3;
                    BuscarCon.buscarConYahoo();
                } else if (asd == 1) {
                    BuscarCon.buscarYahoo = txt_buscar1;
                    BuscarCon.buscarConYahoo();
                }

                break;

            default:
                JOptionPane.showMessageDialog(null, "Error.");
                break;
        }
    }//GEN-LAST:event_btnBuscarNavActionPerformed

    private void pnl_BtnNavMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_BtnNavMouseClicked
        jTabbedPane1.setSelectedIndex(3);
        switch (lang) {
            case "es":
                txtTop.setText("Navegador");
                break;
            case "en":
                txtTop.setText("Browser");
                break;
            case "pg":
                txtTop.setText("Navegador");
                break;
            default:
                break;
        }
    }//GEN-LAST:event_pnl_BtnNavMouseClicked

    private void btnGoogleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoogleActionPerformed
        queBusco = "https://google.com";
        buscar();
    }//GEN-LAST:event_btnGoogleActionPerformed

    private void btnBingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBingActionPerformed
        queBusco = "https://bing.com";
        buscar();
    }//GEN-LAST:event_btnBingActionPerformed

    private void btnYahooActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnYahooActionPerformed
        queBusco = "https://yahoo.com";
        buscar();
    }//GEN-LAST:event_btnYahooActionPerformed

    private void btnDDGoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDDGoActionPerformed
        queBusco = "https://duckduckgo.com";
        buscar();
    }//GEN-LAST:event_btnDDGoActionPerformed

    private void btnGitHubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGitHubActionPerformed
        queBusco = "https://github.com";
        buscar();
    }//GEN-LAST:event_btnGitHubActionPerformed

    private void btnGmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGmailActionPerformed
        queBusco = "https://gmail.com";
        buscar();
    }//GEN-LAST:event_btnGmailActionPerformed

    private void btnPintrstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPintrstActionPerformed
        queBusco = "https://pinterest.com";
        buscar();
    }//GEN-LAST:event_btnPintrstActionPerformed

    private void btnTwitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTwitterActionPerformed
        queBusco = "https://twitter.com";
        buscar();
    }//GEN-LAST:event_btnTwitterActionPerformed

    private void btn_ReiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ReiniciarActionPerformed
        PoderJugar = true;
        Vida = 6;
        String Vida1 = Integer.toString(Vida);
        txt_nVida.setText(Vida1);
        AhorcadoAnim(ANMAhorcado = 0);

        limpiarBotones();
        GenerarNuevaPalabra();
        PalabraOculta = palabraSecreta;
        System.out.println(PalabraOculta);
        int nLetrasPalabraSecreta = palabraSecreta.length();
        System.out.println(nLetrasPalabraSecreta);

        String nLetras = Integer.toString(nLetrasPalabraSecreta);
        txt_nLetras.setText(nLetras);

        switch (PalabraOculta) {
            case "JESUCRISTO":
                JOptionPane.showMessageDialog(null, "Murio y resucito de entre los muertos.");
                PalabraOculta = "JESUCRISTO";
                ConvertirPalabraAGuiones();
                txt_PalabraOculta.setText(PalabraOcultaEnGuiones);
                break;

            case "ZANAHORIA":
                JOptionPane.showMessageDialog(null, "Verdura anaranjada que tiene forma de cono de helado.");
                PalabraOculta = "ZANAHORIA";
                ConvertirPalabraAGuiones();
                txt_PalabraOculta.setText(PalabraOcultaEnGuiones);
                break;

            case "LIBELULA":
                JOptionPane.showMessageDialog(null, "El estudio de este insecto encontrado en rios y lagos se llama odonatología.");
                PalabraOculta = "LIBELULA";
                ConvertirPalabraAGuiones();
                txt_PalabraOculta.setText(PalabraOcultaEnGuiones);
                break;

            case "PRESIDENTE":
                JOptionPane.showMessageDialog(null, "Cabeza del estado de una republica.");
                PalabraOculta = "PRESIDENTE";
                ConvertirPalabraAGuiones();
                txt_PalabraOculta.setText(PalabraOcultaEnGuiones);
                break;

            case "RINOCERONTE":
                JOptionPane.showMessageDialog(null, "Animal con dos cuernos en la parte frontal de la cabeza.");
                PalabraOculta = "RINOCERONTE";
                ConvertirPalabraAGuiones();
                txt_PalabraOculta.setText(PalabraOcultaEnGuiones);
                break;

            case "ARAÑA":
                JOptionPane.showMessageDialog(null, "Se les conoce como aracnidos y producen seda.");
                PalabraOculta = "ARAÑA";
                ConvertirPalabraAGuiones();
                txt_PalabraOculta.setText(PalabraOcultaEnGuiones);
                break;

            default:
                break;
        }
    }//GEN-LAST:event_btn_ReiniciarActionPerformed

    private void Letra_AActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Letra_AActionPerformed
        if (PoderJugar == false) {
            JOptionPane.showMessageDialog(null, "Error, Inicia una nueva partida.");
        } else {
            if (PalabraOculta.contains("A")) {
                switch (PalabraOculta) {
                    case "LIBELULA": {
                        Coords = 7;
                        Letra = 'A';
                        BtnsLetras();
                        palabraOculta2 = palabraOculta1.toString();
                        PalabraOcultaEnGuiones = palabraOculta2;
                        txt_PalabraOculta.setText(palabraOculta2);
                        Letra_A.setVisible(false);

                        VerificarSiGane("LIBELULA");

                        break;
                    }
                    case "ZANAHORIA": {
                        Coords = 1;
                        Letra = 'A';
                        BtnsLetras();
                        palabraOculta2 = palabraOculta1.toString();
                        PalabraOcultaEnGuiones = palabraOculta2;
                        txt_PalabraOculta.setText(palabraOculta2);

                        Coords = 3;
                        Letra = 'A';
                        BtnsLetras();
                        palabraOculta2 = palabraOculta1.toString();
                        PalabraOcultaEnGuiones = palabraOculta2;
                        txt_PalabraOculta.setText(palabraOculta2);

                        Coords = 8;
                        Letra = 'A';
                        BtnsLetras();
                        palabraOculta2 = palabraOculta1.toString();
                        PalabraOcultaEnGuiones = palabraOculta2;
                        txt_PalabraOculta.setText(palabraOculta2);
                        Letra_A.setVisible(false);

                        VerificarSiGane("ZANAHORIA");
                    }
                    break;
                    default:
                        break;
                }
            } else {
                if (Vida == 0) {

                } else {
                    if (Vida == 1) {
                        JOptionPane.showMessageDialog(null, "Has perdido.");
                        AhorcadoAnim(++ANMAhorcado);
                        Vida--;
                        String txtVida = Integer.toString(Vida);
                        PoderJugar = false;
                        txt_nVida.setText(txtVida);
                        Letra_A.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error, Perdiste una vida.");
                        AhorcadoAnim(++ANMAhorcado);
                        Vida--;
                        String txtVida = Integer.toString(Vida);
                        txt_nVida.setText(txtVida);
                        Letra_A.setVisible(false);
                    }

                }
            }
        }
    }//GEN-LAST:event_Letra_AActionPerformed

    private void Letra_EActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Letra_EActionPerformed
        if (PoderJugar == false) {
            JOptionPane.showMessageDialog(null, "Error, Inicia una nueva partida.");
        } else {
            if (PalabraOculta.contains("E")) {
                switch (PalabraOculta) {
                    case "PRESIDENTE": {
                        Coords = 2;
                        Letra = 'E';
                        BtnsLetras();
                        palabraOculta2 = palabraOculta1.toString();
                        PalabraOcultaEnGuiones = palabraOculta2;
                        txt_PalabraOculta.setText(palabraOculta2);

                        Coords = 6;
                        Letra = 'E';
                        BtnsLetras();
                        palabraOculta2 = palabraOculta1.toString();
                        PalabraOcultaEnGuiones = palabraOculta2;
                        txt_PalabraOculta.setText(palabraOculta2);

                        Coords = 9;
                        Letra = 'E';
                        BtnsLetras();
                        palabraOculta2 = palabraOculta1.toString();
                        PalabraOcultaEnGuiones = palabraOculta2;
                        txt_PalabraOculta.setText(palabraOculta2);
                        Letra_E.setVisible(false);

                        VerificarSiGane("PRESIDENTE");
                        break;
                    }
                    case "JESUCRISTO": {
                        Coords = 1;
                        Letra = 'E';
                        BtnsLetras();
                        palabraOculta2 = palabraOculta1.toString();
                        PalabraOcultaEnGuiones = palabraOculta2;
                        txt_PalabraOculta.setText(palabraOculta2);
                        Letra_E.setVisible(false);

                        VerificarSiGane("JESUCRISTO");
                        break;
                    }
                    case "LIBELULA": {
                        Coords = 3;
                        Letra = 'E';
                        BtnsLetras();
                        palabraOculta2 = palabraOculta1.toString();
                        PalabraOcultaEnGuiones = palabraOculta2;
                        txt_PalabraOculta.setText(palabraOculta2);
                        Letra_E.setVisible(false);

                        VerificarSiGane("LIBELULA");
                        break;
                    }
                    case "RINOCERONTE": {
                        Coords = 5;
                        Letra = 'E';
                        BtnsLetras();
                        palabraOculta2 = palabraOculta1.toString();
                        PalabraOcultaEnGuiones = palabraOculta2;
                        txt_PalabraOculta.setText(palabraOculta2);

                        Coords = 10;
                        Letra = 'E';
                        BtnsLetras();
                        palabraOculta2 = palabraOculta1.toString();
                        PalabraOcultaEnGuiones = palabraOculta2;
                        txt_PalabraOculta.setText(palabraOculta2);
                        Letra_E.setVisible(false);

                        VerificarSiGane("RINOCERONTE");
                        break;
                    }

                    default:
                        JOptionPane.showMessageDialog(null, "Error de sintaxis.");
                        break;
                }
            } else {
                if (Vida == 0) {

                } else {
                    if (Vida == 1) {
                        JOptionPane.showMessageDialog(null, "Has perdido.");
                        AhorcadoAnim(++ANMAhorcado);
                        Vida--;
                        String txtVida = Integer.toString(Vida);
                        PoderJugar = false;
                        txt_nVida.setText(txtVida);
                        Letra_E.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error, Perdiste una vida.");
                        AhorcadoAnim(++ANMAhorcado);
                        Vida--;
                        String txtVida = Integer.toString(Vida);
                        txt_nVida.setText(txtVida);

                        Letra_E.setVisible(false);
                    }

                }
            }
        }
    }//GEN-LAST:event_Letra_EActionPerformed

    private void Letra_IActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Letra_IActionPerformed
        if (PoderJugar == false) {
            JOptionPane.showMessageDialog(null, "Error, Inicia una nueva partida.");
        } else {
            if (PalabraOculta.contains("I")) {
                switch (PalabraOculta) {
                    case "PRESIDENTE": {
                        Coords = 4;
                        Letra = 'I';
                        BtnsLetras();
                        palabraOculta2 = palabraOculta1.toString();
                        PalabraOcultaEnGuiones = palabraOculta2;
                        txt_PalabraOculta.setText(palabraOculta2);
                        Letra_I.setVisible(false);

                        VerificarSiGane("PRESIDENTE");
                        break;
                    }
                    case "JESUCRISTO": {
                        Coords = 6;
                        Letra = 'I';
                        BtnsLetras();
                        palabraOculta2 = palabraOculta1.toString();
                        PalabraOcultaEnGuiones = palabraOculta2;
                        txt_PalabraOculta.setText(palabraOculta2);
                        Letra_I.setVisible(false);

                        VerificarSiGane("JESUCRISTO");
                        break;
                    }
                    case "LIBELULA": {
                        Coords = 1;
                        Letra = 'I';
                        BtnsLetras();
                        palabraOculta2 = palabraOculta1.toString();
                        PalabraOcultaEnGuiones = palabraOculta2;
                        txt_PalabraOculta.setText(palabraOculta2);
                        Letra_I.setVisible(false);

                        VerificarSiGane("LIBELULA");
                        break;
                    }
                    case "RINOCERONTE": {
                        Coords = 1;
                        Letra = 'I';
                        BtnsLetras();
                        palabraOculta2 = palabraOculta1.toString();
                        PalabraOcultaEnGuiones = palabraOculta2;
                        txt_PalabraOculta.setText(palabraOculta2);
                        Letra_I.setVisible(false);

                        VerificarSiGane("RINOCERONTE");
                        break;
                    }
                    case "ZANAHORIA": {
                        Coords = 7;
                        Letra = 'I';
                        BtnsLetras();
                        palabraOculta2 = palabraOculta1.toString();
                        PalabraOcultaEnGuiones = palabraOculta2;
                        txt_PalabraOculta.setText(palabraOculta2);
                        Letra_I.setVisible(false);

                        VerificarSiGane("ZANAHORIA");
                        break;
                    }
                    default:
                        JOptionPane.showMessageDialog(null, "Error de sintaxis.");
                        break;
                }
            } else {
                if (Vida == 0) {

                } else {
                    if (Vida == 1) {
                        JOptionPane.showMessageDialog(null, "Has perdido.");
                        AhorcadoAnim(++ANMAhorcado);
                        Vida--;
                        String txtVida = Integer.toString(Vida);
                        PoderJugar = false;
                        txt_nVida.setText(txtVida);
                        Letra_I.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error, Perdiste una vida.");
                        AhorcadoAnim(++ANMAhorcado);
                        Vida--;
                        String txtVida = Integer.toString(Vida);
                        txt_nVida.setText(txtVida);

                        Letra_I.setVisible(false);
                    }

                }
            }
        }
    }//GEN-LAST:event_Letra_IActionPerformed

    private void Letra_MActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Letra_MActionPerformed
        if (PoderJugar == false) {
            JOptionPane.showMessageDialog(null, "Error, Inicia una nueva partida.");
        } else {
            if (PalabraOculta.contains("M")) {
                switch (PalabraOculta) {
                    case "": {

                    }
                    default:
                        JOptionPane.showMessageDialog(null, "Error de sintaxis.");
                        break;
                }
            } else {
                if (Vida == 0) {

                } else {
                    if (Vida == 1) {
                        JOptionPane.showMessageDialog(null, "Has perdido.");
                        AhorcadoAnim(++ANMAhorcado);
                        Vida--;
                        String txtVida = Integer.toString(Vida);
                        PoderJugar = false;
                        txt_nVida.setText(txtVida);
                        Letra_M.setVisible(false);

                    } else {
                        JOptionPane.showMessageDialog(null, "Error, Perdiste una vida.");
                        AhorcadoAnim(++ANMAhorcado);
                        Vida--;
                        String txtVida = Integer.toString(Vida);
                        txt_nVida.setText(txtVida);

                        Letra_M.setVisible(false);
                    }

                }
            }
        }
    }//GEN-LAST:event_Letra_MActionPerformed

    private void Letra_PActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Letra_PActionPerformed
        if (PoderJugar == false) {
            JOptionPane.showMessageDialog(null, "Error, Inicia una nueva partida.");
        } else {
            if (PalabraOculta.contains("P")) {
                switch (PalabraOculta) {
                    case "PRESIDENTE": {
                        Coords = 0;
                        Letra = 'P';
                        BtnsLetras();
                        palabraOculta2 = palabraOculta1.toString();
                        PalabraOcultaEnGuiones = palabraOculta2;
                        txt_PalabraOculta.setText(palabraOculta2);
                        Letra_P.setVisible(false);

                        VerificarSiGane("PRESIDENTE");
                        break;
                    }
                    default:
                        JOptionPane.showMessageDialog(null, "Error de sintaxis.");
                        break;
                }
            } else {
                if (Vida == 0) {

                } else {
                    if (Vida == 1) {
                        JOptionPane.showMessageDialog(null, "Has perdido.");
                        AhorcadoAnim(++ANMAhorcado);
                        Vida--;
                        String txtVida = Integer.toString(Vida);
                        PoderJugar = false;
                        txt_nVida.setText(txtVida);
                        Letra_P.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error, Perdiste una vida.");
                        AhorcadoAnim(++ANMAhorcado);
                        Vida--;
                        String txtVida = Integer.toString(Vida);
                        txt_nVida.setText(txtVida);

                        Letra_P.setVisible(false);
                    }

                }
            }
        }
    }//GEN-LAST:event_Letra_PActionPerformed

    private void Letra_UActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Letra_UActionPerformed
        if (PoderJugar == false) {
            JOptionPane.showMessageDialog(null, "Error, Inicia una nueva partida.");
        } else {
            if (PalabraOculta.contains("U")) {
                switch (PalabraOculta) {
                    case "LIBELULA": {
                        Coords = 5;
                        Letra = 'U';
                        BtnsLetras();
                        palabraOculta2 = palabraOculta1.toString();
                        PalabraOcultaEnGuiones = palabraOculta2;
                        txt_PalabraOculta.setText(palabraOculta2);
                        Letra_U.setVisible(false);

                        VerificarSiGane("LIBELULA");
                        break;
                    }
                    case "JESUCRISTO": {
                        Coords = 3;
                        Letra = 'U';
                        BtnsLetras();
                        palabraOculta2 = palabraOculta1.toString();
                        PalabraOcultaEnGuiones = palabraOculta2;
                        txt_PalabraOculta.setText(palabraOculta2);
                        Letra_U.setVisible(false);

                        VerificarSiGane("JESUCRISTO");
                        break;
                    }
                    default:
                        JOptionPane.showMessageDialog(null, "Error de sintaxis.");
                        break;
                }
            } else {
                if (Vida == 0) {

                } else {
                    if (Vida == 1) {
                        JOptionPane.showMessageDialog(null, "Has perdido.");
                        AhorcadoAnim(++ANMAhorcado);
                        Vida--;
                        String txtVida = Integer.toString(Vida);
                        PoderJugar = false;
                        txt_nVida.setText(txtVida);
                        Letra_U.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error, Perdiste una vida.");
                        AhorcadoAnim(++ANMAhorcado);
                        Vida--;
                        String txtVida = Integer.toString(Vida);
                        txt_nVida.setText(txtVida);

                        Letra_U.setVisible(false);
                    }

                }
            }
        }
    }//GEN-LAST:event_Letra_UActionPerformed

    private void Letra_ZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Letra_ZActionPerformed
        if (PoderJugar == false) {
            JOptionPane.showMessageDialog(null, "Error, Inicia una nueva partida.");
        } else {
            if (PalabraOculta.contains("Z")) {
                switch (PalabraOculta) {
                    case "ZANAHORIA": {
                        Coords = 0;
                        Letra = 'Z';
                        BtnsLetras();
                        palabraOculta2 = palabraOculta1.toString();
                        PalabraOcultaEnGuiones = palabraOculta2;
                        txt_PalabraOculta.setText(palabraOculta2);
                        Letra_Z.setVisible(false);

                        VerificarSiGane("ZANAHORIA");
                        break;
                    }
                    default:
                        JOptionPane.showMessageDialog(null, "Error de sintaxis.");
                        break;
                }
            } else {
                if (Vida == 0) {

                } else {
                    if (Vida == 1) {
                        JOptionPane.showMessageDialog(null, "Has perdido.");
                        AhorcadoAnim(++ANMAhorcado);
                        Vida--;
                        String txtVida = Integer.toString(Vida);
                        PoderJugar = false;
                        txt_nVida.setText(txtVida);
                        Letra_Z.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error, Perdiste una vida.");
                        AhorcadoAnim(++ANMAhorcado);
                        Vida--;
                        String txtVida = Integer.toString(Vida);
                        txt_nVida.setText(txtVida);

                        Letra_Z.setVisible(false);
                    }

                }
            }
        }
    }//GEN-LAST:event_Letra_ZActionPerformed

    private void Letra_BActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Letra_BActionPerformed
        if (PoderJugar == false) {
            JOptionPane.showMessageDialog(null, "Error, Inicia una nueva partida.");
        } else {
            if (PalabraOculta.contains("B")) {
                switch (PalabraOculta) {
                    case "LIBELULA": {
                        Coords = 2;
                        Letra = 'B';
                        BtnsLetras();
                        palabraOculta2 = palabraOculta1.toString();
                        PalabraOcultaEnGuiones = palabraOculta2;
                        txt_PalabraOculta.setText(palabraOculta2);
                        Letra_B.setVisible(false);

                        VerificarSiGane("LIBELULA");
                        break;
                    }
                    default:
                        break;
                }
            } else {
                if (Vida == 0) {

                } else {
                    if (Vida == 1) {
                        JOptionPane.showMessageDialog(null, "Has perdido.");
                        AhorcadoAnim(++ANMAhorcado);
                        Vida--;
                        String txtVida = Integer.toString(Vida);
                        PoderJugar = false;
                        txt_nVida.setText(txtVida);
                        Letra_B.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error, Perdiste una vida.");
                        AhorcadoAnim(++ANMAhorcado);
                        Vida--;
                        String txtVida = Integer.toString(Vida);
                        txt_nVida.setText(txtVida);
                        Letra_B.setVisible(false);
                    }

                }
            }
        }
    }//GEN-LAST:event_Letra_BActionPerformed

    private void Letra_JActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Letra_JActionPerformed
        if (PoderJugar == false) {
            JOptionPane.showMessageDialog(null, "Error, Inicia una nueva partida.");
        } else {
            if (PalabraOculta.contains("J")) {
                switch (PalabraOculta) {
                    case "JESUCRISTO": {
                        Coords = 0;
                        Letra = 'J';
                        BtnsLetras();
                        palabraOculta2 = palabraOculta1.toString();
                        PalabraOcultaEnGuiones = palabraOculta2;
                        txt_PalabraOculta.setText(palabraOculta2);
                        Letra_J.setVisible(false);

                        VerificarSiGane("JESUCRISTO");
                        break;
                    }
                    default:
                        JOptionPane.showMessageDialog(null, "Error de sintaxis.");
                        break;
                }
            } else {
                if (Vida == 0) {

                } else {
                    if (Vida == 1) {
                        JOptionPane.showMessageDialog(null, "Has perdido.");
                        AhorcadoAnim(++ANMAhorcado);
                        Vida--;
                        String txtVida = Integer.toString(Vida);
                        PoderJugar = false;
                        txt_nVida.setText(txtVida);
                        Letra_J.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error, Perdiste una vida.");
                        AhorcadoAnim(++ANMAhorcado);
                        Vida--;
                        String txtVida = Integer.toString(Vida);
                        txt_nVida.setText(txtVida);

                        Letra_J.setVisible(false);
                    }

                }
            }
        }
    }//GEN-LAST:event_Letra_JActionPerformed

    private void Letra_NActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Letra_NActionPerformed
        if (PoderJugar == false) {
            JOptionPane.showMessageDialog(null, "Error, Inicia una nueva partida.");
        } else {
            if (PalabraOculta.contains("N")) {
                switch (PalabraOculta) {
                    case "ZANAHORIA": {
                        Coords = 2;
                        Letra = 'N';
                        BtnsLetras();
                        palabraOculta2 = palabraOculta1.toString();
                        PalabraOcultaEnGuiones = palabraOculta2;
                        txt_PalabraOculta.setText(palabraOculta2);
                        Letra_N.setVisible(false);

                        VerificarSiGane("ZANAHORIA");
                        break;
                    }
                    case "RINOCERONTE": {
                        Coords = 2;
                        Letra = 'N';
                        BtnsLetras();
                        palabraOculta2 = palabraOculta1.toString();
                        PalabraOcultaEnGuiones = palabraOculta2;
                        txt_PalabraOculta.setText(palabraOculta2);

                        Coords = 8;
                        Letra = 'N';
                        BtnsLetras();
                        palabraOculta2 = palabraOculta1.toString();
                        PalabraOcultaEnGuiones = palabraOculta2;
                        txt_PalabraOculta.setText(palabraOculta2);
                        Letra_N.setVisible(false);

                        VerificarSiGane("RINOCERONTE");
                        break;
                    }
                    case "PRESIDENTE": {
                        Coords = 7;
                        Letra = 'N';
                        BtnsLetras();
                        palabraOculta2 = palabraOculta1.toString();
                        PalabraOcultaEnGuiones = palabraOculta2;
                        txt_PalabraOculta.setText(palabraOculta2);
                        Letra_N.setVisible(false);

                        VerificarSiGane("PRESIDENTE");
                        break;
                    }
                    default:
                        JOptionPane.showMessageDialog(null, "Error de sintaxis.");
                        break;
                }
            } else {
                if (Vida == 0) {

                } else {
                    if (Vida == 1) {
                        JOptionPane.showMessageDialog(null, "Has perdido.");
                        AhorcadoAnim(++ANMAhorcado);
                        Vida--;
                        String txtVida = Integer.toString(Vida);
                        PoderJugar = false;
                        txt_nVida.setText(txtVida);
                        Letra_N.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error, Perdiste una vida.");
                        AhorcadoAnim(++ANMAhorcado);
                        Vida--;
                        String txtVida = Integer.toString(Vida);
                        txt_nVida.setText(txtVida);
                        Letra_N.setVisible(false);
                    }

                }
            }
        }
    }//GEN-LAST:event_Letra_NActionPerformed

    private void Letra_RActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Letra_RActionPerformed
        if (PoderJugar == false) {
            JOptionPane.showMessageDialog(null, "Error, Inicia una nueva partida.");
        } else {
            if (PalabraOculta.contains("R")) {
                switch (PalabraOculta) {
                    case "ZANAHORIA": {
                        Coords = 6;
                        Letra = 'R';
                        BtnsLetras();
                        palabraOculta2 = palabraOculta1.toString();
                        PalabraOcultaEnGuiones = palabraOculta2;
                        txt_PalabraOculta.setText(palabraOculta2);
                        Letra_R.setVisible(false);

                        VerificarSiGane("ZANAHORIA");
                        break;
                    }
                    case "PRESIDENTE": {
                        Coords = 1;
                        Letra = 'R';
                        BtnsLetras();
                        palabraOculta2 = palabraOculta1.toString();
                        PalabraOcultaEnGuiones = palabraOculta2;
                        txt_PalabraOculta.setText(palabraOculta2);
                        Letra_R.setVisible(false);

                        VerificarSiGane("PRESIDENTE");
                        break;
                    }
                    case "JESUCRISTO": {
                        Coords = 5;
                        Letra = 'R';
                        BtnsLetras();
                        palabraOculta2 = palabraOculta1.toString();
                        PalabraOcultaEnGuiones = palabraOculta2;
                        txt_PalabraOculta.setText(palabraOculta2);
                        Letra_R.setVisible(false);

                        VerificarSiGane("JESUCRISTO");
                        break;
                    }
                    case "RINOCERONTE": {
                        Coords = 0;
                        Letra = 'R';
                        BtnsLetras();
                        palabraOculta2 = palabraOculta1.toString();
                        PalabraOcultaEnGuiones = palabraOculta2;
                        txt_PalabraOculta.setText(palabraOculta2);
                        Letra_R.setVisible(false);

                        Coords = 6;
                        Letra = 'R';
                        BtnsLetras();
                        palabraOculta2 = palabraOculta1.toString();
                        PalabraOcultaEnGuiones = palabraOculta2;
                        txt_PalabraOculta.setText(palabraOculta2);
                        Letra_R.setVisible(false);

                        VerificarSiGane("RINOCERONTE");
                        break;
                    }

                    default:
                        break;
                }
            } else {
                if (Vida == 0) {

                } else {
                    if (Vida == 1) {
                        JOptionPane.showMessageDialog(null, "Has perdido.");
                        AhorcadoAnim(++ANMAhorcado);
                        Vida--;
                        String txtVida = Integer.toString(Vida);
                        PoderJugar = false;
                        txt_nVida.setText(txtVida);
                        Letra_R.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error, Perdiste una vida.");
                        AhorcadoAnim(++ANMAhorcado);
                        Vida--;
                        String txtVida = Integer.toString(Vida);
                        txt_nVida.setText(txtVida);

                        Letra_R.setVisible(false);
                    }

                }
            }
        }
    }//GEN-LAST:event_Letra_RActionPerformed

    private void Letra_VActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Letra_VActionPerformed
        if (PoderJugar == false) {
            JOptionPane.showMessageDialog(null, "Error, Inicia una nueva partida.");
        } else {
            if (PalabraOculta.contains("V")) {
                switch (PalabraOculta) {
                    case "": {

                    }
                    default:
                        JOptionPane.showMessageDialog(null, "Error de sintaxis.");
                        break;
                }
            } else {
                if (Vida == 0) {

                } else {
                    if (Vida == 1) {
                        JOptionPane.showMessageDialog(null, "Has perdido.");
                        AhorcadoAnim(++ANMAhorcado);
                        Vida--;
                        String txtVida = Integer.toString(Vida);
                        PoderJugar = false;
                        txt_nVida.setText(txtVida);
                        Letra_V.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error, Perdiste una vida.");
                        AhorcadoAnim(++ANMAhorcado);
                        Vida--;
                        String txtVida = Integer.toString(Vida);
                        txt_nVida.setText(txtVida);

                        Letra_V.setVisible(false);
                    }

                }
            }
        }
    }//GEN-LAST:event_Letra_VActionPerformed

    private void Letra_WActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Letra_WActionPerformed
        if (PoderJugar == false) {
            JOptionPane.showMessageDialog(null, "Error, Inicia una nueva partida.");
        } else {
            if (PalabraOculta.contains("W")) {
                switch (PalabraOculta) {
                    case "": {

                    }
                    default:
                        JOptionPane.showMessageDialog(null, "Error de sintaxis.");
                        break;
                }
            } else {
                if (Vida == 0) {

                } else {
                    if (Vida == 1) {
                        JOptionPane.showMessageDialog(null, "Has perdido.");
                        AhorcadoAnim(++ANMAhorcado);
                        Vida--;
                        String txtVida = Integer.toString(Vida);
                        PoderJugar = false;
                        txt_nVida.setText(txtVida);
                        Letra_W.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error, Perdiste una vida.");
                        AhorcadoAnim(++ANMAhorcado);
                        Vida--;
                        String txtVida = Integer.toString(Vida);
                        txt_nVida.setText(txtVida);

                        Letra_W.setVisible(false);
                    }

                }
            }
        }
    }//GEN-LAST:event_Letra_WActionPerformed

    private void Letra_SActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Letra_SActionPerformed
        if (PoderJugar == false) {
            JOptionPane.showMessageDialog(null, "Error, Inicia una nueva partida.");
        } else {
            if (PalabraOculta.contains("S")) {
                switch (PalabraOculta) {
                    case "JESUCRISTO": {
                        Coords = 2;
                        Letra = 'S';
                        BtnsLetras();
                        palabraOculta2 = palabraOculta1.toString();
                        PalabraOcultaEnGuiones = palabraOculta2;
                        txt_PalabraOculta.setText(palabraOculta2);

                        Coords = 7;
                        Letra = 'S';
                        BtnsLetras();
                        palabraOculta2 = palabraOculta1.toString();
                        PalabraOcultaEnGuiones = palabraOculta2;
                        txt_PalabraOculta.setText(palabraOculta2);
                        Letra_S.setVisible(false);

                        VerificarSiGane("JESUCRISTO");
                        break;
                    }
                    case "PRESIDENTE": {
                        Coords = 3;
                        Letra = 'S';
                        BtnsLetras();
                        palabraOculta2 = palabraOculta1.toString();
                        PalabraOcultaEnGuiones = palabraOculta2;
                        txt_PalabraOculta.setText(palabraOculta2);
                        Letra_S.setVisible(false);

                        VerificarSiGane("PRESIDENTE");
                        break;
                    }

                    default:
                        JOptionPane.showMessageDialog(null, "Error de sintaxis.");
                        break;
                }
            } else {
                if (Vida == 0) {

                } else {
                    if (Vida == 1) {
                        JOptionPane.showMessageDialog(null, "Has perdido.");
                        AhorcadoAnim(++ANMAhorcado);
                        Vida--;
                        String txtVida = Integer.toString(Vida);
                        PoderJugar = false;
                        txt_nVida.setText(txtVida);
                        Letra_S.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error, Perdiste una vida.");
                        AhorcadoAnim(++ANMAhorcado);
                        Vida--;
                        String txtVida = Integer.toString(Vida);
                        txt_nVida.setText(txtVida);

                        Letra_S.setVisible(false);
                    }

                }
            }
        }
    }//GEN-LAST:event_Letra_SActionPerformed

    private void Letra_KActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Letra_KActionPerformed
        if (PoderJugar == false) {
            JOptionPane.showMessageDialog(null, "Error, Inicia una nueva partida.");
        } else {
            if (PalabraOculta.contains("K")) {
                switch (PalabraOculta) {
                    case "": {

                    }
                    default:
                        JOptionPane.showMessageDialog(null, "Error de sintaxis.");
                        break;
                }
            } else {
                if (Vida == 0) {

                } else {
                    if (Vida == 1) {
                        JOptionPane.showMessageDialog(null, "Has perdido.");
                        AhorcadoAnim(++ANMAhorcado);
                        Vida--;
                        String txtVida = Integer.toString(Vida);
                        PoderJugar = false;
                        txt_nVida.setText(txtVida);
                        Letra_K.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error, Perdiste una vida.");
                        AhorcadoAnim(++ANMAhorcado);
                        Vida--;
                        String txtVida = Integer.toString(Vida);
                        txt_nVida.setText(txtVida);

                        Letra_K.setVisible(false);
                    }

                }
            }
        }
    }//GEN-LAST:event_Letra_KActionPerformed

    private void Letra_GActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Letra_GActionPerformed
        if (PoderJugar == false) {
            JOptionPane.showMessageDialog(null, "Error, Inicia una nueva partida.");
        } else {
            if (PalabraOculta.contains("G")) {
                switch (PalabraOculta) {
                    case "": {

                    }
                    default:
                        JOptionPane.showMessageDialog(null, "Error de sintaxis.");
                        break;
                }
            } else {
                if (Vida == 0) {

                } else {
                    if (Vida == 1) {
                        JOptionPane.showMessageDialog(null, "Has perdido.");
                        AhorcadoAnim(++ANMAhorcado);
                        Vida--;
                        String txtVida = Integer.toString(Vida);
                        PoderJugar = false;
                        txt_nVida.setText(txtVida);
                        Letra_G.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error, Perdiste una vida.");
                        AhorcadoAnim(++ANMAhorcado);
                        Vida--;
                        String txtVida = Integer.toString(Vida);
                        txt_nVida.setText(txtVida);

                        Letra_G.setVisible(false);
                    }

                }
            }
        }
    }//GEN-LAST:event_Letra_GActionPerformed

    private void Letra_CActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Letra_CActionPerformed
        if (PoderJugar == false) {
            JOptionPane.showMessageDialog(null, "Error, Inicia una nueva partida.");
        } else {
            if (PalabraOculta.contains("C")) {
                switch (PalabraOculta) {
                    case "JESUCRISTO": {
                        Coords = 4;
                        Letra = 'C';
                        BtnsLetras();
                        palabraOculta2 = palabraOculta1.toString();
                        PalabraOcultaEnGuiones = palabraOculta2;
                        txt_PalabraOculta.setText(palabraOculta2);
                        Letra_C.setVisible(false);

                        VerificarSiGane("JESUCRISTO");
                        break;
                    }
                    case "RINOCERONTE": {
                        Coords = 4;
                        Letra = 'C';
                        BtnsLetras();
                        palabraOculta2 = palabraOculta1.toString();
                        PalabraOcultaEnGuiones = palabraOculta2;
                        txt_PalabraOculta.setText(palabraOculta2);
                        Letra_C.setVisible(false);

                        VerificarSiGane("RINOCERONTE");
                        break;
                    }
                    default:
                        JOptionPane.showMessageDialog(null, "Error de sintaxis.");
                        break;
                }
            } else {
                if (Vida == 0) {

                } else {
                    if (Vida == 1) {
                        JOptionPane.showMessageDialog(null, "Has perdido.");
                        AhorcadoAnim(++ANMAhorcado);
                        Vida--;
                        String txtVida = Integer.toString(Vida);
                        PoderJugar = false;
                        txt_nVida.setText(txtVida);
                        Letra_C.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error, Perdiste una vida.");
                        AhorcadoAnim(++ANMAhorcado);
                        Vida--;
                        String txtVida = Integer.toString(Vida);
                        txt_nVida.setText(txtVida);
                        Letra_C.setVisible(false);
                    }

                }
            }
        }
    }//GEN-LAST:event_Letra_CActionPerformed

    private void Letra_DActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Letra_DActionPerformed
        if (PoderJugar == false) {
            JOptionPane.showMessageDialog(null, "Error, Inicia una nueva partida.");
        } else {
            if (PalabraOculta.contains("D")) {
                switch (PalabraOculta) {
                    case "PRESIDENTE": {
                        Coords = 5;
                        Letra = 'D';
                        BtnsLetras();
                        palabraOculta2 = palabraOculta1.toString();
                        PalabraOcultaEnGuiones = palabraOculta2;
                        txt_PalabraOculta.setText(palabraOculta2);
                        Letra_D.setVisible(false);

                        VerificarSiGane("PRESIDENTE");
                        break;
                    }
                    default:
                        JOptionPane.showMessageDialog(null, "Error de sintaxis.");
                        break;
                }
            } else {
                if (Vida == 0) {

                } else {
                    if (Vida == 1) {
                        JOptionPane.showMessageDialog(null, "Has perdido.");
                        AhorcadoAnim(++ANMAhorcado);
                        Vida--;
                        String txtVida = Integer.toString(Vida);
                        PoderJugar = false;
                        txt_nVida.setText(txtVida);
                        Letra_D.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error, Perdiste una vida.");
                        AhorcadoAnim(++ANMAhorcado);
                        Vida--;
                        String txtVida = Integer.toString(Vida);
                        txt_nVida.setText(txtVida);
                        Letra_D.setVisible(false);
                    }

                }
            }
        }
    }//GEN-LAST:event_Letra_DActionPerformed

    private void Letra_HActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Letra_HActionPerformed
        if (PoderJugar == false) {
            JOptionPane.showMessageDialog(null, "Error, Inicia una nueva partida.");
        } else {
            if (PalabraOculta.contains("H")) {
                switch (PalabraOculta) {
                    case "ZANAHORIA": {
                        Coords = 4;
                        Letra = 'H';
                        BtnsLetras();
                        palabraOculta2 = palabraOculta1.toString();
                        PalabraOcultaEnGuiones = palabraOculta2;
                        txt_PalabraOculta.setText(palabraOculta2);
                        Letra_H.setVisible(false);

                        VerificarSiGane("ZANAHORIA");
                        break;
                    }
                    default:
                        JOptionPane.showMessageDialog(null, "Error de sintaxis.");
                        break;
                }
            } else {
                if (Vida == 0) {

                } else {
                    if (Vida == 1) {
                        JOptionPane.showMessageDialog(null, "Has perdido.");
                        AhorcadoAnim(++ANMAhorcado);
                        Vida--;
                        String txtVida = Integer.toString(Vida);
                        PoderJugar = false;
                        txt_nVida.setText(txtVida);
                        Letra_H.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error, Perdiste una vida.");
                        AhorcadoAnim(++ANMAhorcado);
                        Vida--;
                        String txtVida = Integer.toString(Vida);
                        txt_nVida.setText(txtVida);

                        Letra_H.setVisible(false);
                    }

                }
            }
        }
    }//GEN-LAST:event_Letra_HActionPerformed

    private void Letra_LActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Letra_LActionPerformed
        if (PoderJugar == false) {
            JOptionPane.showMessageDialog(null, "Error, Inicia una nueva partida.");
        } else {
            if (PalabraOculta.contains("L")) {
                switch (PalabraOculta) {
                    case "LIBELULA": {
                        Coords = 0;
                        Letra = 'L';
                        BtnsLetras();
                        palabraOculta2 = palabraOculta1.toString();
                        PalabraOcultaEnGuiones = palabraOculta2;
                        txt_PalabraOculta.setText(palabraOculta2);

                        Coords = 4;
                        Letra = 'L';
                        BtnsLetras();
                        palabraOculta2 = palabraOculta1.toString();
                        PalabraOcultaEnGuiones = palabraOculta2;
                        txt_PalabraOculta.setText(palabraOculta2);

                        Coords = 6;
                        Letra = 'L';
                        BtnsLetras();
                        palabraOculta2 = palabraOculta1.toString();
                        PalabraOcultaEnGuiones = palabraOculta2;
                        txt_PalabraOculta.setText(palabraOculta2);
                        Letra_L.setVisible(false);

                        VerificarSiGane("LIBELULA");
                        break;
                    }
                    default:
                        JOptionPane.showMessageDialog(null, "Error de sintaxis.");
                        break;
                }
            } else {
                if (Vida == 0) {

                } else {
                    if (Vida == 1) {
                        JOptionPane.showMessageDialog(null, "Has perdido.");
                        AhorcadoAnim(++ANMAhorcado);
                        Vida--;
                        String txtVida = Integer.toString(Vida);
                        PoderJugar = false;
                        txt_nVida.setText(txtVida);
                        Letra_L.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error, Perdiste una vida.");
                        AhorcadoAnim(++ANMAhorcado);
                        Vida--;
                        String txtVida = Integer.toString(Vida);
                        txt_nVida.setText(txtVida);

                        Letra_L.setVisible(false);
                    }

                }
            }
        }
    }//GEN-LAST:event_Letra_LActionPerformed

    private void Letra_OActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Letra_OActionPerformed
        if (PoderJugar == false) {
            JOptionPane.showMessageDialog(null, "Error, Inicia una nueva partida.");
        } else {
            if (PalabraOculta.contains("O")) {
                switch (PalabraOculta) {
                    case "JESUCRISTO": {
                        Coords = 9;
                        Letra = 'O';
                        BtnsLetras();
                        palabraOculta2 = palabraOculta1.toString();
                        PalabraOcultaEnGuiones = palabraOculta2;
                        txt_PalabraOculta.setText(palabraOculta2);
                        Letra_O.setVisible(false);

                        VerificarSiGane("JESUCRISTO");
                        break;
                    }
                    case "ZANAHORIA": {
                        Coords = 5;
                        Letra = 'O';
                        BtnsLetras();
                        palabraOculta2 = palabraOculta1.toString();
                        PalabraOcultaEnGuiones = palabraOculta2;
                        txt_PalabraOculta.setText(palabraOculta2);
                        Letra_O.setVisible(false);

                        VerificarSiGane("ZANAHORIA");
                        break;
                    }
                    case "RINOCERONTE": {
                        Coords = 3;
                        Letra = 'O';
                        BtnsLetras();
                        palabraOculta2 = palabraOculta1.toString();
                        PalabraOcultaEnGuiones = palabraOculta2;
                        txt_PalabraOculta.setText(palabraOculta2);

                        Coords = 7;
                        Letra = 'O';
                        BtnsLetras();
                        palabraOculta2 = palabraOculta1.toString();
                        PalabraOcultaEnGuiones = palabraOculta2;
                        txt_PalabraOculta.setText(palabraOculta2);
                        Letra_O.setVisible(false);

                        VerificarSiGane("RINOCERONTE");
                        break;
                    }
                    default:
                        JOptionPane.showMessageDialog(null, "Error de sintaxis.");
                        break;
                }
            } else {
                if (Vida == 0) {

                } else {
                    if (Vida == 1) {
                        JOptionPane.showMessageDialog(null, "Has perdido.");
                        AhorcadoAnim(++ANMAhorcado);
                        Vida--;
                        String txtVida = Integer.toString(Vida);
                        PoderJugar = false;
                        txt_nVida.setText(txtVida);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error, Perdiste una vida.");
                        AhorcadoAnim(++ANMAhorcado);
                        Vida--;
                        String txtVida = Integer.toString(Vida);
                        txt_nVida.setText(txtVida);
                        Letra_O.setVisible(false);
                    }

                }
            }
        }
    }//GEN-LAST:event_Letra_OActionPerformed

    private void Letra_TActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Letra_TActionPerformed
        if (PoderJugar == false) {
            JOptionPane.showMessageDialog(null, "Error, Inicia una nueva partida.");
        } else {
            if (PalabraOculta.contains("T")) {
                switch (PalabraOculta) {
                    case "RINOCERONTE": {
                        Coords = 9;
                        Letra = 'T';
                        BtnsLetras();
                        palabraOculta2 = palabraOculta1.toString();
                        PalabraOcultaEnGuiones = palabraOculta2;
                        txt_PalabraOculta.setText(palabraOculta2);
                        Letra_T.setVisible(false);

                        VerificarSiGane("RINOCERONTE");
                        break;
                    }
                    case "PRESIDENTE": {
                        Coords = 8;
                        Letra = 'T';
                        BtnsLetras();
                        palabraOculta2 = palabraOculta1.toString();
                        PalabraOcultaEnGuiones = palabraOculta2;
                        txt_PalabraOculta.setText(palabraOculta2);
                        Letra_T.setVisible(false);

                        VerificarSiGane("PRESIDENTE");
                        break;
                    }
                    case "JESUCRISTO": {
                        Coords = 8;
                        Letra = 'T';
                        BtnsLetras();
                        palabraOculta2 = palabraOculta1.toString();
                        PalabraOcultaEnGuiones = palabraOculta2;
                        txt_PalabraOculta.setText(palabraOculta2);
                        Letra_T.setVisible(false);

                        VerificarSiGane("PRESIDENTE");
                        break;
                    }

                    default:
                        JOptionPane.showMessageDialog(null, "Error de sintaxis.");
                        break;
                }
            } else {
                if (Vida == 0) {

                } else {
                    if (Vida == 1) {
                        JOptionPane.showMessageDialog(null, "Has perdido.");
                        AhorcadoAnim(++ANMAhorcado);
                        Vida--;
                        String txtVida = Integer.toString(Vida);
                        PoderJugar = false;
                        txt_nVida.setText(txtVida);
                        Letra_T.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error, Perdiste una vida.");
                        AhorcadoAnim(++ANMAhorcado);
                        Vida--;
                        String txtVida = Integer.toString(Vida);
                        txt_nVida.setText(txtVida);

                        Letra_T.setVisible(false);
                    }

                }
            }
        }
    }//GEN-LAST:event_Letra_TActionPerformed

    private void Letra_XActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Letra_XActionPerformed
        if (PoderJugar == false) {
            JOptionPane.showMessageDialog(null, "Error, Inicia una nueva partida.");
        } else {
            if (PalabraOculta.contains("X")) {
                switch (PalabraOculta) {
                    case "": {

                    }
                    default:
                        JOptionPane.showMessageDialog(null, "Error de sintaxis.");
                        break;
                }
            } else {
                if (Vida == 0) {

                } else {
                    if (Vida == 1) {
                        JOptionPane.showMessageDialog(null, "Has perdido.");
                        AhorcadoAnim(++ANMAhorcado);
                        Vida--;
                        String txtVida = Integer.toString(Vida);
                        PoderJugar = false;
                        txt_nVida.setText(txtVida);
                        Letra_X.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error, Perdiste una vida.");
                        AhorcadoAnim(++ANMAhorcado);
                        Vida--;
                        String txtVida = Integer.toString(Vida);
                        txt_nVida.setText(txtVida);

                        Letra_X.setVisible(false);
                    }

                }
            }
        }
    }//GEN-LAST:event_Letra_XActionPerformed

    private void Letra_QActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Letra_QActionPerformed
        if (PoderJugar == false) {
            JOptionPane.showMessageDialog(null, "Error, Inicia una nueva partida.");
        } else {
            if (PalabraOculta.contains("Q")) {
                switch (PalabraOculta) {
                    case "": {

                    }
                    default:
                        JOptionPane.showMessageDialog(null, "Error de sintaxis.");
                        break;
                }
            } else {
                if (Vida == 0) {

                } else {
                    if (Vida == 1) {
                        JOptionPane.showMessageDialog(null, "Has perdido.");
                        AhorcadoAnim(++ANMAhorcado);
                        Vida--;
                        String txtVida = Integer.toString(Vida);
                        PoderJugar = false;
                        txt_nVida.setText(txtVida);
                        Letra_Q.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error, Perdiste una vida.");
                        AhorcadoAnim(++ANMAhorcado);
                        Vida--;
                        String txtVida = Integer.toString(Vida);
                        txt_nVida.setText(txtVida);
                        Letra_Q.setVisible(false);
                    }

                }
            }
        }
    }//GEN-LAST:event_Letra_QActionPerformed

    private void Letra_YActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Letra_YActionPerformed
        if (PoderJugar == false) {
            JOptionPane.showMessageDialog(null, "Error, Inicia una nueva partida.");
        } else {
            if (PalabraOculta.contains("Y")) {
                switch (PalabraOculta) {
                    case "": {

                    }
                    default:
                        JOptionPane.showMessageDialog(null, "Error de sintaxis.");
                        break;
                }
            } else {
                if (Vida == 0) {

                } else {
                    if (Vida == 1) {
                        JOptionPane.showMessageDialog(null, "Has perdido.");
                        AhorcadoAnim(++ANMAhorcado);
                        Vida--;
                        String txtVida = Integer.toString(Vida);
                        PoderJugar = false;
                        txt_nVida.setText(txtVida);
                        Letra_Y.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error, Perdiste una vida.");
                        AhorcadoAnim(++ANMAhorcado);
                        Vida--;
                        String txtVida = Integer.toString(Vida);
                        txt_nVida.setText(txtVida);
                        Letra_Y.setVisible(false);
                    }

                }
            }
        }
    }//GEN-LAST:event_Letra_YActionPerformed

    private void pnl_BtnGameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_BtnGameMouseClicked
        jTabbedPane1.setSelectedIndex(5);

        pnl_Snake.add(SnakePanel);
        SnakePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        SnakePanel.requestFocus(true);
        SnakePanel.setBounds(70, 15, 540, 540);
        SnakePanel.guardarValor(SnakePanel.pts, "pts.txt");
        txtSnakePts.setText("Pts: "+SnakePanel.leerValor("pts.txt"));

        switch (lang) {
            case "es":
                txtTop.setText("Serpiente");
                break;
            case "en":
                txtTop.setText("Snake");
                break;
            case "pg":
                txtTop.setText("Cobra");
                break;
            default:
                break;
        }
    }//GEN-LAST:event_pnl_BtnGameMouseClicked

    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
        jfr_Copyrgt.setVisible(true);
        jfr_Copyrgt.setResizable(false);
        jfr_Copyrgt.setLocationRelativeTo(null);
    }//GEN-LAST:event_jLabel18MouseClicked

    private void pnl_CopyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_CopyMouseClicked
    }//GEN-LAST:event_pnl_CopyMouseClicked

    private void pnl_BtnNavMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_BtnNavMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_pnl_BtnNavMousePressed

    private void Letra_FActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Letra_FActionPerformed
        if (PoderJugar == false) {
            JOptionPane.showMessageDialog(null, "Error, Inicia una nueva partida.");
        } else {
            if (PalabraOculta.contains("F")) {
                switch (PalabraOculta) {
                    case "PRESIDENTE": {

                    }
                    default:
                        JOptionPane.showMessageDialog(null, "Error de sintaxis.");
                        break;
                }
            } else {
                if (Vida == 0) {

                } else {
                    if (Vida == 1) {
                        JOptionPane.showMessageDialog(null, "Has perdido.");
                        AhorcadoAnim(++ANMAhorcado);
                        Vida--;
                        String txtVida = Integer.toString(Vida);
                        PoderJugar = false;
                        txt_nVida.setText(txtVida);
                        Letra_F.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error, Perdiste una vida.");
                        AhorcadoAnim(++ANMAhorcado);
                        Vida--;
                        String txtVida = Integer.toString(Vida);
                        txt_nVida.setText(txtVida);

                        Letra_F.setVisible(false);
                    }

                }
            }
        }
    }//GEN-LAST:event_Letra_FActionPerformed

    private void pnl_SnakeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pnl_SnakeKeyPressed
    }//GEN-LAST:event_pnl_SnakeKeyPressed

    private void btnSnakePlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSnakePlayActionPerformed
        txtSnakeHighScore.setText(GamePanel.leerValor("highScore.txt") + "");
        value = sldrDificultad.getValue();

        if (blnSnakePlay) {

            SnakePanel.requestFocus(true);
            SnakePanel.running = true;
            SnakePanel.timer.start();

            blnSnakePlay = false;
        } else {

        }
    }//GEN-LAST:event_btnSnakePlayActionPerformed

    private void btnSnakeStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSnakeStopActionPerformed
        try {
            if (blnSnakeStop) {
                SnakePanel.running = false;
                SnakePanel.timer.stop();

                blnSnakeStop = false;
            } else {
                SnakePanel.requestFocus(true);
                SnakePanel.running = true;
                SnakePanel.timer.start();

                blnSnakeStop = true;
            }
        } catch (Exception e) {
            System.out.println("e = " + e);
        }
    }//GEN-LAST:event_btnSnakeStopActionPerformed

    private void btnSnakeRestartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSnakeRestartActionPerformed
        btnSnakeRestartFunc();
        btnSnakeRestartFunc();
    }//GEN-LAST:event_btnSnakeRestartActionPerformed

    private void txtSnakeHighScoreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSnakeHighScoreMouseClicked
        txtSnakeHighScore.setText(SnakePanel.leerValor("highScore.txt") + "");
    }//GEN-LAST:event_txtSnakeHighScoreMouseClicked

    private void btnNegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNegActionPerformed
        try {
            if (turno == 0 && !(valsTxt.getText().equals(""))) {
                val1 = Float.parseFloat(valsTxt.getText()) * -1;
                valsTxt.setText("" + val1);
            } else {
                val2 = Float.parseFloat(valsTxt.getText()) * -1;
                valsTxt.setText("" + val2);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

    }//GEN-LAST:event_btnNegActionPerformed

    private void sldrDificultadStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sldrDificultadStateChanged

    }//GEN-LAST:event_sldrDificultadStateChanged

    private void pnl_DAKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pnl_DAKeyPressed
    }//GEN-LAST:event_pnl_DAKeyPressed

    private void jTabbedPane1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTabbedPane1KeyPressed
        keyboardCalc(evt);

        keySequence.append(evt.getKeyChar());
        // Si la secuencia es "ad", imprimir en consola
        if (keySequence.toString().equals("mel")) {
            JOptionPane.showMessageDialog(null, "Mel, te amo<3");
            // Limpiar el StringBuilder después de imprimir en la consola
            keySequence.setLength(0);
        }
    }//GEN-LAST:event_jTabbedPane1KeyPressed

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        settings_sn.setVisible(true);
        settings_sn.setLocationRelativeTo(null);
    }//GEN-LAST:event_jLabel8MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton Letra_A;
    protected javax.swing.JButton Letra_B;
    protected javax.swing.JButton Letra_C;
    protected javax.swing.JButton Letra_D;
    protected javax.swing.JButton Letra_E;
    protected javax.swing.JButton Letra_F;
    protected javax.swing.JButton Letra_G;
    protected javax.swing.JButton Letra_H;
    protected javax.swing.JButton Letra_I;
    protected javax.swing.JButton Letra_J;
    protected javax.swing.JButton Letra_K;
    protected javax.swing.JButton Letra_L;
    protected javax.swing.JButton Letra_M;
    protected javax.swing.JButton Letra_N;
    protected javax.swing.JButton Letra_O;
    protected javax.swing.JButton Letra_P;
    protected javax.swing.JButton Letra_Q;
    protected javax.swing.JButton Letra_R;
    protected javax.swing.JButton Letra_S;
    protected javax.swing.JButton Letra_T;
    protected javax.swing.JButton Letra_U;
    protected javax.swing.JButton Letra_V;
    protected javax.swing.JButton Letra_W;
    protected javax.swing.JButton Letra_X;
    protected javax.swing.JButton Letra_Y;
    protected javax.swing.JButton Letra_Z;
    public static javax.swing.JButton btn0;
    public static javax.swing.JButton btn1;
    public static javax.swing.JButton btn2;
    public static javax.swing.JButton btn3;
    public static javax.swing.JButton btn4;
    public static javax.swing.JButton btn5;
    public static javax.swing.JButton btn6;
    public static javax.swing.JButton btn7;
    public static javax.swing.JButton btn8;
    public static javax.swing.JButton btn9;
    private javax.swing.JButton btnBing;
    private javax.swing.JButton btnBuscarNav;
    public static javax.swing.JButton btnC;
    public static javax.swing.JButton btnClearNotes;
    private javax.swing.JButton btnDDGo;
    public static javax.swing.JButton btnDiv;
    public static javax.swing.JButton btnDot;
    public static javax.swing.JButton btnEqual;
    public static javax.swing.JButton btnExp;
    private javax.swing.JButton btnFacebook;
    public static javax.swing.JButton btnFor;
    private javax.swing.JButton btnGitHub;
    private javax.swing.JButton btnGmail;
    private javax.swing.JButton btnGoogle;
    public static javax.swing.JButton btnNeg;
    public static javax.swing.JButton btnOpenNotes;
    public static javax.swing.JButton btnPI;
    private javax.swing.JButton btnPintrst;
    private javax.swing.JButton btnPrz1;
    private javax.swing.JButton btnPrz2;
    private javax.swing.JButton btnPrz3;
    private javax.swing.JButton btnPrz4;
    private javax.swing.JButton btnReddit;
    public static javax.swing.JButton btnRest;
    public static javax.swing.JButton btnSaveDocNotes;
    public static javax.swing.JButton btnSaveNotes;
    private javax.swing.JButton btnSnakePlay;
    public static javax.swing.JButton btnSnakeRestart;
    private javax.swing.JButton btnSnakeStop;
    public static javax.swing.JButton btnSqr2;
    public static javax.swing.JButton btnSqr3;
    public static javax.swing.JButton btnSqrPrz;
    public static javax.swing.JButton btnSum;
    private javax.swing.JButton btnTwitter;
    private javax.swing.JButton btnWhatsApp;
    private javax.swing.JButton btnYT1;
    private javax.swing.JButton btnYahoo;
    private javax.swing.JButton btn_Reiniciar;
    public static javax.swing.JComboBox<String> cmbNotes;
    private javax.swing.JComboBox<String> combo_Nav;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboStyle;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JComboBox<String> jcomboFont;
    private javax.swing.JPanel jp_TopMov;
    private javax.swing.JLabel lblAhorcado;
    private javax.swing.JPanel pnl_Ahorcado;
    private javax.swing.JPanel pnl_BtnAhorcado;
    private javax.swing.JPanel pnl_BtnCalc;
    private javax.swing.JPanel pnl_BtnDA;
    private javax.swing.JPanel pnl_BtnGame;
    private javax.swing.JPanel pnl_BtnNav;
    private javax.swing.JPanel pnl_BtnNotes;
    private javax.swing.JPanel pnl_BtnSettings;
    public static javax.swing.JPanel pnl_Calc;
    private javax.swing.JPanel pnl_Copy;
    private javax.swing.JPanel pnl_DA;
    private javax.swing.JPanel pnl_Nav;
    private javax.swing.JPanel pnl_Notes;
    private javax.swing.JPanel pnl_Snake;
    private javax.swing.JSlider sldrDificultad;
    public static javax.swing.JLabel txtAhorcado;
    public static javax.swing.JLabel txtCC;
    public static javax.swing.JLabel txtColor;
    public static javax.swing.JLabel txtDA;
    public static javax.swing.JLabel txtFuente;
    public static javax.swing.JLabel txtGame;
    public static javax.swing.JLabel txtLetras;
    public static javax.swing.JLabel txtNav;
    public static javax.swing.JLabel txtNotes;
    public static javax.swing.JTextField txtNumRaiz;
    public static javax.swing.JTextField txtNumSqrt;
    public static javax.swing.JLabel txtRecordScore;
    public static javax.swing.JLabel txtScoreSnake;
    public static javax.swing.JLabel txtSnakeDifficult;
    public static javax.swing.JLabel txtSnakeHighScore;
    public static javax.swing.JLabel txtSnakePts;
    public static javax.swing.JLabel txtSnakeRestart;
    public static javax.swing.JLabel txtSnakeScore;
    public static javax.swing.JLabel txtSnakeStart;
    public static javax.swing.JLabel txtSnakeStop;
    public static javax.swing.JLabel txtTamaño;
    public static javax.swing.JLabel txtTop;
    public static javax.swing.JLabel txtVidas;
    private javax.swing.JTextField txt_Buscar;
    private javax.swing.JLabel txt_PalabraOculta;
    private javax.swing.JLabel txt_nLetras;
    private javax.swing.JLabel txt_nVida;
    private javax.swing.JTextArea txtarea;
    public static javax.swing.JTextField valsTxt;
    // End of variables declaration//GEN-END:variables
}

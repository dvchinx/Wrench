package principal;

/**
 *
 * @author dvchinx_
 */
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 530;
    static final int SCREEN_HEIGHT = 530;
    static final int UNIT_SIZE = 27;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / (UNIT_SIZE * UNIT_SIZE);
    static int DELAY = 120;
    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];
    int bodyParts = 6;
    int applesEaten;
    int appleX;
    int appleY;
    int value;
    char direction = 'R';
    boolean blnBorder = false;
    boolean running = false;
    Timer timer;
    Random random;
    public static String txtScore = "Puntaje: ";
    public static String txtGameOver = "Fin del Juego";
    
    public static int pts;
    Random rndm = new Random();

    int highScore;
    int lastScore;
    
    public static Color ColorFood = Color.RED;
    public static Color ColorHead = new Color(25, 156, 0);
    public static Color ColorBody = new Color(43, 255, 0);
    
    public static boolean rgb = false;
    
    GamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.LIGHT_GRAY);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        pts = leerValor("pts.txt");
        switch (value) {
                case 1:
                    DELAY = 200;
                    System.out.println("value = " + value);
                    break;
                case 2:
                    DELAY = 120;
                    System.out.println("value = " + value);
                    break;
                case 3:
                    DELAY = 80;
                    System.out.println("value = " + value);
                    break;
                default:
                    break;
            }
        System.out.println("DELAY = " + DELAY);
        startGame();
    }

    public void startGame() {
        newApple();
//        running = true;
        timer = new Timer(DELAY, this);
//        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {

        if (running) {
//            if (blnBorder) {
//                for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
//                    g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
//                    g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
//                }
//            }

            g.setColor(ColorFood); //COMIDA
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.setColor(ColorHead); //CABEZA
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                } else if(i%2==0 && rgb==true){
                    g.setColor(Color.RED); //CUERPO
                    g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                } else{
                    g.setColor(ColorBody);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
//            g.setColor(Color.BLACK);
//            g.setFont(new Font("Roboto", Font.BOLD, 32));
//            FontMetrics metrics = getFontMetrics(g.getFont());
//            g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: " + applesEaten)) / 2, g.getFont().getSize());
            Interfaz.txtSnakeScore.setText("" + applesEaten);

        } else {
            gameOver(g);
        }

    }

    public void newApple() {
        appleX = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        appleY = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
    }

    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (direction) {
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }

    }

    public void checkApple() {
        pts = leerValor("pts.txt");
        int num;
        
        if ((x[0] == appleX) && (y[0] == appleY)) {
            
            num = random.nextInt(4) + 2; //num entre 2 y 5
            calcPts(num);
            
            bodyParts++;
            applesEaten++;
            newApple();
        }
    }
    
    public void calcPts(int num){
        switch(num){
            case 2:
                pts+=2;
                guardarValor(pts, "pts.txt");
                Interfaz.txtSnakePts.setText("Pts: "+pts);
                break;
            case 3:
                pts+=3;
                guardarValor(pts, "pts.txt");
                Interfaz.txtSnakePts.setText("Pts: "+pts);
                break;
            case 4:
                pts+=4;
                guardarValor(pts, "pts.txt");
                Interfaz.txtSnakePts.setText("Pts: "+pts);
                break;
            case 5:
                pts+=5;
                guardarValor(pts, "pts.txt");
                Interfaz.txtSnakePts.setText("Pts: "+pts);
                break;
        }
    }

    public void checkCollisions() {
        //checks if head collides with body
        for (int i = bodyParts; i > 0; i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
            }
        }
        //check if head touches left border
        if (x[0] < 0) {
            running = false;
        }
        //check if head touches right border
        if (x[0] > SCREEN_WIDTH) {
            running = false;
        }
        //check if head touches top border
        if (y[0] < 0) {
            running = false;
        }
        //check if head touches bottom border
        if (y[0] > SCREEN_HEIGHT) {
            running = false;
        }

        if (!running) {
            timer.stop();
        }
    }

    public void gameOver(Graphics g) {
        //Score
        g.setColor(Color.black);
        g.setFont(new Font("Roboto", Font.BOLD, 40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString(txtScore + applesEaten, (SCREEN_WIDTH - metrics1.stringWidth(txtScore + applesEaten)) / 2, g.getFont().getSize());
        //Game Over text
        g.setColor(Color.black);
        g.setFont(new Font("Roboto", Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString(txtGameOver, (SCREEN_WIDTH - metrics2.stringWidth(txtGameOver)) / 2, SCREEN_HEIGHT / 2);

        highScore();
    }

    public void highScore() {
        lastScore = Integer.parseInt(Interfaz.txtSnakeScore.getText());
        highScore = leerValor("highScore.txt");
        if(lastScore > highScore){
            highScore=lastScore;
            guardarValor(highScore, "highScore.txt");
            Interfaz.txtSnakeHighScore.setText(""+highScore);
            System.out.println(leerValor("highScore.txt"));
        }else{
            Interfaz.txtSnakeHighScore.setText(""+highScore);
        }
    }
    
    public static void guardarValor(int valor, String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            bw.write(Integer.toString(valor));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int leerValor(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String linea = br.readLine();
            return Integer.parseInt(linea);
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (running) {
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'U') {
                        direction = 'D';
                    }
                    break;
            }
        }
    }
}

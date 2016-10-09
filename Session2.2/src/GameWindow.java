import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Lush on 10/2/2016.
 *
 */
public class GameWindow extends Frame implements Runnable {
    public static final int PLANE_FIRST_X = 280;
    public static final int PLANE_FIRST_Y = 300;
    public static final int PLANE2_FIRST_X = 280;
    public static final int PLANE2_FIRST_Y = 100;
    public static final int BACKGROUND_WIDTH = 600;
    public static final int BACKGROUND_HEIGHT = 400;
    public static final int SPAWN_TIME = 100;

    private int lastSpawn;

    Image backgroundImage;
    Image backBufferImage;

    Plane plane;
    Plane plane2;
    ArrayList<EnemyPlane> enemyPlanes;

    public GameWindow(){
        lastSpawn = 0;

        backBufferImage = new BufferedImage(BACKGROUND_WIDTH, BACKGROUND_HEIGHT, BufferedImage.TYPE_INT_ARGB);

        enemyPlanes = new ArrayList<EnemyPlane>();

        try {
            backgroundImage = ImageIO.read(new File("resources/background.png"));
            plane = new Plane(PLANE_FIRST_X, PLANE_FIRST_Y, ImageIO.read(new File("resources/plane3.png")));
            plane2 = new Plane(PLANE2_FIRST_X, PLANE2_FIRST_Y, ImageIO.read(new File("resources/plane4.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setVisible(true);
        this.setSize(BACKGROUND_WIDTH, BACKGROUND_HEIGHT);

        // --------------------Closing window--------------------
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent windowEvent) {
                System.out.println("windowOpened");
            }

            @Override
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent windowEvent) {
                System.out.println("windowClosed");
            }

            @Override
            public void windowIconified(WindowEvent windowEvent) {
                System.out.println("windowIconified");
            }

            @Override
            public void windowDeiconified(WindowEvent windowEvent) {
                System.out.println("windowDeiconified");
            }

            @Override
            public void windowActivated(WindowEvent windowEvent) {
                System.out.println("windowActivated");
            }

            @Override
            public void windowDeactivated(WindowEvent windowEvent) {
                System.out.println("windowDeactivated");
            }
        });

        // --------------------Handling keyboard event--------------------
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                plane.keyPressed(keyEvent);
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {

            }
        });

        // --------------------Handling mouse event--------------------
        this.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent mouseEvent) {

            }

            public void mousePressed(MouseEvent mouseEvent) {
                plane2.mousePressed();
            }

            public void mouseReleased(MouseEvent mouseEvent) {

            }

            public void mouseEntered(MouseEvent mouseEvent) {

            }

            public void mouseExited(MouseEvent mouseEvent) {

            }
        });

        this.addMouseMotionListener(new MouseMotionListener() {
            public void mouseDragged(MouseEvent mouseEvent) {
                plane2.mouseMoved(mouseEvent);
            }

            public void mouseMoved(MouseEvent mouseEvent) {
                plane2.mouseMoved(mouseEvent);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    @Override
    public void update(Graphics g) {
        Graphics backBufferGraphics = backBufferImage.getGraphics();

        backBufferGraphics.drawImage(backgroundImage, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT, null);

        // --------------------Drawing planes--------------------

        plane.drawImage(backBufferGraphics);
        plane2.drawImage(backBufferGraphics);
        for (int i = 0; i < enemyPlanes.size(); i++)
            enemyPlanes.get(i).drawImage(backBufferGraphics);

        // --------------------Drawing bullets--------------------
        if (!plane.getBullets().isEmpty())
            for (int i = 0; i < plane.getBullets().size(); i++)
                plane.getBullets().get(i).drawImage(backBufferGraphics);

        if (!plane2.getBullets().isEmpty())
            for (int i = 0; i < plane2.getBullets().size(); i++)
                plane2.getBullets().get(i).drawImage(backBufferGraphics);

        for (int i = 0; i < enemyPlanes.size(); i++)
            if (!enemyPlanes.get(i).getBullets().isEmpty())
                for (int j = 0; j < enemyPlanes.get(i).getBullets().size(); j++)
                    enemyPlanes.get(i).getBullets().get(j).drawImage(backBufferGraphics);
        // ------------------------------------------------------------

        g.drawImage(backBufferImage, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT, null);
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(17);
                // --------------------Creating enemy Planes--------------------
                EnemyPlane enemy = null;
                if (lastSpawn == SPAWN_TIME){
                    try {
                        enemy = new EnemyPlane(randomEnemy(), 0, ImageIO.read(new File("resources/enemy_plane_white_2.png")));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    enemyPlanes.add(enemy);
                    lastSpawn = 0;
                }
                else lastSpawn++;
                //       ---------Auto moving and shooting---------
                for (int i = 0; i < enemyPlanes.size(); i++) {
                    enemyPlanes.get(i).autoMove();
                    enemyPlanes.get(i).addBullet();
                }

                // --------------------Drawing bullets--------------------
                if (!plane.getBullets().isEmpty())
                    for (int i = 0; i < plane.getBullets().size(); i++)
                        plane.getBullets().get(i).playerShoot();

                if (!plane2.getBullets().isEmpty())
                    for (int i = 0; i < plane2.getBullets().size(); i++)
                        plane2.getBullets().get(i).playerShoot();

                for (int i = 0; i < enemyPlanes.size(); i++)
                        for (int j = 0; j < enemyPlanes.get(i).getBullets().size(); j++)
                            enemyPlanes.get(i).getBullets().get(j).enemyShoot();
                // -------------------------------------------------------
                repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int randomEnemy(){
        Random rd = new Random();
        int range = GameWindow.BACKGROUND_WIDTH - Plane.PLANE_WIDTH;
        int x = rd.nextInt(range);
        return x;
    }
}
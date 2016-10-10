import controllers.EnemyPlaneController;
import controllers.PlaneController;
import models.EnemyPlane;
import models.Plane;
import utils.Utils;
import views.EnemyPlaneView;
import views.PlaneView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

/**
 * Created by apple on 10/2/16.
 */
public class GameWindow extends Frame implements Runnable {

    public static final int BACKGROUND_WIDTH = 600;
    public static final int BACKGROUND_HEIGHT = 400;
    public static final int PLANE1_FIRST_X = BACKGROUND_WIDTH / 2 - Plane.PLANE_WIDTH / 2;
    public static final int PLANE1_FIRST_Y = BACKGROUND_HEIGHT - Plane.PLANE_HEIGHT * 2;
    public static final int PLANE2_FIRST_X = BACKGROUND_WIDTH / 2 - Plane.PLANE_WIDTH / 2;
    public static final int PLANE2_FIRST_Y = BACKGROUND_HEIGHT - Plane.PLANE_HEIGHT * 4;

    Image backgroundImage = null;
    Image backBufferImage;

    private PlaneController planeController;
    private PlaneController planeController2;
    private Vector<EnemyPlaneController> enemyPlaneControllerVector;

    public GameWindow() {

        enemyPlaneControllerVector = new Vector<>();

        planeController = new PlaneController(
                new Plane(PLANE1_FIRST_X, PLANE1_FIRST_Y),
                new PlaneView(Utils.loadImageFromRes("plane3.png"))
        );

        planeController2 = new PlaneController(
                new Plane(PLANE2_FIRST_X, PLANE2_FIRST_Y),
                new PlaneView(Utils.loadImageFromRes("plane4.png"))
        );

        Image enemyPlaneImage = Utils.loadImageFromRes("plane1.png");
        for(int i = 0; i < 10; i++) {
            int y = 0;
            int x = i * (EnemyPlane.ENEMY_PLANE_WIDTH + 5);
            EnemyPlaneController enemyPlaneController = new EnemyPlaneController(
                    new EnemyPlane(x, y),
                    new EnemyPlaneView(enemyPlaneImage)
            );
            enemyPlaneControllerVector.add(enemyPlaneController);
        }

        backBufferImage = new BufferedImage(BACKGROUND_WIDTH,
                BACKGROUND_HEIGHT, BufferedImage.TYPE_INT_ARGB);

        this.setVisible(true);
        this.setSize(BACKGROUND_WIDTH, BACKGROUND_HEIGHT);

        //------------------ Handling window events ------------------
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                System.out.println("windowOpened");
            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("windowClosing");
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("windowClosed");
            }

            @Override
            public void windowIconified(WindowEvent e) {
                System.out.println("windowIconified");
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                System.out.println("windowDeiconified");
            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        //------------------ Handling mouse events--------------------
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                planeController2.mouseMoved(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                planeController2.mouseMoved(e);
            }
        });

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                planeController2.mousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });
        //----------------- Handling keyboard events------------------
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println("keyTyped");
            }

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("keyPressed");
                planeController.keyPressed(e);

//                if(e.getKeyCode() == KeyEvent.VK_SPACE) {
//                    Image bulletImage = Utils.loadImage("resources/bullet.png");
//                    models.Bullet newBullet = new models.Bullet(
//                            plane.getMiddleX() - models.Bullet.BULLET_WIDTH / 2,
//                            plane.getY() - models.Bullet.BULLET_HEIGHT,
//                            bulletImage);
//                    bulletVector.add(newBullet);
//                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                planeController.keyReleased(e);
                System.out.println("keyReleased");
            }
        });
        //------------------------------------------------------------

        try {
            backgroundImage = ImageIO.read (
                    new File("resources/background.png"));
            System.out.println("Loaded backgroundImage");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        System.out.println("draw background image");
    }

    @Override
    public void update(Graphics g) {

        Graphics backBufferGraphics = backBufferImage.getGraphics();

        backBufferGraphics.drawImage(backgroundImage,
                0, 0,
                BACKGROUND_WIDTH, BACKGROUND_HEIGHT, null);

        //---------------------- Player drawing ---------------------
        planeController.draw(backBufferGraphics);
        planeController2.draw(backBufferGraphics);
        //-----------------------------------------------------------

        //--------------------- Enemies drawing ---------------------
        for (EnemyPlaneController enemyPlaneController : enemyPlaneControllerVector){
            enemyPlaneController.draw(backBufferGraphics);
        }
        //-----------------------------------------------------------

        g.drawImage(backBufferImage,
                0, 0,
                BACKGROUND_WIDTH, BACKGROUND_HEIGHT, null);
    }

    int count = 0;

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(17);
                repaint();
                planeController.run();
                planeController2.run();
                for (EnemyPlaneController enemyPlaneController : enemyPlaneControllerVector) {
                    enemyPlaneController.run();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


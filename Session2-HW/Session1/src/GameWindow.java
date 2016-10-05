import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Lush on 10/2/2016.
 *
 */
public class GameWindow extends Frame implements Runnable {
    private static final int PLANE_FIRST_X = 280;
    private static final int PLANE_FIRST_Y = 300;
    private static final int PLANE2_FIRST_X = 280;
    private static final int PLANE2_FIRST_Y = 100;
    private static final int BACKGROUND_WIDTH = 600;
    private static final int BACKGROUND_HEIGHT = 400;

    Image backgroundImage;
    Image backBufferImage;

    Plane plane;
    Plane plane2;
    Bullet bullet;
    Bullet bullet2;

    public GameWindow(){

        backBufferImage = new BufferedImage(BACKGROUND_WIDTH, BACKGROUND_HEIGHT, BufferedImage.TYPE_INT_ARGB);

        try {
            plane = new Plane(PLANE_FIRST_X, PLANE_FIRST_Y, ImageIO.read(new File("resources/plane3.png")));
            plane2 = new Plane(PLANE2_FIRST_X, PLANE2_FIRST_Y, ImageIO.read(new File("resources/plane4.png")));
            bullet = new Bullet(PLANE_FIRST_X + 15, PLANE2_FIRST_X - 15, ImageIO.read(new File("resources/bullet.png")), plane);
            bullet2 = new Bullet(PLANE_FIRST_X + 15, PLANE2_FIRST_X - 15, ImageIO.read(new File("resources/bullet.png")), plane2);

            backgroundImage = ImageIO.read(new File("resources/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setVisible(true);
        this.setSize(BACKGROUND_WIDTH, BACKGROUND_HEIGHT);

        // Closing window
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

        // Handling keyboard event
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                plane.keyPressed(keyEvent);
                bullet.keyPressed(keyEvent);
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {

            }
        });

        // Handling mouse event
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent mouseEvent) {
            }

            @Override
            public void mouseMoved(MouseEvent mouseEvent) {
                plane2.mouseMoved(mouseEvent);
            }
        });

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                bullet2.mouseClicked(mouseEvent);
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

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
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    @Override
    public void update(Graphics g) {
        Graphics backBufferGraphics = backBufferImage.getGraphics();

        backBufferGraphics.drawImage(backgroundImage, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT, null);
        plane.drawImage(backBufferGraphics);
        plane2.drawImage(backBufferGraphics);
        bullet.drawImage(backBufferGraphics);
        bullet2.drawImage(backBufferGraphics);

        g.drawImage(backBufferImage, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT, null);
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(17);
                bullet.fly();
                bullet2.fly();
                repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

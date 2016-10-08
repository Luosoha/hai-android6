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
    public static final int PLANE_FIRST_X = 280;
    public static final int PLANE_FIRST_Y = 300;
    public static final int PLANE2_FIRST_X = 280;
    public static final int PLANE2_FIRST_Y = 100;
    public static final int BACKGROUND_WIDTH = 600;
    public static final int BACKGROUND_HEIGHT = 400;

    Image backgroundImage;
    Image backBufferImage;

    Plane plane;
    Plane plane2;

    public GameWindow(){

        backBufferImage = new BufferedImage(BACKGROUND_WIDTH, BACKGROUND_HEIGHT, BufferedImage.TYPE_INT_ARGB);

        try {
            backgroundImage = ImageIO.read(new File("resources/background.png"));
            plane = new Plane(PLANE_FIRST_X, PLANE_FIRST_Y, ImageIO.read(new File("resources/plane3.png")));
            plane2 = new Plane(PLANE2_FIRST_X, PLANE2_FIRST_Y, ImageIO.read(new File("resources/plane4.png")));
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
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {

            }
        });

        // Handling mouse event
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
        plane.drawImage(backBufferGraphics);
        plane2.drawImage(backBufferGraphics);

        if (!plane.getBullets().isEmpty())
            for (int i = 0; i < plane.getBullets().size(); i++)
                plane.getBullets().get(i).drawImage(backBufferGraphics);

        if (!plane2.getBullets().isEmpty())
            for (int i = 0; i < plane2.getBullets().size(); i++)
                plane2.getBullets().get(i).drawImage(backBufferGraphics);

        g.drawImage(backBufferImage, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT, null);
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(17);

                if (!plane.getBullets().isEmpty())
                    for (int i = 0; i < plane.getBullets().size(); i++)
                        plane.getBullets().get(i).fly();

                if (!plane2.getBullets().isEmpty())
                    for (int i = 0; i < plane2.getBullets().size(); i++)
                        plane2.getBullets().get(i).fly();

                repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
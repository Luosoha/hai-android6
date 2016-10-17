import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by apple on 10/4/16.
 */
public class Plane {
    private int x;
    private int y;
    private Image image;

    public static final int PLANE_WIDTH = 50;
    public static final int PLANE_HEIGHT = 35;

    public Plane(int x, int y, Image image) {
        this.x = x;
        this.y = y;
        this.image = image;
    }

    // Gettters

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getMiddleX() {
        return x + PLANE_WIDTH / 2;
    }

    public Image getImage() {
        return image;
    }

    public void keyPressed(KeyEvent e) {
        System.out.println("keyPressed");
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                System.out.println("Key right");
                this.x += 10;
                break;
            case KeyEvent.VK_LEFT:
                x -= 10;
                break;
            case KeyEvent.VK_UP:
                y -= 10;
                break;
            case KeyEvent.VK_DOWN:
                y += 10;
                break;

        }
    }

    public void mouseMoved(MouseEvent e) {
        x = e.getX() - (PLANE_WIDTH / 2);
        y = e.getY() - (PLANE_HEIGHT / 2);
    }

    public void drawImage(Graphics g) {
        g.drawImage(image, x, y, PLANE_WIDTH, PLANE_HEIGHT, null);
    }
}

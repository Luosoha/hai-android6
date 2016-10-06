import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by Lush on 10/4/2016.
 */

public class Plane {
    private static final int PLANE_WIDTH = 40;
    private static final int PLANE_HEIGHT = 30;

    private int x;
    private int y;
    private Image image;

    public Plane(int x, int y, Image image){
        this.x = x;
        this.y = y;
        this.image = image;
    }

    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()){
            case KeyEvent.VK_RIGHT:
                x += 10;
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

    public void mouseMoved(MouseEvent mouseEvent) {
        x = mouseEvent.getX() - PLANE_WIDTH / 2;
        y = mouseEvent.getY() - PLANE_HEIGHT / 2;
    }

    public void drawImage(Graphics g){
        g.drawImage(image, x, y, PLANE_WIDTH, PLANE_HEIGHT, null);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }
}
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by Lush on 10/4/2016.
 */
public class Bullet {
    private static final int BULLET_WIDTH = 13;
    private static final int BULLET_HEIGHT = 30;
    private static final int BULLET_SPEED = 5;
    private static final int PLANE_WIDTH = 40;
    private static final int PLANE_HEIGHT = 30;

    private int x;
    private int y;
    private Image image;
    private Plane plane;

    public Bullet(int x, int y, Image image, Plane plane){
        this.x = x;
        this.y = y;
        this.image = image;
        this.plane = plane;
    }

    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()){
            case KeyEvent.VK_SPACE:
                x = plane.getX() + PLANE_WIDTH / 2 - BULLET_WIDTH / 2;
                y = plane.getY() - BULLET_HEIGHT / 2;
                break;
        }
    }

    public void mouseClicked(MouseEvent mouseEvent) {
        x = plane.getX() + PLANE_WIDTH / 3;
        y = plane.getY() - PLANE_HEIGHT;
    }

    public void drawImage(Graphics g){
        g.drawImage(image, x, y, BULLET_WIDTH, BULLET_HEIGHT, null);
    }

    public void fly(){
        y -= BULLET_SPEED;
    }
}
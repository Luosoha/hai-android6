import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by Lush on 10/4/2016.
 */
public class Bullet {
    public static final int BULLET_WIDTH = 12;
    public static final int BULLET_HEIGHT = 30;
    public static final int BULLET_SPEED = 5;

    private int x;
    private int y;
    private Image image;
    private Plane plane;

    public Bullet(int x, int y, Image image){
        this.x = x;
        this.y = y;
        this.image = image;
    }

    public void drawImage(Graphics g){
        g.drawImage(image, x, y, BULLET_WIDTH, BULLET_HEIGHT, null);
    }

    public void fly(){
        y -= BULLET_SPEED;
    }
}
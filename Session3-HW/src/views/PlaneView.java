package views;

import models.Plane;

import java.awt.*;

/**
 * Created by Lush on 10/10/2016.
 */
public class PlaneView {

    private Image image;
    public Image getImage() {
        return image;
    }

    public PlaneView(Image image) {
        this.image = image;
    }

    public void drawImage(Graphics g, Plane plane) {
        g.drawImage(
                image,
                plane.getX(), plane.getY(),
                Plane.PLANE_WIDTH, Plane.PLANE_HEIGHT,
                null
        );
    }

}

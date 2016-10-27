package controllers.Manager;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by Lush on 10/25/2016.
 */
public abstract class GameScreen implements MouseListener, KeyListener, MouseMotionListener {

    protected ScreenManager screenManager;

    public GameScreen(ScreenManager screenManager) {
        this.screenManager = screenManager;
    }

    public abstract void run();
    public abstract void update(Graphics g);

}

package controllers;

import models.GameConfig;
import models.GameObject;

import java.awt.*;

import java.util.Iterator;
import java.util.Vector;

/**
 * Created by apple on 10/11/16.
 */
public class ControllerManager implements BaseController {

    protected Vector<BaseController> baseControllers;

    public ControllerManager() {
        this.baseControllers = new Vector<>();
    }

    public void add(BaseController baseController) {
        this.baseControllers.add(baseController);
    }

    @Override
    public void run() {
        Iterator<BaseController> iterator = baseControllers.iterator();
        while(iterator.hasNext()) {
            BaseController baseController = iterator.next();
            if (baseController instanceof SingleController) {
                GameObject gameObject =
                        ((SingleController) baseController).getGameObject();
                if(!gameObject.isAlive()) {
                    iterator.remove();
                } else {
                    baseController.run();
                }
            } else {
                baseController.run(); // Manager
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        for(BaseController baseController : baseControllers) {
            baseController.draw(g);
        }
    }
}

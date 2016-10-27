package controllers;

/**
 * Created by Lush on 10/25/2016.
 */
public interface Subcriber {
    void onEvent(EventType eventType, SingleController singleController);
}

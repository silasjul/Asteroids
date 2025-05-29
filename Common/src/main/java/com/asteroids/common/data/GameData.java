package com.asteroids.common.data;

import java.util.HashSet;
import java.util.Set;

public class GameData {
    boolean isTesting;
    double mouseX;
    double mouseY;
    boolean mousePressed;
    Set<String> keySet = new HashSet<>();
    int frame = 0;
    int animationSpeed = 14;

    public GameData(boolean isTesting) {
        this.isTesting = isTesting;
    }

    // MOUSE
    public double getMousePosX() {
        return this.mouseX;
    }

    public double getMousePosY() {
        return this.mouseY;
    }

    public boolean getMousePressed() {
        return this.mousePressed;
    }

    public void setMouseX(double mouseX) {
        this.mouseX = mouseX;
    }

    public void setMouseY(double mouseY) {
        this.mouseY = mouseY;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    // KEYS
    public void addKey(String key) {
        this.keySet.add(key);
    }

    public void removeKey(String key) {
        this.keySet.remove(key);
    }

    // Other
    public void increaseFrame() {
        frame++;
    }

    public boolean isKeyPressed(String key) {
        return keySet.contains(key);
    }

    public boolean isTesting() {
        return isTesting;
    }

    public boolean isAnimationFrame() {
        return frame % animationSpeed == 0;
    }
}

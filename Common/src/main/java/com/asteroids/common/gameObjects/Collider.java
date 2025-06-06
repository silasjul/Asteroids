package com.asteroids.common.gameObjects;

public class Collider {
    private final int width;
    private final int height;

    Collider(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

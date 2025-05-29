package com.asteroids.common.data;

public class World {
    private final double width;
    private final double height;

    public World(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}

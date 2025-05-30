package com.asteroids.common.gameObjects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Entity {
    protected double x;
    protected double y;
    protected int width;
    protected int height;
    EntityType type;
    Collider collider;

    public Entity(double x, double y, int width, int height, int colliderWidth, int colliderHeight, EntityType type) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.type = type;
        this.collider = new Collider(colliderWidth, colliderHeight);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public EntityType getType() {
        return type;
    }

    public void drawCenterCollider(GraphicsContext gc) {
        gc.setFill(Color.web("aqua", 0.5));
        gc.fillRect(this.x-collider.getWidth()/2., this.y- collider.getHeight()/2., collider.getWidth(), collider.getHeight());

        gc.setFill(Color.RED);
        int size = 5;
        gc.fillOval(this.x-size/2., this.y-size/2., size, size);
    }

    public double getColliderWidth() {
        return this.collider.getWidth();
    }

    public double getColliderHeight() {
        return this.collider.getHeight();
    }
}

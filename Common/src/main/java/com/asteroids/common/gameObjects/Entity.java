package com.asteroids.common.gameObjects;

import com.asteroids.common.data.GameData;
import com.asteroids.common.data.World;
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

    public void drawCollider(GraphicsContext gc, GameData gameData) {
        if (!gameData.isTesting()) return;
        gc.setFill(Color.web("aqua", 0.5));
        gc.fillRect(this.getColliderX(), this.getColliderY(), this.getColliderWidth(), this.getColliderHeight());

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

    public double getColliderX() {
        return this.x-this.collider.getWidth()/2.;
    }
    public double getColliderY() {
        return this.y-this.collider.getHeight()/2.;
    }

    public boolean isOutOfScreen(World world) {
        int max = Math.max(this.width, this.height); // Max distance allowed for a bullet to be out of canvas
        return this.x < -max || this.x > world.getWidth() + max || this.y < -max || this.y > world.getHeight() + max;
    }
}

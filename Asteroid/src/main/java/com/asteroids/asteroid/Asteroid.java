package com.asteroids.asteroid;

import com.asteroids.common.data.GameData;
import com.asteroids.common.data.World;
import com.asteroids.common.gameObjects.Entity;
import com.asteroids.common.gameObjects.EntityType;
import com.asteroids.common.gameObjects.IGameObject;
import com.asteroids.common.sprites.Sprite;
import javafx.scene.canvas.GraphicsContext;

public class Asteroid extends Entity implements IGameObject {
    private final int z = 0;
    private double speed;
    private double angleRadians;
    private double scale;
    private double rotation;
    private Sprite sprite;

    public Asteroid(double x, double y, double angleRadians, int size, double speed, double scale) {
        super(x, y, size, size, size, size, EntityType.ASTEROID);
        this.angleRadians = angleRadians;
        this.speed = speed;
        this.scale = scale;
        this.rotation = angleRadians;
        this.sprite = new Sprite("/asteroid/base.png", 96, 96, this.scale);
    }

    @Override
    public void update(GameData gameData, World world) {
        // Move asteroid
        this.x = this.x + this.speed * Math.cos(this.angleRadians);
        this.y = this.y + this.speed * Math.sin(this.angleRadians);

        // Rotate it
        this.rotation = (this.rotation + speed) % Math.PI*2;
    }

    @Override
    public void draw(GameData gameData, GraphicsContext gc, World world) {
        gc.drawImage(sprite.getSubImage(0, this.rotation), this.x, this.y, this.width, this.height);
    }

    @Override
    public int getZ() {
        return z;
    }
}

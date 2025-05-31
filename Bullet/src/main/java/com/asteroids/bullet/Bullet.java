package com.asteroids.bullet;

import com.asteroids.common.data.GameData;
import com.asteroids.common.data.World;
import com.asteroids.common.gameObjects.Entity;
import com.asteroids.common.gameObjects.EntityType;
import com.asteroids.common.gameObjects.IGameObject;
import com.asteroids.common.sprites.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Bullet extends Entity implements IGameObject {
    double speed;
    double angleRad;
    double imgAngleRad;
    Sprite sprite;
    private final int z = 0;

    public Bullet(double x, double y, double speed, double angleRad, BulletType type, EntityType entityType, double scale) {
        super(x, y, 32,32, 20, 20, entityType);
        this.speed = speed;
        this.angleRad = angleRad;
        this.imgAngleRad = angleRad + Math.PI/2;

        // Different bullets
        switch (type) {
            case ZAP -> this.sprite = new Sprite ("/player/projectiles/zap.png", width, height, scale, imgAngleRad);
            case BULLET -> this.sprite = new Sprite ("/player/projectiles/bullet.png", width, height, scale, imgAngleRad);
            case BALL -> this.sprite = new Sprite ("/player/projectiles/ball.png", width, height, scale, imgAngleRad);
        }
    }

    @Override
    public void update(GameData gameData, World world) {
        if (this.isOutOfScreen(world)) {
            world.removeGameObject(this);
            return;
        }
        move();
    }

    @Override
    public void draw(GameData gameData, GraphicsContext gc, World world) {
        Image img = sprite.getSubImage(sprite.getCurrent(), this.imgAngleRad);
        gc.drawImage(img, x - img.getWidth()/2, y - img.getHeight()/2);
        if (gameData.isAnimationFrame()) sprite.next();

        drawCollider(gc, gameData);
    }

    @Override
    public int getZ() {
        return z;
    }

    public void move() {
        this.x = this.x + speed * Math.cos(angleRad);
        this.y = this.y + speed * Math.sin(angleRad);
    }
}

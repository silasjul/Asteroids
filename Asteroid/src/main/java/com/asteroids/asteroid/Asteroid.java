package com.asteroids.asteroid;

import com.asteroids.common.data.GameData;
import com.asteroids.common.data.World;
import com.asteroids.common.gameObjects.Entity;
import com.asteroids.common.gameObjects.EntityType;
import com.asteroids.common.gameObjects.IGameObject;
import com.asteroids.common.gameObjects.IPlayer;
import com.asteroids.common.sprites.Sprite;
import javafx.scene.canvas.GraphicsContext;

public class Asteroid extends Entity implements IGameObject {
    private final int z = 0;
    private final double speed;
    private final double angleRadians;
    private final double scale;
    private final Sprite spriteAsteroid;
    private final Sprite spriteFlame;
    private Sprite spriteDestroyedAnimation;
    private final int size;
    private double rotation;
    private boolean hasEnteredScreen = false;
    private boolean isDestroyed = false;
    private final int minSize = 120;
    private IPlayer player;

    public Asteroid(double x, double y, double angleRadians, int size, double speed, double scale, IPlayer player) {
        super(x, y, size, size, size/2, size/2, EntityType.ASTEROID);
        this.angleRadians = angleRadians;
        this.size = size;
        this.speed = speed;
        this.scale = scale;
        this.rotation = angleRadians;
        this.spriteAsteroid = new Sprite("/asteroid/base.png", 96, 96, this.scale);
        this.spriteFlame = new Sprite("/asteroid/asteroidFlame.png", 96, 96, this.scale, this.angleRadians + Math.PI);
        this.player = player;
    }

    @Override
    public void update(GameData gameData, World world) {
        if (isDestroyed) return;

        // Move asteroid
        this.x = this.x + this.speed * Math.cos(this.angleRadians);
        this.y = this.y + this.speed * Math.sin(this.angleRadians);

        // Rotate
        double rotationSpeed = this.speed / 100;
        this.rotation = (this.rotation + rotationSpeed) % (Math.PI*2);

        // Collision
        for (Entity entity : world.getEntities(EntityType.PLAYERBULLET)) {
            if (world.isColliding(this, entity))
                onHit(this, entity, world);
        }

        if (player != null && player.isAlive() && world.isColliding(this, (Entity) player)) {
            player.takeDmg(this.size/2);
            this.triggerDeathAnimation();
            return;
        }

        // Entered Screen
        if (!this.hasEnteredScreen) {
            // Check if inside screen
            if (0 < this.x && this.x < world.getWidth() && 0 < this.y && this.y < world.getHeight()) {
                this.hasEnteredScreen = true;
            }
        }

        // Check out of screen
        else {
            if (this.isOutOfScreen(world)) {
                world.removeGameObject(this);
            }
        }
    }

    @Override
    public void draw(GameData gameData, GraphicsContext gc, World world) {
        if (isDestroyed) {
            if (spriteDestroyedAnimation.getAnimationCount() > 0) {
                world.removeGameObject(this);
                return;
            }
            gc.drawImage(spriteDestroyedAnimation.getCurrentImage(), x-this.width/2.*scale, y-this.height/2.*scale, this.width*scale, this.height*scale);
            if (gameData.isAnimationFrame()) spriteDestroyedAnimation.next();
            return;
        }

        // Draw sprites
        gc.drawImage(spriteFlame.getCurrentImage(), x-this.width/2.*scale, y-this.height/2.*scale, this.width*scale, this.height*scale);
        if (gameData.isAnimationFrame()) spriteFlame.next();

        gc.drawImage(spriteAsteroid.getSubImage(0, this.rotation), x-this.width/2.*scale, y-this.height/2.*scale, this.width*scale, this.height*scale);

        drawCollider(gc, gameData);
    }

    @Override
    public int getZ() {
        return z;
    }

    private void onHit(Asteroid hitAsteroid, Entity bullet, World world) {
        world.removeGameObject((IGameObject) bullet);
        if (hitAsteroid.size >= minSize) {
            split(world);
            world.removeGameObject(hitAsteroid);
        } else {
            triggerDeathAnimation();
        }
    }

    private void triggerDeathAnimation() {
        this.isDestroyed = true;
        this.spriteDestroyedAnimation = new Sprite("/asteroid/explosion.png", 96, 96, this.scale, this.rotation);
    }

    private void split(World world) {
        // Spawn 2 new asteroids in half size
        Asteroid child1 = new Asteroid(this.x, this.y, this.angleRadians + Math.PI/8, (int) (this.size/2), this.speed, this.scale, this.player);
        Asteroid child2 = new Asteroid(this.x, this.y, this.angleRadians - Math.PI/8, (int) (this.size/2), this.speed, this.scale, this.player);

        world.addGameObject(child1);
        world.addGameObject(child2);
    }
}

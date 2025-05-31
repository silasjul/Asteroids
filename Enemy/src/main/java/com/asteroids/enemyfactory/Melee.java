package com.asteroids.enemyfactory;

import com.asteroids.common.data.GameData;
import com.asteroids.common.data.World;
import com.asteroids.common.gameObjects.*;
import com.asteroids.common.sprites.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


/**
 * A close ranged enemy that seeks towards the player and damages the player at close range
 *
 */
public class Melee extends Enemy {
    private final Sprite sprite;
    private final Sprite deathAnimation;
    private boolean isDeathAnimationFinished = false;

    public Melee(double x, double y, IPlayer player) {
        super(x, y,64, 64,45, 40, EntityType.ENEMY, 1.8,20,5, 1, 1, 0, player);

        this.sprite = new Sprite("/melee/ships.png", width, height, scale);
        this.deathAnimation = new Sprite("/melee/death.png", width, height, scale);
    }

    @Override
    public void update(GameData gameData, World world) {
        if (isDead) {
            if (gameData.isAnimationFrame() && !isDeathAnimationFinished) deathAnimation.next();
            if (deathAnimation.getAnimationCount() > 0) {
                world.removeGameObject(this);
                isDeathAnimationFinished = true;
            }
            return;
        }

        this.move(gameData, world);

        if (!isPlayerImplementation) return;
        // Attack player on collision
        if (isLoaded() && world.isColliding(this, (Entity) player)) this.fire(world, gameData);

        checkBulletCollision(world);
    }

    @Override
    public void draw(GameData gameData, GraphicsContext gc, World world) {
        if (isDeathAnimationFinished) return;
        gc.drawImage(this.getImg(), x-this.width/2.*scale, y-this.height/2.*scale, this.width*scale, this.height*scale);

        drawCollider(gc, gameData);
    }

    private Image getImg() {
        if (isDead) return deathAnimation.getCurrentImage();
        return sprite.getCurrentImage();}

    @Override
    protected void move(GameData gameData, World world) {
        double angleRad = isPlayerImplementation ? calcAngle(player.getCenterX(), player.getCenterY()) : calcAngle(world.getWidth()/2, world.getHeight()/2);
        double moveX = speed * Math.cos(angleRad);
        double moveY = speed * Math.sin(angleRad);
        double[] force = calcSeperationForce(world);

        this.x += moveX + force[0];
        this.y += moveY + force[1];
    }

    @Override
    protected void fire(World world, GameData gameData) {
        player.takeDmg(this.dmg);
        lastFire = System.currentTimeMillis();
    }

    @Override
    public void die() {
        this.isDead = true;
    }

    @Override
    public int getZ() {
        return 0;
    }
}

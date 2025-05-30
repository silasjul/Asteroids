package com.asteroids.enemyfactory;

import com.asteroids.common.data.GameData;
import com.asteroids.common.data.World;
import com.asteroids.common.gameObjects.*;
import com.asteroids.common.gameObjects.Character;
import com.asteroids.common.sprites.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


/**
 * A close ranged enemy that seeks towards the player and damages the player at close range
 *
 */
public class Melee extends Character
{
    protected IPlayer player;
    protected Sprite sprite;
    protected Sprite deathAnimation;
    boolean isDeathAnimationFinished = false;
    boolean isPlayerImplementation = true;

    public Melee(double x, double y, IPlayer player) {
        super(x, y,64, 64,45, 40, EntityType.ENEMY, 1.8,20,5, 1, 1, 0);
        this.player = player;
        if (player == null) isPlayerImplementation = false;

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

        // Hit by player bullet
        for (Entity bullet : world.getEntities(EntityType.PLAYERBULLET)) {
            if (world.isColliding(this, bullet)) {
                world.removeGameObject((IGameObject) bullet);
                this.takeDmg(player.getDmg());
            }
        }
    }

    @Override
    public void draw(GameData gameData, GraphicsContext gc, World world) {
        if (isDeathAnimationFinished) return;
        gc.drawImage(this.getImg(), x-this.width/2.*scale, y-this.height/2.*scale, this.width*scale, this.height*scale);

        if (gameData.isTesting()) drawCenterCollider(gc);
    }

    @Override
    protected Image getImg() {
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

    protected double[] calcSeperationForce(World world) {
        // Separation force to prevent enemies from stacking
        double separationX = 0;
        double separationY = 0;
        double minDistance = this.width-10;

        for (Entity other : world.getEntities(EntityType.ENEMY)) {
            if (other == this) continue;

            double dx = this.x - other.getX();
            double dy = this.y - other.getY();
            double distance = Math.sqrt(dx * dx + dy * dy);

            if (distance < minDistance && distance > 0) {
                // If an enemy gets too close we apply the force
                separationX += dx / distance;
                separationY += dy / distance;
            }
        }
        return new double[] {separationX, separationY};
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

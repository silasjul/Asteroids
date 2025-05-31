package com.asteroids.enemyfactory;

import com.asteroids.common.data.World;
import com.asteroids.common.gameObjects.*;
import com.asteroids.common.gameObjects.Character;

public abstract class Enemy extends Character {
    protected IPlayer player;
    boolean isPlayerImplementation = true;

    public Enemy(double x, double y, int width, int height, int colliderWidth, int colliderHeight, EntityType type, double scale, int hp, int dmg, double speed, int fireRate, int bulletSpeed, IPlayer player) {
        super(x, y, width, height, colliderWidth, colliderHeight, type, scale, hp, dmg, speed, fireRate, bulletSpeed);

        this.player = player;
        if (player == null) isPlayerImplementation = false;
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

    protected void checkBulletCollision(World world) {
        for (Entity bullet : world.getEntities(EntityType.PLAYERBULLET)) {
            if (world.isColliding(this, bullet)) {
                world.removeGameObject((IGameObject) bullet);
                this.takeDmg(((IBullet) (bullet)).getDamage());
            }
        }
    }
}

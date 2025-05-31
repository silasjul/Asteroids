package com.asteroids.enemyfactory;
import com.asteroids.common.data.GameData;
import com.asteroids.common.data.World;
import com.asteroids.common.gameObjects.BulletType;
import com.asteroids.common.gameObjects.EntityType;
import com.asteroids.common.gameObjects.IPlayer;
import com.asteroids.common.gameObjects.IWeapon;
import com.asteroids.common.sprites.Sprite;
import javafx.scene.canvas.GraphicsContext;

public class Shooter extends Enemy {
    private Sprite spriteBase;
    private Sprite spriteDeathAnimation;
    public double moveX, moveY;
    private long lastDirectionChangeTime;
    private final long directionChangeInterval = 1000;

    public Shooter(double x, double y, IPlayer player) {
        super(x, y, 64, 64, 50, 50, EntityType.ENEMY, 2, 10, 30, 1, 1, 4, player);

        this.spriteBase = new Sprite("/shooter/base.png", 64, 64, 2);
        this.spriteDeathAnimation = new Sprite("/shooter/death.png", 64, 64, 2);
    }

    @Override
    protected void fire(World world, GameData gameData) {
        IWeapon weapon = world.getWeapon();
        if (weapon == null) return;

        // Firing angle
        double dy = player.getY() - this.y;
        double dx = player.getX() - this.x;
        double firingAngleRad = Math.atan2(dy, dx);

        weapon.fire(world,this.x, this.y, this.bulletSpeed, firingAngleRad, this.dmg,BulletType.TORPEDO, EntityType.ENEMYBULLET, 3);
        this.lastFire = System.currentTimeMillis();
    }

    @Override
    public void die() {
        this.isDead = true;
    }

    @Override
    public void update(GameData gameData, World world) {
        if (this.isDead) return;

        this.move(gameData, world);
        checkBulletCollision(world);

        if (isPlayerImplementation && player.isAlive() && isLoaded()) {
            fire(world, gameData);
        }
    }

    @Override
    public void draw(GameData gameData, GraphicsContext gc, World world) {
        if (this.isDead) {
            // Draw death animation
            if (spriteDeathAnimation.getAnimationCount() > 0) {
                world.removeGameObject(this);
                return;
            }
            gc.drawImage(spriteDeathAnimation.getCurrentImage(), x-this.width/2.*scale, y-this.height/2.*scale, this.width*scale, this.height*scale);
            if (gameData.isAnimationFrame()) spriteDeathAnimation.next();
            return;
        }

        gc.drawImage(this.spriteBase.getImage(0), x-this.width/2.*scale, y-this.height/2.*scale, this.width*scale, this.height*scale);

        drawCollider(gc, gameData);
    }

    @Override
    protected void move(GameData gameData, World world) {
        if (System.currentTimeMillis() - lastDirectionChangeTime > directionChangeInterval) {
            lastDirectionChangeTime = System.currentTimeMillis();
            // Pick random direction
            this.moveX = (-1 + Math.random() * 2) * speed;
            this.moveY = (-1 + Math.random() * 2) * speed;
        }

        double marginX = world.getWidth() / 5;
        double marginY = world.getHeight() / 5;

        // Out of bound horizontal
        if (this.x < marginX) {
            this.moveX = Math.abs(moveX);
        }
        else if (world.getWidth() - marginX < marginX) {
            this.moveX = -Math.abs(moveX);
        }

        // Out of bound vertical
        if (this.y < marginY) {
            this.moveY = Math.abs(moveY);
        }
        else if (world.getHeight() - marginY < this.y) {
            this.moveY = -Math.abs(moveY);
        }

        double[] separationForce = calcSeperationForce(world);

        this.x += this.moveX + separationForce[0];
        this.y += this.moveY + separationForce[1];
    }

    @Override
    public int getZ() {
        return 0;
    }
}

package com.asteroids.player;

import com.asteroids.bullet.Bullet;
import com.asteroids.bullet.BulletType;
import com.asteroids.common.data.GameData;
import com.asteroids.common.data.World;
import com.asteroids.common.gameObjects.Character;
import com.asteroids.common.gameObjects.EntityType;
import com.asteroids.common.gameObjects.IPlayer;
import com.asteroids.common.sprites.Sprite;
import com.asteroids.player.engine.Engine;
import com.asteroids.player.engine.EngineManager;
import com.asteroids.player.engine.EngineType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;

public class Player extends Character implements IPlayer {
    private int xp = 0;
    private final EngineManager engineManager;
    private final Engine engine;
    private boolean isMoving = false;
    private final int z = 10;

    HashMap<PlayerCondition, String> shipMap = new HashMap<>();

    public Player(double screenWidth, double screenHeight) {
        super(0,0, 48, 48, 40,40, EntityType.PLAYER, 2.5, 100,10, 2,5, 5);

        // Spawn position
        this.x = screenHeight / 2. - this.width / 2.;
        this.y = screenWidth / 1.8;

        // Ships
        shipMap.put(PlayerCondition.PERFECT, "/player/pl100.png");
        shipMap.put(PlayerCondition.GOOD, "/player/pl75.png");
        shipMap.put(PlayerCondition.DAMAGED, "/player/pl50.png");
        shipMap.put(PlayerCondition.SHIT, "/player/pl25.png");

        // Engine
        this.engineManager = new EngineManager();
        this.engine = engineManager.getEngine(EngineType.CHARGED);
    }

    @Override
    public void update(GameData gameData, World world) {
        if (isDead) return;

        this.angle = calcAngle(gameData.getMousePosX(), gameData.getMousePosY());
        move(gameData, world);

        // Bullet logic
        if (gameData.getMousePressed() && isLoaded()) {
            fire(world, gameData);
        }
    }

    @Override
    public void draw(GameData gameData, GraphicsContext gc, World world) {
        if (isDead) return;

        double rotation = this.getImageRotation();

        // draw engine flame
        Sprite flame = isMoving ? engine.getPowering(this.scale, rotation) : engine.getIdle(this.scale, rotation);
        int width = flame.getWidth();
        int height = flame.getHeight();
        double[] flamePos = getCenterImagePos(width, height);
        Image flameImage = flame.getSubImages(engine.getCurrent(gameData));
        gc.drawImage(flameImage, flamePos[0], flamePos[1], width*this.scale, height*this.scale);

        // draw engine
        Sprite base = engine.getBase(this.scale, rotation);
        width = base.getWidth();
        height = base.getHeight();
        double[] enginePos = getCenterImagePos(width, height);
        Image engineImage = base.getSubImages(base.getCurrent());
        gc.drawImage(engineImage, enginePos[0], enginePos[1], width*this.scale, height*this.scale);

        // draw ship
        double[] pos = getCenterImagePos(this.width, this.height);
        gc.drawImage(getImg(), pos[0] , pos[1], this.width*scale, this.height*scale);

        // draw collision in testing
        if (gameData.isTesting()) drawCenterCollider(gc);
    }

    @Override
    public int getZ() {
        return z;
    }

    @Override
    protected void fire(World world, GameData gameData) {
        Bullet bullet = new Bullet(this.x, this.y, this.bulletSpeed, this.angle, BulletType.BULLET, EntityType.PLAYERBULLET, this.scale);
        world.addGameObject(bullet);
        lastFire = System.currentTimeMillis();
    }

    @Override
    public Image getImg() {
        PlayerCondition c;
        if (hp >= 75) c = PlayerCondition.PERFECT;
        else if (hp >= 50) c = PlayerCondition.GOOD;
        else if (hp >= 25) c = PlayerCondition.DAMAGED;
        else c = PlayerCondition.SHIT;

        Sprite sprite = new Sprite (this.shipMap.get(c), this.width, this.height, this.scale, getImageRotation());
        return sprite.getSubImages(0);
    }

    @Override
    public void move(GameData gameData, World world)
    {
        double tempX = this.x;
        double tempY = this.y;
        // x-axis
        if (gameData.isKeyPressed("A")) x -= speed;
        if (gameData.isKeyPressed("D")) x += speed;

        // y-axis
        if (gameData.isKeyPressed("W")) y -= speed;
        if (gameData.isKeyPressed("S")) y += speed;

        // the player moved
        this.isMoving = this.x != tempX || this.y != tempY;
    }

    @Override
    public void die() {
        isDead = true;
    }

    private double[] getCenterImagePos(int width, int height) {
        double shipX = this.x - width * this.scale / 2;
        double shipY = this.y - height * this.scale / 2;

        return new double[]{shipX, shipY};
    }

    private double getImageRotation() {
        return this.angle + Math.PI/2;
    }

    private double[] getOffsetPosition(double offSet, double width, double height) {
        // returns an offset position from the center of the player to draw an image from based on rotation of player
        double engineX = getCenterX() + offSet * Math.cos(this.angle) - width/2.*this.scale;
        double engineY = getCenterY() + offSet * Math.sin(this.angle) - height/2.*this.scale;

        return new double[]{engineX, engineY};
    }
}

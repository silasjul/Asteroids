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

    HashMap<PlayerCondition, Sprite> shipMap = new HashMap<>();

    public Player(double screenWidth, double screenHeight, double scale) {
        super(0,0, 48, 48, 40,40, EntityType.PLAYER, scale, 100,10, 2,5, 5);

        // Spawn position
        this.x = screenHeight / 2. - this.width / 2.;
        this.y = screenWidth / 1.8;

        // Ships
        shipMap.put(PlayerCondition.PERFECT, new Sprite ("/player/pl100.png", this.width, this.height, this.scale));
        shipMap.put(PlayerCondition.GOOD, new Sprite ("/player/pl75.png", this.width, this.height, this.scale));
        shipMap.put(PlayerCondition.DAMAGED, new Sprite ("/player/pl50.png", this.width, this.height, this.scale));
        shipMap.put(PlayerCondition.SHIT, new Sprite ("/player/pl25.png", this.width, this.height, this.scale));

        // Engine
        this.engineManager = new EngineManager();
        this.engine = engineManager.getEngine(EngineType.CHARGED, this.scale);
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
        Sprite flame = isMoving ? engine.getPowering(rotation) : engine.getIdle(rotation);
        int width = flame.getWidth();
        int height = flame.getHeight();
        double[] flamePos = getCenterImagePos(width, height);
        Image flameImage = flame.getSubImage(engine.getCurrent(gameData), rotation);
        gc.drawImage(flameImage, flamePos[0], flamePos[1], width*this.scale, height*this.scale);

        // draw engine
        Sprite base = engine.getBase(rotation);
        width = base.getWidth();
        height = base.getHeight();
        double[] enginePos = getCenterImagePos(width, height);
        Image engineImage = base.getSubImage(base.getCurrent(), rotation);
        gc.drawImage(engineImage, enginePos[0], enginePos[1], width*this.scale, height*this.scale);

        // draw ship
        double[] pos = getCenterImagePos(this.width, this.height);
        gc.drawImage(getPlayerImg(), pos[0] , pos[1], this.width*scale, this.height*scale);

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

    private Image getPlayerImg() {
        PlayerCondition c;
        if (hp >= 75) c = PlayerCondition.PERFECT;
        else if (hp >= 50) c = PlayerCondition.GOOD;
        else if (hp >= 25) c = PlayerCondition.DAMAGED;
        else c = PlayerCondition.SHIT;

        Sprite sprite = this.shipMap.get(c);
        return sprite.getSubImage(0, getImageRotation());
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

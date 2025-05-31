package com.asteroids.asteroid;

import com.asteroids.common.data.World;
import com.asteroids.common.gameObjects.EntityType;
import com.asteroids.common.spawner.ISpawner;
import com.asteroids.common.spawner.Spawner;
import com.asteroids.common.sprites.Sprite;

public class AsteroidSpawner extends Spawner implements ISpawner {

    AsteroidSpawner() {
        super(2, 1);
    }

    @Override
    public void spawn(World world) {
        // Is it time to spawn and are we bellow or equal to max allowed instances of this entity type
        if (!(this.getLastSpawnSeconds() >= spawnRate && world.getEntities(EntityType.ASTEROID).size() <= this.maxInstances)) return;

        // Spawn position
        int[] passPoint = getPassthroughPoint(world);
        double angleRadians = getRandomAngleRadians();
        int distance = (int) world.getWidth();
        int[] spawnPos = getSpawnPos(passPoint, angleRadians, distance);

        double scale = getRandomScale();
        int size = (int) (96 * scale);
        Asteroid asteroid = new Asteroid(spawnPos[0], spawnPos[1], angleRadians, size, getRandomSpeed(), scale);
        world.addGameObject(asteroid);

        this.setSpawnTime();
    }

    private int[] getPassthroughPoint(World world) {
        int x;
        int y;

        int windowWidth = (int) world.getWidth();
        int windowHeight = (int) world.getHeight();

        // X and Y values should range from 1/4 to 3/4 of the screen width or height
        int passthroughX = (int) Math.round((windowWidth/4.) + Math.random() * (windowWidth/2.));
        int passthroughY = (int) Math.round((windowHeight/4.) + Math.random() * (windowHeight/2.));

        return new int[]{passthroughX, passthroughY};
    }

    private double getRandomAngleRadians() {
        return Math.random() * 2*Math.PI;
    }

    private int[] getSpawnPos(int[] passthroughPoint, double angleRadians, int distance) {
        // The spawn position should be opposite the travel direction from the intended passthroughPoint by a certain distance
        double reverseAngle = Math.PI + angleRadians;
        int spawnX = (int) Math.round(passthroughPoint[0] + distance * Math.cos(reverseAngle));
        int spawnY = (int) Math.round(passthroughPoint[1] + distance * Math.sin(reverseAngle));

        return new int[]{spawnX, spawnY};
    }

    private double getRandomSpeed() {
        return 1 + Math.random() * 1;
    }

    private double getRandomScale() {
        return 1 + Math.random() * 3;
    }
}

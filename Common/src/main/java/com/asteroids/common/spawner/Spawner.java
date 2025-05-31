package com.asteroids.common.spawner;

import com.asteroids.common.data.World;

public abstract class Spawner {
    protected int maxInstances;
    protected double spawnRate;
    protected double lastSpawnTime = 0;

    protected Spawner(int maxInstances, double spawnRate) {
        this.maxInstances = maxInstances;
        this.spawnRate = spawnRate;
    };

    protected int getLastSpawnSeconds() {
        return (int) (System.currentTimeMillis() / 1000. - this.lastSpawnTime);
    }

    protected double[] getSpawnPos(World world) {
        double[] spawnPos = new double[2];

        double sideToSpawn = Math.random();

        // spawn topside
        if (sideToSpawn <= 0.25) {
            spawnPos[0] = Math.random() * world.getWidth();
            spawnPos[1] = -50;
        }

        // spawn bottom
        else if (sideToSpawn <= 0.50) {
            spawnPos[0] = Math.random() * world.getWidth();
            spawnPos[1] = world.getHeight() + 10;
        }

        // spawn right
        else if (sideToSpawn <= 0.75) {
            spawnPos[0] = world.getWidth() + 10;
            spawnPos[1] = Math.random() * world.getHeight();
        }

        // spawn left
        else {
            spawnPos[0] = -50;
            spawnPos[1] = Math.random() * world.getHeight();
        }

        return spawnPos;
    }

    protected void setSpawnTime() {
        this.lastSpawnTime = System.currentTimeMillis() / 1000.;
    }
}

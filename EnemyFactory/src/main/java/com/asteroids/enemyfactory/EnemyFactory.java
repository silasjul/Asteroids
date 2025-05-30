package com.asteroids.enemyfactory;

import com.asteroids.common.data.IEnemyFactory;
import com.asteroids.common.data.World;

public class EnemyFactory implements IEnemyFactory {
    int maxEnemies = 10;
    double spawnRate = .5;
    double lastSpawnTime = 0;

    public enum Type {
        MELEE,
    }

    @Override
    public void spawn(World world) {
        if (getLastSpawnSeconds() >= spawnRate && world.getEnemiesAmount() < this.maxEnemies) {
            double[] spawnPos = getSpawnPos(world);
            spawnEnemy(Type.MELEE, spawnPos[0], spawnPos[1], world);
            lastSpawnTime = System.currentTimeMillis() / 1000.;
        }
    }

    private void spawnEnemy(Type type, double x, double y, World world) {
        switch (type) {
            case MELEE -> world.addGameObject(new Melee(x, y, world.getPlayer()));
        }
    }

    private int getLastSpawnSeconds() {
        return (int) (System.currentTimeMillis() / 1000. - this.lastSpawnTime);
    }

    private double[] getSpawnPos(World world) {
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
}

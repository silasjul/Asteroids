package com.asteroids.enemyfactory;

import com.asteroids.common.data.World;
import com.asteroids.common.gameObjects.EntityType;
import com.asteroids.common.spawner.ISpawner;
import com.asteroids.common.spawner.Spawner;

public class EnemySpawner extends Spawner implements ISpawner {
    EnemySpawner() {
        super(10, 0.5);
    }

    @Override
    public void spawn(World world) {
        if (!(this.getLastSpawnSeconds() >= spawnRate && world.getEntities(EntityType.ENEMY).size() <= this.maxInstances)) return;

        double[] spawnPos = getSpawnPos(world);
        spawnEnemy(EnemyType.MELEE, spawnPos[0], spawnPos[1], world);
        this.setSpawnTime();
    }

    private void spawnEnemy(EnemyType type, double x, double y, World world) {
        switch (type) {
            case MELEE -> world.addGameObject(new Melee(x, y, world.getPlayer()));
        }
    }
}

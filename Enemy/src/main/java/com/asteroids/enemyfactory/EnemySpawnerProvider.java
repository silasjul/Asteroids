package com.asteroids.enemyfactory;

import com.asteroids.common.data.World;
import com.asteroids.common.services.IPluginService;

public class EnemySpawnerProvider implements IPluginService {
    @Override
    public void start(World world) {
        System.out.println("Starting EnemySpawner");
        world.addSpawner(new EnemySpawner());
    }

    @Override
    public void stop(World world) {

    }
}

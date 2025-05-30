package com.asteroids.enemyfactory;

import com.asteroids.common.data.World;
import com.asteroids.common.services.IPluginService;

public class EnemyFactoryProvider implements IPluginService {
    @Override
    public void start(World world) {
        System.out.println("Starting EnemyFactory");
        world.setEnemyFactory(new EnemyFactory());
    }

    @Override
    public void stop(World world) {

    }
}

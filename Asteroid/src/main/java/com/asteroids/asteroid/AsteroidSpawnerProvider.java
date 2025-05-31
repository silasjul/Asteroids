package com.asteroids.asteroid;

import com.asteroids.common.data.World;
import com.asteroids.common.services.IPluginService;

public class AsteroidSpawnerProvider implements IPluginService {
    @Override
    public void start(World world) {
        System.out.println("Starting AsteroidSpawner");
        world.addSpawner(new AsteroidSpawner());
    }
}

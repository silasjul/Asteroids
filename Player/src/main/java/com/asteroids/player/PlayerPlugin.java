package com.asteroids.player;

import com.asteroids.common.data.World;
import com.asteroids.common.services.IPluginService;

public class PlayerPlugin implements IPluginService {

    @Override
    public void start(World world) {
        System.out.println("Starting Player");
        Player player = new Player(world.getWidth(), world.getHeight());
        world.addGameObject(player);
        world.setPlayer(player);
    }

    @Override
    public void stop(World world) {

    }
}

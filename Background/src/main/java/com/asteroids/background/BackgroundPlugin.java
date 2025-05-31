package com.asteroids.background;

import com.asteroids.common.data.World;
import com.asteroids.common.services.IPluginService;

public class BackgroundPlugin implements IPluginService {

    @Override
    public void start(World world) {
        System.out.println("Starting Background");
        Background bg = new Background((int) world.getWidth(), (int) world.getHeight());
        world.addGameObject(bg);
    }
}

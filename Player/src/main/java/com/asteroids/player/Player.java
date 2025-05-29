package com.asteroids.player;

import com.asteroids.common.data.GameData;
import com.asteroids.common.data.World;
import com.asteroids.common.services.IRenderService;
import com.asteroids.common.services.IStartService;
import javafx.scene.canvas.GraphicsContext;

public class Player implements IStartService, IRenderService {

    @Override
    public void start(World world) {
        System.out.println("Starting Player");
    }

    @Override
    public void stop(World world) {
        System.out.println("Stopping Player");
    }

    @Override
    public void update(World world) {

    }

    @Override
    public void draw(GameData gameData, GraphicsContext gc, World world) {

    }
}

package com.asteroids.common.services;

import com.asteroids.common.data.GameData;
import com.asteroids.common.data.World;
import javafx.scene.canvas.GraphicsContext;

public interface IGameObject {
    void update(GameData gameData, World world);
    void draw(GameData gameData, GraphicsContext gc, World world);
    int getZ();
}

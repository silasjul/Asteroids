package com.asteroids.common.data;

import com.asteroids.common.services.IGameObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class World {
    private final double width;
    private final double height;
    private final List<IGameObject> gameObjects = new ArrayList<>();

    public World(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public List<IGameObject> getGameObjects() {
        return gameObjects;
    }

    public void addGameObject(IGameObject gameObject) {
        this.gameObjects.add(gameObject);
        sortGameObjects();
    }

    public void removeGameObject(IGameObject gameObject) {
        this.gameObjects.remove(gameObject);
    }

    private void sortGameObjects() {
        gameObjects.sort(new Comparator<IGameObject>() {
            @Override
            public int compare(IGameObject o1, IGameObject o2) {
                return o1.getZ() - o2.getZ();
            }
        });
    }
}

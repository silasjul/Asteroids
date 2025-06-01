package com.asteroids.common.data;

import com.asteroids.common.gameObjects.*;
import com.asteroids.common.scoringClient.ScoringClient;
import com.asteroids.common.spawner.ISpawner;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class World {
    private final double width;
    private final double height;
    private final List<IGameObject> gameObjects = new ArrayList<>();
    private final List<IGameObject> gameObjectsToAdd = new ArrayList<>();
    private final List<IGameObject> gameObjectsToRemove = new ArrayList<>();
    private final List<ISpawner> spawners = new ArrayList<>();
    private final ScoringClient scoringClient;
    private IPlayer player;
    private IWeapon weapon;

    public World(double width, double height, ScoringClient scoringClient) {
        this.width = width;
        this.height = height;
        this.scoringClient = scoringClient;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public List<ISpawner> getSpawners() {
        return spawners;
    }

    public void addSpawner(ISpawner spawner) {
        spawners.add(spawner);
    }

    public List<IGameObject> getGameObjects() {
        updateGameObjectsList();
        return gameObjects;
    }

    private void updateGameObjectsList() {
        // This avoids concurrent modification by only changing gameObjects list between each renders
        boolean isModified = false;
        if (!gameObjectsToAdd.isEmpty()) {
            gameObjects.addAll(gameObjectsToAdd);
            gameObjectsToAdd.clear();
            isModified = true;
        }
        if (!gameObjectsToRemove.isEmpty()) {
            gameObjects.removeAll(gameObjectsToRemove);
            gameObjectsToRemove.clear();
            isModified = true;
        }
        if (isModified) {
            sortGameObjects();
        }
    }

    public void addGameObject(IGameObject gameObject) {
        this.gameObjectsToAdd.add(gameObject);
    }

    public void removeGameObject(IGameObject gameObject) {
        this.gameObjectsToRemove.add(gameObject);
    }

    private void sortGameObjects() {
        gameObjects.sort(new Comparator<IGameObject>() {
            @Override
            public int compare(IGameObject o1, IGameObject o2) {
                return o1.getZ() - o2.getZ();
            }
        });
    }

    public ArrayList<Entity> getEntities(EntityType type) {
        ArrayList<Entity> e = new ArrayList<>();
        for (IGameObject gameObject : gameObjects) {
            if (gameObject instanceof Entity entity) {
                if (entity.getType() == type) e.add(entity);
            }
        }
        return e;
    }

    public boolean isColliding(Entity a, Entity b) {
        return a.getColliderX() < b.getColliderX() + b.getColliderWidth() &&
                a.getColliderX() + a.getColliderWidth() > b.getColliderX() &&
                a.getColliderY() < b.getColliderY() + b.getColliderHeight() &&
                a.getColliderY() + a.getColliderHeight() > b.getColliderY();
    }

    public IPlayer getPlayer() {
        return this.player;
    }

    public void setPlayer(IPlayer player) {
        this.player = player;
    }

    public IWeapon getWeapon() {
        return this.weapon;
    }

    public void setWeapon(IWeapon weapon) {
        this.weapon = weapon;
    }

    public void addScore(int amount) {
        scoringClient.addScore(amount);
    }
}

package com.asteroids.common.data;

import com.asteroids.common.gameObjects.Entity;
import com.asteroids.common.gameObjects.EntityType;
import com.asteroids.common.gameObjects.IGameObject;
import com.asteroids.common.gameObjects.IPlayer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class World {
    private final double width;
    private final double height;
    private final List<IGameObject> gameObjects = new ArrayList<>();
    private final List<IGameObject> gameObjectsToAdd = new ArrayList<>();
    private final List<IGameObject> gameObjectsToRemove = new ArrayList<>();
    private IEnemyFactory enemyFactory;
    private IPlayer player;

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

    public int getEnemiesAmount() {
        return getEntities(EntityType.ENEMY).size();
    }

    public boolean isColliding(Entity a, Entity b) {
        return a.getX() < b.getX() + b.getColliderWidth() &&
                a.getX() + a.getColliderWidth() > b.getX() &&
                a.getY() < b.getY() + b.getColliderHeight() &&
                a.getY() + a.getColliderHeight() > b.getY();
    }

    public IPlayer getPlayer() {
        return this.player;
    }

    public void setPlayer(IPlayer player) {
        this.player = player;
    }

    public IEnemyFactory getEnemyFactory() {
        return this.enemyFactory;
    }

    public void setEnemyFactory(IEnemyFactory enemyFactory) {
        this.enemyFactory = enemyFactory;
    }
}

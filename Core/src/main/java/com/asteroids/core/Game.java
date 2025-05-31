package com.asteroids.core;

import com.asteroids.common.data.GameData;
import com.asteroids.common.data.World;
import com.asteroids.common.gameObjects.IGameObject;
import com.asteroids.common.spawner.ISpawner;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;

import static com.asteroids.core.ServiceLoaders.pluginServiceList;

public class Game {
    private final Scene scene;
    private final GraphicsContext gc;
    private final GameData gameData = new GameData(false);
    private final World world;

    Game(Scene scene, GraphicsContext gc, int width, int height) {
        this.scene = scene;
        this.gc = gc;
        this.world = new World(width, height);
    }

    public void onStart() {
        scene.setCursor(Cursor.CROSSHAIR);

        scene.setOnKeyPressed(keyEvent -> gameData.addKey(keyEvent.getCode().toString()));
        scene.setOnKeyReleased(keyEvent -> gameData.removeKey(keyEvent.getCode().toString()));

        scene.setOnMouseMoved(mouseEvent -> {
            gameData.setMouseX(mouseEvent.getX());
            gameData.setMouseY(mouseEvent.getY());
        });
        scene.setOnMouseDragged(mouseEvent -> {
            gameData.setMouseX(mouseEvent.getX());
            gameData.setMouseY(mouseEvent.getY());
        });

        scene.setOnMousePressed(_ -> gameData.setMousePressed(true));
        scene.setOnMouseReleased(_ -> gameData.setMousePressed(false));

        // Start IPluginService implementations
        pluginServiceList().forEach(plugin -> plugin.start(world));
    }

    public void render() {
        // Update and draw all gameobjects
        for (IGameObject gameObject : world.getGameObjects()) {
            gameObject.update(gameData, world);
            gameObject.draw(gameData, gc, world);
        }

        // Spawn new gameObjects with spawners (asteroids, enemies)
        for (ISpawner spawner : world.getSpawners()) {
            spawner.spawn(world);
        }

        gameData.increaseFrame(); // used in animation
    }
}

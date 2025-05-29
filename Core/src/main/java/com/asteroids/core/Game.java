package com.asteroids.core;

import com.asteroids.common.data.GameData;
import com.asteroids.common.data.World;
import com.asteroids.common.services.IStartService;
import com.asteroids.common.services.IRenderService;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;

import java.util.List;
import static com.asteroids.core.ServiceLoaders.startServiceList;

public class Game {
    private final Scene scene;
    private final GraphicsContext gc;
    private final GameData gameData = new GameData(false);
    private final World world;

    private final List<IStartService> startServices;

    Game(Scene scene, GraphicsContext gc, int width, int height) {
        this.scene = scene;
        this.gc = gc;
        this.world = new World(width, height);
        startServices = startServiceList();
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

        // Start IStartService implementations
        startServices.forEach(startService -> startService.start(world));
    }

    public void render() {
        for (IStartService startService : startServices) {
            if (startService instanceof IRenderService renderService) {
                renderService.update(world);
                renderService.draw(gameData, gc, world);
            }
        }
        gameData.increaseFrame();
    }
}

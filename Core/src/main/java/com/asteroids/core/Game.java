package com.asteroids.core;

import com.asteroids.common.data.GameData;
import com.asteroids.common.data.World;
import com.asteroids.common.gameObjects.IGameObject;
import com.asteroids.common.services.IPluginService;
import com.asteroids.common.spawner.ISpawner;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.List;

public class Game {
    private final int width = 1280;
    private final int height = 720;

    private final GameData gameData = new GameData(false);
    private final World world = new World(width, height);

    private final Canvas canvas = new Canvas(width, height);
    private final GraphicsContext gc = canvas.getGraphicsContext2D();
    private final StackPane root = new StackPane(canvas);
    private final Scene scene = new Scene(root, width, height);
    private final Timeline tl = new Timeline();

    private final List<IPluginService> pluginServiceList;

    Game(List<IPluginService> pluginServiceList) {
        this.pluginServiceList = pluginServiceList;
    }

    public void onStart(Stage stage) {
        configureIO();
        configureStage(stage);
        configureTimeline();

        // Start IPluginService implementations
        pluginServiceList.forEach(plugin -> plugin.start(world));

        // Start game
        stage.show();
        tl.play();
    }

    private void configureStage(Stage stage) {
        // Stage setting and set scene
        gc.setImageSmoothing(false); // ruins the pixelart when scaled
        scene.setCursor(Cursor.CROSSHAIR);
        stage.setScene(scene);
        stage.setResizable(false);
    }

    private void configureTimeline() {
        // Use infinite timeline for rendering
        KeyFrame kf = new KeyFrame(Duration.millis( (double) 1000 / 144), _ -> render()); // 144 fps
        tl.getKeyFrames().add(kf);
        tl.setCycleCount(Timeline.INDEFINITE);
    }

    private void configureIO() {
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
    }

    private void render() {
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

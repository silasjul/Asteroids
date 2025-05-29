package com.asteroids.core;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
    private final int width = 1280;
    private final int height = 720;

    private final Canvas canvas = new Canvas(width, height);
    private final GraphicsContext gc = canvas.getGraphicsContext2D();
    private final StackPane root = new StackPane(canvas);
    private final Scene scene = new Scene(root, width, height);
    private final Timeline tl = new Timeline();

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        Game game = new Game(scene, gc, width, height);
        game.onStart();

        // Stage setting and set scene
        gc.setImageSmoothing(false); // ruins the pixelart when scaled
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        // Use infinite timeline for rendering
        KeyFrame kf = new KeyFrame(Duration.millis( (double) 1000 / 144), _ -> game.render()); // 144 fps
        tl.getKeyFrames().add(kf);
        tl.setCycleCount(Timeline.INDEFINITE);
        tl.play();
    }
}

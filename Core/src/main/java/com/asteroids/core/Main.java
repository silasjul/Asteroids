package com.asteroids.core;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main extends Application {
    public static void main(String[] args) {launch();}

    @Override
    public void start(Stage stage) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ModuleConfig.class);
        Game game = ctx.getBean(Game.class);
        game.onStart(stage);
    }
}

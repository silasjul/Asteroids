package com.asteroids.background;

import com.asteroids.common.data.GameData;
import com.asteroids.common.data.World;
import com.asteroids.common.services.IRenderService;
import com.asteroids.common.services.IStartService;
import com.asteroids.common.sprites.Parallax;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Background implements IRenderService, IStartService {
    private int planetScale = 5;
    private final Parallax planet = new Parallax("/background/planet.png", 96, 96, 1);
    private Parallax[] bg;

    @Override
    public void update(World world) {
        for (Parallax layer : bg) {
            layer.move();
            if (layer.getOffsetY() >= world.getHeight()) layer.setOffsetY(0);
        }

        // Random planet fly-by
        planet.move();
        if (planet.getOffsetY() > world.getHeight()) {
            planet.setOffsetX(Math.random()*(world.getWidth()-planet.getWidth()*planetScale));
            planetScale = (int) (Math.random() * 10);
            planet.setOffsetY(-planet.getHeight()*planetScale-800);
        }
    }

    @Override
    public void draw(GameData gameData, GraphicsContext gc, World world) {
        for (Parallax parallax : bg) {
            if (gameData.isAnimationFrame()) {
                parallax.next(); // point to next image
                planet.next();
            }

            Image img = parallax.getCurrentImage();
            // Draw the background twice. once regular and one offset making it go infinitely
            gc.drawImage(img, 0, parallax.getOffsetY(), world.getWidth(), world.getHeight());
            gc.drawImage(img, 0, parallax.getOffsetY() - world.getHeight(), world.getWidth(), world.getHeight());
        }
        gc.drawImage(planet.getCurrentImage(), planet.getOffsetX(), planet.getOffsetY(), planet.getWidth()*planetScale, planet.getHeight()*planetScale);
    }

    @Override
    public void start(World world) {
        System.out.println("Starting Background");
        int w = (int) world.getWidth()/2;
        int h = (int) world.getHeight()/2;

        bg = new Parallax[] {
                new Parallax("/background/void.png", w, h, 0.2),
                new Parallax("/background/stars.png", w, h, 0.35),
                new Parallax("/background/bigStarts.png", w, h, 0.5),
        };

        planet.setOffsetY(-planet.getHeight()*planetScale-500);
        planet.setOffsetX(Math.random()*(w));
    }

    @Override
    public void stop(World world) {

    }
}

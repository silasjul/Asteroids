package com.asteroids.common.sprites;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

/**
 * Takes a spritesheet and divides it into images and store them in a list
 *
 */
public class Sprite {
    private BufferedImage spriteSheet;
    private BufferedImage[] sprites;
    private Image[] fxSprites;
    private int width;
    private int height;
    private double scale;
    private int amount = 0;
    private double rotationRad;
    private int current = 0;
    private int animationCount = 0;

    public Sprite(String imagePath, int width, int height, double scale, double rotationRad) {
        this.width = width;
        this.height = height;
        this.scale = scale;
        this.rotationRad = rotationRad;

        try {
            spriteSheet = ImageIO.read(this.getClass().getResourceAsStream(imagePath)); // This tanks performance ://
        } catch (IOException e) {
            throw new RuntimeException("image is null");
        }

        this.amount = spriteSheet.getWidth() / width;
        createSubImages();
    }

    public Sprite(String imagePath, int width, int height, double scale) {
        this(imagePath, width, height, scale, 0);
        createSubImages();
    }

    public void createSubImages() {
        fxSprites = new Image[amount];
        sprites = new BufferedImage[amount];

        for (int i = 0; i < amount; i++) {
            BufferedImage newImage = spriteSheet.getSubimage(i*width, 0, width, height);

            if (this.rotationRad != 0) {
                newImage = scaleImage(newImage, this.scale);
                newImage = rotateImage(newImage, this.rotationRad);
            }

            this.sprites[i] = newImage;

            this.fxSprites[i] = SwingFXUtils.toFXImage(newImage, null); // Converts BufferedImage to a JavaFX Image
        }
    }

    public Image getSubImage(int i, double rotationRad) {
        BufferedImage newImage = spriteSheet.getSubimage(i*this.width, 0, this.width, this.height);
        newImage = scaleImage(newImage, this.scale);
        newImage = rotateImage(newImage, rotationRad);
        return SwingFXUtils.toFXImage(newImage, null);
    }

    public Image getImage(int i) {
        return this.fxSprites[i];
    }
    public Image getCurrentImage() {return getImage(this.current);}

    public BufferedImage scaleImage(BufferedImage img, double scale) {
        int w = (int) (img.getWidth()*scale);
        int h = (int) (img.getHeight()*scale);

        BufferedImage scaledImage = new BufferedImage(w, h, img.getType());
        Graphics2D g2d = scaledImage.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        g2d.drawImage(img, 0, 0, w, h, null);
        g2d.dispose();

        return scaledImage;
    }

    public BufferedImage rotateImage(BufferedImage img, double angle) {
        int w = img.getWidth();
        int h = img.getHeight();

        // Create a new transparent image
        BufferedImage rotatedImage = new BufferedImage(w, h, img.getType());

        Graphics2D g2d = rotatedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);

        // Apply transformation
        AffineTransform at = new AffineTransform();
        at.rotate(angle, w / 2.0, h / 2.0);  // Rotate around center (correcting rotation with PI)
        g2d.setTransform(at);

        // Draw the original image onto the rotated transformation
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();

        return rotatedImage;
    }

    public int getWidth() {return width;}
    public int getHeight() {return height;}
    public int getAmount()  {return this.amount;}
    public int getCurrent() {return this.current;}

    public void next() {
        current++;
        if (current >= amount) {
            current = 0;
            animationCount++;
        }
    }
    public int getAnimationCount() {return animationCount;}

}

package com.asteroids.common.sprites;

/**
 * Used to create the background where the images are chopped into layers and moved at different speeds creating a cool effect
 *
 */
public class Parallax extends Sprite {

    private final double speed;
    private double offsetY = 0;
    private double offsetX = 0;

    public Parallax(String imagePath, int width, int height, double speed) {
        super(imagePath, width, height);
        this.speed = speed;
    }

    public double getOffsetY() {return this.offsetY;}
    public double getOffsetX() {return this.offsetX;}
    public void move() {this.offsetY += this.speed;}

    public void setOffsetX(double offsetX) {
        this.offsetX = offsetX;
    }
    public void setOffsetY(double offsetY) {
        this.offsetY = offsetY;
    }
}

package com.asteroids.common.gameObjects;

public interface IPlayer {
    int getDmg();
    double getCenterX();
    double getCenterY();
    double getY();
    double getX();
    void takeDmg(int dmg);
    boolean isAlive();
}

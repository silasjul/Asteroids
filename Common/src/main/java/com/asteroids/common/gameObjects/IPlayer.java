package com.asteroids.common.gameObjects;

public interface IPlayer {
    int getDmg();
    double getCenterX();
    double getCenterY();
    void takeDmg(int dmg);
}

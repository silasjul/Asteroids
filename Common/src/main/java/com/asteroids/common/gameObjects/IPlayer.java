package com.asteroids.common.gameObjects;

import com.asteroids.common.data.World;

public interface IPlayer {
    double getCenterX();
    double getCenterY();
    double getY();
    double getX();
    void takeDmg(int dmg, World world);
    boolean isAlive();
}

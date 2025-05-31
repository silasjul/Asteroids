package com.asteroids.common.gameObjects;

import com.asteroids.common.data.World;

public interface IWeapon {
    void fire(World world, double x, double y, double speed, double angleRad, int damage, BulletType type, EntityType entityType, double scale);
}

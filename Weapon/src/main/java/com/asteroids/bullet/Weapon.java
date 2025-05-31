package com.asteroids.bullet;

import com.asteroids.common.data.World;
import com.asteroids.common.gameObjects.BulletType;
import com.asteroids.common.gameObjects.EntityType;
import com.asteroids.common.gameObjects.IWeapon;

public class Weapon implements IWeapon {

    @Override
    public void fire(World world, double x, double y, double speed, double angleRad, int damage, BulletType type, EntityType entityType, double scale) {
        Bullet bullet =  new Bullet(x,y,speed,angleRad, damage, type, entityType,scale);
        world.addGameObject(bullet);
    }
}

package com.asteroids.bullet;

import com.asteroids.common.data.World;
import com.asteroids.common.services.IPluginService;

public class WeaponProvider implements IPluginService {
    @Override
    public void start(World world) {
        System.out.println("Starting Weapon");
        world.setWeapon(new Weapon());
    }
}

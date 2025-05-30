package com.asteroids.common.services;

import com.asteroids.common.data.World;

public interface IPluginService {
    void start(World world);
    void stop(World world);
}

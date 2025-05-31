package com.asteroids.core;

import com.asteroids.common.gameObjects.IGameObject;
import com.asteroids.common.services.IPluginService;

import java.util.List;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class ServiceLoaders {
    public static List<IPluginService> pluginServiceList() {
        return ServiceLoader.load(IPluginService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    public static List<IGameObject> renderServiceList() {
        return ServiceLoader.load(IGameObject.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}

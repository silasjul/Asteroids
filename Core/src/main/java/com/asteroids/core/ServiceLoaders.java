package com.asteroids.core;

import com.asteroids.common.services.IRenderService;
import com.asteroids.common.services.IStartService;

import java.util.List;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class ServiceLoaders {
    public static List<IStartService> startServiceList() {
        return ServiceLoader.load(IStartService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    public static List<IRenderService> renderServiceList() {
        return ServiceLoader.load(IRenderService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}

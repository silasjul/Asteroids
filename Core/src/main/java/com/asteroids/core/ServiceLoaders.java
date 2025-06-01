package com.asteroids.core;

import com.asteroids.common.services.IPluginService;
import com.asteroids.common.services.IPostProcessingService;

import java.util.List;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class ServiceLoaders {
    /**
     * Loads and returns a list of all available plugin services implementing {@link IPluginService}.
     * <p>
     * This method uses Java's {@link ServiceLoader} to find plugins.
     * </p>
     *
     * @return A list of {@link IPluginService} instances. Returns an empty list if none are found.
     */
    public static List<IPluginService> pluginServiceList() {
        return ServiceLoader.load(IPluginService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    public static List<IPostProcessingService> postProcessingServices() {
        return ServiceLoader.load(IPostProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}

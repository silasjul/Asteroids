package com.asteroids.common.services;

import com.asteroids.common.data.World;

import java.util.ServiceLoader;

public interface IPluginService {
    /**
     * Loads and returns a list of all available plugin services implementing {@link IPluginService}.
     * <p>
     * This method uses Java's {@link ServiceLoader} to find plugins.
     * </p>
     *
     * @return A list of {@link IPluginService} instances. Returns an empty list if none are found.
     */
    void start(World world);
}

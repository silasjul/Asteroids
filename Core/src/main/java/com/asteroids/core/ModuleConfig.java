package com.asteroids.core;

import java.util.List;
import java.util.ServiceLoader;
import static java.util.stream.Collectors.toList;

import com.asteroids.common.services.IPluginService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author jcs
 */
@Configuration
class ModuleConfig {

    public ModuleConfig() {
    }

    @Bean
    public Game game() {
        return new Game(pluginServiceList());
    }

    @Bean
    public List<IPluginService> pluginServiceList(){
        return ServiceLoader.load(IPluginService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
import com.asteroids.asteroid.AsteroidSpawnerProvider;
import com.asteroids.common.services.IPluginService;

module Asteroid {
    requires Common;
    requires javafx.graphics;
    provides IPluginService with AsteroidSpawnerProvider;
}
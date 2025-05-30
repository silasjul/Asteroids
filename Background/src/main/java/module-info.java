import com.asteroids.common.services.IPluginService;

module Background {
    requires Common;
    requires javafx.graphics;
    provides IPluginService with com.asteroids.background.BackgroundPlugin;
}
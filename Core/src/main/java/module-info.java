import com.asteroids.common.services.IGameObject;
import com.asteroids.common.services.IPluginService;

module Core {
    requires Common;
    requires javafx.graphics;
    uses IPluginService;
    uses IGameObject;
    exports com.asteroids.core;
}
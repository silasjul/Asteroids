import com.asteroids.common.gameObjects.IGameObject;
import com.asteroids.common.services.IPluginService;

module Core {
    requires Common;
    requires javafx.graphics;
    uses IPluginService;
    uses IGameObject;
    uses com.asteroids.common.services.IPostProcessingService;
    exports com.asteroids.core;
}
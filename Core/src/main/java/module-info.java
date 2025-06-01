import com.asteroids.common.gameObjects.IGameObject;
import com.asteroids.common.services.IPluginService;

module Core {
    requires Common;
    requires javafx.graphics;
    requires spring.context;
    uses IPluginService;
    uses IGameObject;
    opens com.asteroids.core to spring.core;
    exports com.asteroids.core;
}
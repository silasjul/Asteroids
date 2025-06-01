import com.asteroids.common.gameObjects.IGameObject;
import com.asteroids.common.services.IPluginService;

module Core {
    requires Common;
    requires spring.context;
    requires javafx.controls;
    uses IPluginService;
    uses IGameObject;
    opens com.asteroids.core to spring.core;
    exports com.asteroids.core;
}
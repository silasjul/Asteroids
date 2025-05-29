import com.asteroids.common.services.IStartService;

module Core {
    requires Common;
    requires javafx.graphics;
    uses IStartService;
    uses com.asteroids.common.services.IRenderService;
    exports com.asteroids.core;
}
module Common {
    requires javafx.graphics;
    requires java.desktop;
    requires javafx.swing;
    exports com.asteroids.common.data;
    exports com.asteroids.common.services;
    exports com.asteroids.common.sprites;
    exports com.asteroids.common.gameObjects;
    exports com.asteroids.common.spawner;
}
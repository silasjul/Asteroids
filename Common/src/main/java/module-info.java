module Common {
    requires java.desktop;
    requires javafx.swing;
    requires java.net.http;
    requires javafx.controls;
    exports com.asteroids.common.data;
    exports com.asteroids.common.services;
    exports com.asteroids.common.sprites;
    exports com.asteroids.common.gameObjects;
    exports com.asteroids.common.spawner;
    exports com.asteroids.common.scoringClient;
}
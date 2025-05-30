import com.asteroids.common.services.IPluginService;

module Player {
    requires Bullet;
    requires Common;
    requires javafx.graphics;
    exports com.asteroids.player;
    provides IPluginService with com.asteroids.player.PlayerPlugin;
}
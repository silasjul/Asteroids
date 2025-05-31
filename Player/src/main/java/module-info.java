import com.asteroids.common.services.IPluginService;
import com.asteroids.player.PlayerPlugin;

module Player {
    requires Common;
    requires javafx.graphics;
    exports com.asteroids.player;
    provides IPluginService with PlayerPlugin;
}
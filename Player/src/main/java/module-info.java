import com.asteroids.common.services.IStartService;

module Player {
    requires Common;
    requires javafx.graphics;
    exports com.asteroids.player;
    provides IStartService with com.asteroids.player.Player;
}
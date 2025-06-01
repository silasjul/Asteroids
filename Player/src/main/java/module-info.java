import com.asteroids.common.services.IPluginService;
import com.asteroids.common.services.IPostProcessingService;

module Player {
    requires Common;
    requires javafx.graphics;
    provides IPluginService with com.asteroids.player.PlayerPlugin;
    provides IPostProcessingService with com.asteroids.PostProcess;
}
import com.asteroids.common.services.IPluginService;
import com.asteroids.common.services.IPostProcessingService;

module Bullet {
    requires Common;
    requires javafx.graphics;
    provides IPluginService with com.asteroids.bullet.WeaponProvider;
    provides IPostProcessingService with com.asteroids.PostProcess;
}
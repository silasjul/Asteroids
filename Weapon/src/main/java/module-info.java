import com.asteroids.common.services.IPluginService;
import com.asteroids.bullet.WeaponProvider;

module Bullet {
    requires Common;
    requires javafx.graphics;
    exports com.asteroids.bullet;
    provides IPluginService with WeaponProvider;
}
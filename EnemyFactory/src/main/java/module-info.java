import com.asteroids.common.services.IPluginService;

module EnemyFactory {
    requires Common;
    requires javafx.graphics;
    provides IPluginService with com.asteroids.enemyfactory.EnemyFactoryProvider;
}
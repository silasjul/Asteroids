import com.asteroids.common.services.IPluginService;
import com.asteroids.enemyfactory.EnemySpawnerProvider;

module EnemyFactory {
    requires Common;
    requires javafx.graphics;
    provides IPluginService with EnemySpawnerProvider;
}
import com.asteroids.common.services.IRenderService;
import com.asteroids.common.services.IStartService;

module Background {
    requires Common;
    requires javafx.graphics;
    provides IStartService with com.asteroids.background.Background;
    provides IRenderService with com.asteroids.background.Background;
}
package com.asteroids.player.engine;

public class EngineManager {
    private Engine engine;

    public Engine getEngine(EngineType type, double playerScale) {
        if (EngineType.BASE == type) {
            this.engine = new Engine("/player/engines/base", playerScale);
        }
        else if (EngineType.CHARGED == type) {
            this.engine = new Engine("/player/engines/charged", playerScale);
        }
        else if (EngineType.PULSE == type) {
            this.engine = new Engine("/player/engines/pulse", playerScale);
        }
        else if (EngineType.BURST == type) {
            this.engine = new Engine("/player/engines/burst", playerScale);
        }

        return this.engine;
    }
}

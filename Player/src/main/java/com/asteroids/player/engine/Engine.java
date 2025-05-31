package com.asteroids.player.engine;

import com.asteroids.common.data.GameData;
import com.asteroids.common.sprites.Sprite;

import java.util.HashMap;
import java.util.Map;

public class Engine {
    private String enginePath;
    private int spriteLength;
    private int current = 0;
    private Map<String, Sprite> sprites = new HashMap<>();

    public Engine(String enginePath, double playerScale) {
        this.enginePath = enginePath;

        // Ships
        sprites.put("base", new Sprite(enginePath+"/base.png", 48, 48, playerScale));
        sprites.put("idle", new Sprite(enginePath+"/idle.png", 48, 48, playerScale));
        sprites.put("powering", new Sprite(enginePath+"/powering.png", 48, 48, playerScale));
    }

    public Sprite getBase(double playerRotation) {
        return sprites.get("base");
    }

    public Sprite getIdle(double playerRotation) {
        Sprite idle = sprites.get("idle");
        spriteLength = idle.getAmount();
        return idle;
    }

    public Sprite getPowering(double playerRotation) {
        Sprite powering = sprites.get("powering");
        spriteLength = powering.getAmount();
        return powering;
    }

    public int getCurrent(GameData gameData) {
        if (current >= spriteLength-1) current = 0;
        int temp = current;
        if (gameData.isAnimationFrame()) current++;
        return temp;
    }
}

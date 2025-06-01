package com.asteroids;

import com.asteroids.common.services.IPostProcessingService;

public class PostProcess implements IPostProcessingService {
    @Override
    public void postProcess() {
        System.out.println("postProcess from Weapon");
    }
}

package com.asteroids.scoringservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ScoringServiceApplication {

    private Long totalScore = 0L;

    public static void main(String[] args) {
        SpringApplication.run(ScoringServiceApplication.class, args);
    }

    @GetMapping("/score")
    public Long addPointsToScore(@RequestParam(value = "point") Long point) {
        totalScore += point;
        return totalScore ;
    }
}

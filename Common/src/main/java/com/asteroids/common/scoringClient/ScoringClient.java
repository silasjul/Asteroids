package com.asteroids.common.scoringClient;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import javafx.scene.control.Label;

public class ScoringClient {
    private HttpClient client = HttpClient.newHttpClient();
    private Label scoreLabel;

    public ScoringClient(Label scoreLabel) {
        this.scoreLabel = scoreLabel;
    }

    public void addScore(int amount) {
        String apiUrl = "http://localhost:8080/score?point=" + amount;

        try {
            // Build request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .GET()
                    .build();

            // Send request
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Handle Response
            scoreLabel.setText("Score: " + response.body());
            return;
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
        scoreLabel.setText("Score: " + -1);
    }

    public static void main(String[] args) {
        ScoringClient scoringClient = new ScoringClient(null);

        scoringClient.addScore(10);
    }
}

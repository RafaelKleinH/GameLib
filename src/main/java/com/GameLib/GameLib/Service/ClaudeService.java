package com.GameLib.GameLib.Service;

import com.GameLib.GameLib.Model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Service
public class ClaudeService {

    private final WebClient webClient;
    
    @Value("${claude.api.key}")
    private String apiKey;
    
    @Value("${claude.api.url}")
    private String apiUrl;

    public ClaudeService() {
        this.webClient = WebClient.builder().build();
    }

    public Mono<String> generateGameDescription(GameModel game) {
        String prompt = createDescriptionPrompt(game);
        
        ClaudeMessage message = new ClaudeMessage("user", prompt);
        List<ClaudeMessage> messages = Arrays.asList(message);
        
        ClaudeRequest request = new ClaudeRequest("claude-3-haiku-20240307", 200, messages);
        
        return webClient.post()
                .uri(apiUrl)
                .header("Content-Type", "application/json")
                .header("x-api-key", apiKey)
                .header("anthropic-version", "2023-06-01")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(ClaudeResponse.class)
                .map(this::extractTextFromResponse)
                .onErrorReturn("Unable to generate description at this time.");
    }

    public Mono<String> generateGameStoryResume(GameModel game) {
        String prompt = createStoryResumePrompt(game);

        ClaudeMessage message = new ClaudeMessage("user", prompt);
        List<ClaudeMessage> messages = Arrays.asList(message);

        ClaudeRequest request = new ClaudeRequest("claude-3-haiku-20240307", 200, messages);

        return webClient.post()
                .uri(apiUrl)
                .header("Content-Type", "application/json")
                .header("x-api-key", apiKey)
                .header("anthropic-version", "2023-06-01")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(ClaudeResponse.class)
                .map(this::extractTextFromResponse)
                .onErrorReturn("Unable to generate description at this time.");
    }

    private String createDescriptionPrompt(GameModel game) {
        StringBuilder promptBuilder = new StringBuilder();
        promptBuilder.append("Generate a brief, engaging description for this video game:\n\n");
        promptBuilder.append("Game Name: ").append(game.getName()).append("\n");
        
        promptBuilder.append("\nPlease write a 2-3 sentence description that would help a user understand what this game is about. ");
        promptBuilder.append("Focus on the genre, gameplay style, and what makes it interesting. ");
        promptBuilder.append("Keep it concise and engaging.");
        promptBuilder.append("But without any spoilers.");
        
        return promptBuilder.toString();
    }

    private String createStoryResumePrompt(GameModel game) {
        StringBuilder promptBuilder = new StringBuilder();
        promptBuilder.append("Generate a resume for this video game history:\n\n");
        promptBuilder.append("Game Name: ").append(game.getName()).append("\n");
        promptBuilder.append("Spoilers are allowed and necessary.");
        promptBuilder.append("But going through all the important points of the narrative.");

        return promptBuilder.toString();
    }

    private String extractTextFromResponse(ClaudeResponse response) {
        if (response != null && 
            response.getContent() != null && 
            !response.getContent().isEmpty() &&
            response.getContent().get(0).getText() != null) {
            return response.getContent().get(0).getText().trim();
        }
        return "No description available.";
    }
}
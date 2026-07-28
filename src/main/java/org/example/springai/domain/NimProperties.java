package org.example.springai.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.ai.nim")
public record NimProperties(
        String apiKey,
        String baseUrl,
//        String model
        Chat chat
) {
    public record Chat(String model) {
    }
}
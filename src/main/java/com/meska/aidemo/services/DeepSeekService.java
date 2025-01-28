package com.meska.aidemo.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meska.aidemo.dto.DeepSeekRequest;
import com.meska.aidemo.dto.DeepSeekResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class DeepSeekService {
    private final WebClient webClient;

    public DeepSeekService(WebClient deepSeekWebClient){
        this.webClient = deepSeekWebClient;
    }


    public Mono<String> askQuestion(String question) {
        DeepSeekRequest request = createRequest(question);

        // Log the request payload
        try {
            String jsonPayload = new ObjectMapper().writeValueAsString(request);
            System.out.println("Request Payload: " + jsonPayload);
        } catch (Exception e) {
            System.err.println("Error logging request payload: " + e.getMessage());
        }

        return webClient.post()
                .bodyValue(request)
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError() || status.is5xxServerError(),
                        response -> response.bodyToMono(String.class)
                                .flatMap(errorBody -> Mono.error(new RuntimeException("API Error: " + errorBody)))
                )
                .bodyToMono(DeepSeekResponse.class)
                .map(response -> response.getChoices().get(0).getMessage().getContent());
    }

    private DeepSeekRequest createRequest(String question){
        DeepSeekRequest request = new DeepSeekRequest();
        request.setModel("deepseek-chat");
        request.setTemperature(0.8);

        DeepSeekRequest.Message message = new DeepSeekRequest.Message();
        message.setRole("user");
        message.setContent(question);

        request.setMessages(List.of(message));
        return request;
    }
}

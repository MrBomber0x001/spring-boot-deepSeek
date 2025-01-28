package com.meska.aidemo.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Value("${deepseek.api.key}")
    private String apiKey;

    @Value("${deepseek.api.url}")
    private String apiUrl;


    @Bean
    public WebClient deepSeekWebClient(){
        return WebClient.builder().baseUrl(apiUrl).defaultHeader("Authorization", "Bearer " + apiKey).defaultHeader("Content-Type", "application/json").build();
    }
}

package com.meska.aidemo.dto;
import lombok.Data;
import java.util.List;

@Data
public class DeepSeekRequest {
    private String model;
    private List<Message> messages;
    private double temperature;

    @Data
    public static class Message {
        private String role;
        private String content;
    }
}

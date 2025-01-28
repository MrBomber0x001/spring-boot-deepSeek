package com.meska.aidemo.controllers;

import com.meska.aidemo.dto.QuestionRequest;
import com.meska.aidemo.services.DeepSeekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class DeepSeekController {
    private final DeepSeekService deepSeekService;

    public DeepSeekController(DeepSeekService deepSeekService){
        this.deepSeekService = deepSeekService;
    }

    @PostMapping("/ask")
    public Mono<ResponseEntity<String>> askQuestion(@RequestBody QuestionRequest request) {
        return deepSeekService.askQuestion(request.getQuestion())
                .map(answer -> ResponseEntity.ok().body(answer))
                .onErrorResume(e -> Mono.just(
                        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body("Error: " + e.getMessage())
                ));
    }
}

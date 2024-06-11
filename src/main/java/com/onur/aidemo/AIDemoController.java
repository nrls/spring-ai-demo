package com.onur.aidemo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AIDemoController {

    private final AIService AIService;

    public AIDemoController(AIService AIService) {
        this.AIService = AIService;
    }

    @PostMapping("api/ai/generateGeneral")
    public ResponseEntity<GenerateGeneralResponse> generateGeneral(@RequestBody GenerateGeneralRequest generateGeneralRequest) {
        System.out.println("HIT: api/v1/ai/generate");
        final GenerateGeneralResponse aiResponse = AIService.generateGeneralMessage(generateGeneralRequest.getPromptMessage());
        return ResponseEntity.status(HttpStatus.OK).body(aiResponse);
    }

    @PostMapping("api/ai/generateJoke")
    public ResponseEntity<GenerateGeneralResponse> generateJoke(@RequestBody GenerateJokeRequest generateJokeRequest) {
        final GenerateGeneralResponse aiResponse = AIService.generateJoke(generateJokeRequest.getTopic());
        return ResponseEntity.status(HttpStatus.OK).body(aiResponse);
    }
}
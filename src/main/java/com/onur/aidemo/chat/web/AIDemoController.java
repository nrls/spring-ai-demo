package com.onur.aidemo.chat.web;

import com.onur.aidemo.chat.model.FootballerDto;
import com.onur.aidemo.chat.model.FootballerQuestion;
import com.onur.aidemo.chat.model.GenerateGeneralRequest;
import com.onur.aidemo.chat.model.GenerateGeneralResponse;
import com.onur.aidemo.chat.service.AIService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AIDemoController {

    private final AIService aiService;

    public AIDemoController(AIService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("api/ai/generateGeneral")
    public ResponseEntity<GenerateGeneralResponse> generateGeneral(@RequestBody GenerateGeneralRequest generateGeneralRequest) {
        final var aiResponse = aiService.generateGeneralMessage(generateGeneralRequest.getPromptMessage());
        return ResponseEntity.status(HttpStatus.OK).body(aiResponse);
    }

    @PostMapping("api/ai/generateJsonOutput")
    public ResponseEntity<FootballerDto> generateJsonOutput(@RequestBody FootballerQuestion question) {
        final var aiResponse = aiService.generateJsonOutput(question);
        return ResponseEntity.status(HttpStatus.OK).body(aiResponse);
    }
}
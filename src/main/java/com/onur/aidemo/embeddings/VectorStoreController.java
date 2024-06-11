package com.onur.aidemo.embeddings;

import com.onur.aidemo.chat.model.GenerateGeneralRequest;
import com.onur.aidemo.chat.model.GenerateGeneralResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class VectorStoreController {

    final VectorStoreService vectorStoreService;

    @PostMapping("api/ai/chatIngestion")
    public ResponseEntity<GenerateGeneralResponse> chatIngestion(@RequestBody GenerateGeneralRequest question) {
        final var aiResponse = vectorStoreService.generateGeneralMessage(question.getPromptMessage());
        return ResponseEntity.status(HttpStatus.OK).body(aiResponse);
    }
}
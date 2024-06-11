package com.onur.aidemo.chat.service;

import com.onur.aidemo.chat.model.FootballerDto;
import com.onur.aidemo.chat.model.FootballerQuestion;
import com.onur.aidemo.chat.model.GenerateGeneralResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.stereotype.Service;

@Service
public class AIService {

    private final ChatClient chatClient;

    public AIService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public GenerateGeneralResponse generateGeneralMessage(String promptMessage) {
        final String llamaMessage = chatClient.prompt().user(promptMessage).call().content();
        return new GenerateGeneralResponse().setMessage(llamaMessage);
    }

    public FootballerDto generateJsonOutput(FootballerQuestion question) {
        var userPromptTemplate = """
                Tell me name and team of one football player playing in {league} league.
                Consider only the players who play in the {position} position.
                """;

        return chatClient.prompt()
                .user(userSpec -> userSpec
                        .text(userPromptTemplate)
                        .param("league", question.league())
                        .param("position", question.position())
                )
                .options(OllamaOptions.create().withFormat("json"))
                .call()
                .entity(FootballerDto.class);
    }
}

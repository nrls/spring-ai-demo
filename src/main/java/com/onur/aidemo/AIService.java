package com.onur.aidemo;

import org.springframework.ai.chat.client.ChatClient;
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

  public GenerateGeneralResponse generateJoke(String topic) {
    final String llamaMessage = chatClient.prompt().user(String.format("Tell me a joke about %s", topic)).call().content();
    return new GenerateGeneralResponse().setMessage(llamaMessage);
  }

}

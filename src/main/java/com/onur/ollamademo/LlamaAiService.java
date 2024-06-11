package com.onur.ollamademo;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class LlamaAiService {

  private final ChatClient chatClient;

  public LlamaAiService(ChatClient.Builder chatClientBuildert) {
    this.chatClient = chatClientBuildert.build();
  }

  public LlamaResponse generateMessage(String promptMessage) {
    final String llamaMessage = chatClient.prompt().user(promptMessage).call().content();
    return new LlamaResponse().setMessage(llamaMessage);
  }

  public LlamaResponse generateJoke(String topic) {
    final String llamaMessage = chatClient.prompt().user(String.format("Tell me a joke about %s", topic)).call().content();
    return new LlamaResponse().setMessage(llamaMessage);
  }

}

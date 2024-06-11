package com.onur.aidemo.embeddings;

import java.util.List;
import java.util.Map;

import com.onur.aidemo.chat.model.GenerateGeneralResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class VectorStoreService {
    private final Logger logger = LoggerFactory.getLogger(VectorStoreService.class);

    private final ChatClient chatClient;
    private final VectorStore vectorStore;

    public VectorStoreService(ChatClient.Builder chatClientBuilder, EmbeddingModel embeddingModel) {
        this.chatClient = chatClientBuilder.build();
        this.vectorStore = new SimpleVectorStore(embeddingModel);
    }

    record Team(int id, String name, String description) {
    }

    @Bean
    ApplicationRunner applicationRunner() {
        return args -> {
            logger.info("Loading team data into vector store...");

            // create a list of Team objects with id, name and description
            List<Team> teams = List.of(
                    new Team(1, "Billing", "Responsible for visit, billing, and claim rule engine services"),
                    new Team(2, "PACCA", "Everything related to payments, claim and case management"),
                    new Team(3, "MAPPPS", "Responsible for provider, medical products, pricing services"),
                    new Team(4, "POSE", "Everyting about policies, eligibility, and enrollment"),
                    new Team(5, "Health Hub", "Owns the user mobile app, web portal, security and fingerprint services")
            );

            teams.forEach(team -> {
                var document = new Document("name: " + team.name() + ", description: "
                        + team.description(), Map.of("teamId", team.id()));
                vectorStore.add(List.of(document));

            });

            logger.info("Team data loaded into vector store");
        };
    }

    GenerateGeneralResponse generateGeneralMessage(String promptMessage) {
        final var content = chatClient.prompt()
                .advisors(new QuestionAnswerAdvisor(vectorStore, SearchRequest.defaults().withTopK(5)))
                .user(promptMessage).call().content();
        return new GenerateGeneralResponse().setMessage(content);
    }
}
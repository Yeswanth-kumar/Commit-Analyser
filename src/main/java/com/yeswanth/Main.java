package com.yeswanth;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.yeswanth.commitanalyser.*;

import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {

        GitChangeExtractor extractor = new GitChangeExtractor();

        String summary = extractor.getCommitMessage();
        String files = extractor.getModifiedFiles();
        String diff = extractor.getDiff("HEAD~1", "HEAD");

        if (diff == null || diff.isBlank()) {
            System.out.println("No changes detected between branches.");
            return;
        }

        PromptBuilder promptBuilder = new PromptBuilder();
        String prompt = promptBuilder.build(summary, files, diff);

        LLMClient llmClient = new LLMClient();
        String response = llmClient.callOllama(prompt);

        ObjectMapper mapper = new ObjectMapper();

        OllamaResponse ollama = mapper.readValue(response, OllamaResponse.class);

        ImpactResponse result =
                mapper.readValue(ollama.response, ImpactResponse.class);

        mapper.writerWithDefaultPrettyPrinter()
                .writeValue(new File("impact-report.json"), result);

        System.out.println("\nImpact report saved to impact-report.json");
    }
}
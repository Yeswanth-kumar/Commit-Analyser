package com.yeswanth.commitanalyser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yeswanth.commitanalyser.LLM.LLMClient;
import com.yeswanth.commitanalyser.LLM.PromptBuilder;
import com.yeswanth.commitanalyser.git.GitChangeExtractor;
import com.yeswanth.commitanalyser.model.ImpactResponse;
import com.yeswanth.commitanalyser.model.OllamaResponse;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class CommitImpactAnalyzer {

    public void analyse() throws Exception {
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

        String markdown = buildMarkdownReport(result, files);

        Files.writeString(
                Path.of("impact-comment.md"),
                markdown,
                StandardCharsets.UTF_8
        );
    }

    private String buildMarkdownReport(ImpactResponse result, String files) {

        StringBuilder md = new StringBuilder();

        md.append("## 🤖 AI Change Impact Analysis\n\n");
        md.append("**Risk Level:** ").append(result.riskLevel).append("\n\n");

        md.append("### Changed Files\n");
        for (String file : files.split("\n")) {
            if (!file.isBlank()) {
                md.append("- ").append(file).append("\n");
            }
        }

        if (result.contextWarnings != null && !result.contextWarnings.isEmpty()) {
            md.append("\n### Context Warnings\n");
            for (String warning : result.contextWarnings) {
                md.append("- ").append(warning).append("\n");
            }
        }

        md.append("\n### Regression Focus\n");
        for (String item : result.regressionFocus) {
            md.append("- [ ] ").append(item).append("\n");
        }

        md.append("\n### Suggested Tests\n");
        for (String item : result.suggestedTests) {
            md.append("- [ ] ").append(item).append("\n");
        }

        return md.toString();
    }
}

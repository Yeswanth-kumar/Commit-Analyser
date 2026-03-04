package com.yeswanth.commitanalyser;

public class PromptBuilder {

    public String build(String summary, String files, String diff) {

        return """
        You are a backend change impact analysis engine.
        Analyze the following change and respond with structured JSON.
        
        Commit Summary:
        %s
        
        Modified Files:
        %s
        
        Git Diff:
        %s
        
        Return JSON in this format:
        
        {
          "riskLevel": "Low | Medium | High",
          "contextWarnings": ["string"],
          "regressionFocus": ["string"],
          "suggestedTests": ["string"]
        }
        
        Respond with JSON only. No explanations.
        """.formatted(summary, files, diff);
    }
}
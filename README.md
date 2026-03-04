## Commit Impact Analyzer
AI-assisted commit analysis tool that evaluates code changes, identifies potential regression risks, and generates structured review reports using LLM reasoning.
The analyzer inspects Git diffs, builds contextual prompts, and produces risk analysis reports that help developers and reviewers understand the impact of code changes before deployment.

## Overview
Modern repositories often contain large commits that are difficult to review thoroughly.
This tool analyzes code changes and automatically generates:
 - Risk level assessment
 - Context warnings about architectural concerns
 - Areas requiring regression focus
 - Suggested tests for verification
The system integrates with CI pipelines and produces reports.

## Architecture

```
        Git Commit
             │
             ▼
     Git Diff Extraction
             │
             ▼
      Prompt Construction
             │
             ▼
  LLM Analysis (Local Model)
             │
             ▼
  Structured Response Parsing
             │
             ▼
        Generated Reports
        ├─ impact-report.json
        └─ impact-comment.md
```

## Project Structure

```
commitanalyzer
│
├── cli
│   └── Main.java
│       - Command line interface entry point
│
├── git
│   └── GitChangeExtractor.java
│       - Extract commit diff, modified files, and commit message
│
├── llm
│   ├── LLMClient.java
│   └── PromptBuilder.java
│       - Build prompts and interact with the LLM
│
├── model
│   ├── ImpactResponse.java
│   ├── OllamaRequest.java
│   └── OllamaResponse.java
│       - Structured request/response data models
│
├── report
│   ├── JsonReportWriter.java
│   └── MarkdownReportWriter.java
│       - Generate JSON and Markdown reports
│
└── CommitImpactAnalyzer.java
    - Core orchestration engine
```

## Example Output
**Markdown Report**
**AI Change Impact Analysis**

Risk Level: Medium

Context Warnings
- New public methods introduced without tests
- Git process execution without validation

Regression Focus
- Payment validation logic
- Order service integration

Suggested Tests
- Payment failure scenarios
- Order persistence after payment
**JSON**
```
  {
    "riskLevel": "Medium",
    "contextWarnings": [
      "Git commands executed without validation",
      "Platform dependency on git availability"
    ],
    "regressionFocus": [
      "GitChangeExtractor.getDiff()",
      "Main.java output structure"
    ],
    "suggestedTests": [
      "Unit test getModifiedFiles with mocked git output",
      "Integration test Main with real git repo"
    ]
  }
```
## Future Improvements
- Module impact detection based on package structure and file path analysis  
- Historical issue memory to recall previously detected risks for similar code changes
- AI-assisted test selection to automatically run relevant unit and integration tests for modified modules

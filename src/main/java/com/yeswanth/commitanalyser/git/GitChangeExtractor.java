package com.yeswanth.commitanalyser.git;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GitChangeExtractor {

    public String getDiff(String baseBranch, String headBranch) throws Exception {
        ProcessBuilder pb = new ProcessBuilder(
                "git", "diff", baseBranch, headBranch
        );
        pb.redirectErrorStream(true);

        Process process = pb.start();

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream())
        );

        StringBuilder diff = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            diff.append(line).append("\n");
        }

        process.waitFor();

        return diff.toString();
    }

    public String getModifiedFiles() throws Exception {
        ProcessBuilder pb = new ProcessBuilder("git", "diff", "--name-only", "HEAD~1", "HEAD");
        pb.redirectErrorStream(true);

        Process process = pb.start();

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream())
        );

        StringBuilder files = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            files.append(line).append("\n");
        }

        process.waitFor();

        return files.toString();
    }

    public String getCommitMessage() throws Exception {
        ProcessBuilder pb = new ProcessBuilder("git", "log", "-1", "--pretty=%B");
        pb.redirectErrorStream(true);

        Process process = pb.start();

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream())
        );

        StringBuilder message = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            message.append(line).append("\n");
        }

        process.waitFor();

        return message.toString().trim();
    }
}
package com.yeswanth.commitanalyser;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GitChangeExtractor {

    public String getDiff() throws Exception {
        ProcessBuilder pb = new ProcessBuilder("git", "diff", "HEAD~1", "HEAD");
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
}
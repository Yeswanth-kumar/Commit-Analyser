package com.yeswanth;

import com.yeswanth.commitanalyser.GitChangeExtractor;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Commit Analyser started...");

        GitChangeExtractor extractor = new GitChangeExtractor();
        String diff = extractor.getDiff();

        System.out.println("------ GIT DIFF START ------");
        System.out.println(diff);
        System.out.println("------ GIT DIFF END ------");
    }
}
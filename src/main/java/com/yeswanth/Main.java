package com.yeswanth;

import com.yeswanth.commitanalyser.GitChangeExtractor;

public class Main {
    public static void main(String[] args) throws Exception {
        GitChangeExtractor extractor = new GitChangeExtractor();

        String diff = extractor.getDiff();
        String files = extractor.getModifiedFiles();
        String summary = extractor.getCommitMessage();

        System.out.println("----- COMMIT SUMMARY -----");
        System.out.println(summary);

        System.out.println("----- MODIFIED FILES -----");
        System.out.println(files);

        System.out.println("----- DIFF -----");
        System.out.println(diff);
    }
}
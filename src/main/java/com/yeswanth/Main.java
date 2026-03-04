package com.yeswanth;

import com.yeswanth.commitanalyser.*;

public class Main {
    public static void main(String[] args) throws Exception {

        CommitImpactAnalyzer commitAnalyzer = new CommitImpactAnalyzer();

        commitAnalyzer.analyse();
    }
}
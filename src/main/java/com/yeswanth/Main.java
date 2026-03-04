package com.yeswanth;

import com.yeswanth.commitanalyser.*;
import com.yeswanth.store.controller.OrderController;

public class Main {
    public static void main(String[] args) throws Exception {

        OrderController controller = new OrderController();
        controller.createOrder();

        // Impact analyser
        CommitImpactAnalyzer commitAnalyzer = new CommitImpactAnalyzer();
        commitAnalyzer.analyse();
    }
}
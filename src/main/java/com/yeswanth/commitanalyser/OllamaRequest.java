package com.yeswanth.commitanalyser;

public class OllamaRequest {

    public String model;
    public String prompt;
    public boolean stream = false;

    public OllamaRequest(String model, String prompt) {
        this.model = model;
        this.prompt = prompt;
    }
}
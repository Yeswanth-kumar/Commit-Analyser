package com.yeswanth.commitanalyser.model;

import java.util.List;

public class ImpactResponse {

    public String riskLevel;
    public List<String> contextWarnings;
    public List<String> regressionFocus;
    public List<String> suggestedTests;
}

package com.competitors.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SentimentAnalysis {
    private int sentimentValue;
    private String[] targets;
    private String[] targetNames;
    private String content;

    public SentimentAnalysis(int sentimentValue,String[] targets, String content) {
        this.sentimentValue = sentimentValue;
        this.targets = targets;
        this.content = content;
    }
}

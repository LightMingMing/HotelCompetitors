package com.competitors.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TargetSentimentDistribution {
    private int analysisTarget;
    private String analysisTargetName;
    private int praiseNumber;
    private int criticismNumber;
    private int neutralNumber;

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }
}

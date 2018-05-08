package com.competitors.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SentimentDistribution {

    private int praiseNumber;
    private int criticismNumber;
    private int neutralNumber;

    // private int opinionNumber;

    private int nocareNumber;

    private double praiseRate;
    private double criticismRate;
    private double neutralRate;
    private double nocareRate;

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public void computeRate() {
        double count = praiseNumber + criticismNumber + neutralNumber + nocareNumber;
        if (count == 0) return;
        praiseRate = praiseNumber / count;
        criticismRate = criticismNumber / count;
        neutralRate = neutralNumber / count;
        nocareRate = nocareNumber / count;
    }
}

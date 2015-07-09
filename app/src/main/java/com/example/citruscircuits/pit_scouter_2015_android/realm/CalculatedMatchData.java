package com.example.citruscircuits.pit_scouter_2015_android.realm;

import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

/**
 * Created by Citrus Circuits on 10/15/2014.
 */

@RealmClass
public class CalculatedMatchData extends RealmObject {
    private int predictedRedScore;
    private int predictedBlueScore;
    private String bestRedAutoStrategy;
    private String bestBlueAutoStrategy;

    public int getPredictedRedScore() {
        return predictedRedScore;
    }

    public void setPredictedRedScore(int predictedRedScore) {
        this.predictedRedScore = predictedRedScore;
    }

    public int getPredictedBlueScore() {
        return predictedBlueScore;
    }

    public void setPredictedBlueScore(int predictedBlueScore) {
        this.predictedBlueScore = predictedBlueScore;
    }

    public String getBestRedAutoStrategy() {
        return bestRedAutoStrategy;
    }

    public void setBestRedAutoStrategy(String bestRedAutoStrategy) {
        this.bestRedAutoStrategy = bestRedAutoStrategy;
    }

    public String getBestBlueAutoStrategy() {
        return bestBlueAutoStrategy;
    }

    public void setBestBlueAutoStrategy(String bestBlueAutoStrategy) {
        this.bestBlueAutoStrategy = bestBlueAutoStrategy;
    }
}

package com.example.citruscircuits.pit_scouter_2015_android.realm;

import java.lang.String;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

/**
 * Created by Citrus Circuits on 10/15/2014.
 */

@RealmClass
public class Team extends RealmObject {
    private int number;
    private String name;
    private int seed;
    private RealmList<TeamInMatchData> matchData;
    private CalculatedTeamData calculatedData;
    private UploadedTeamData uploadedData;


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setSeed(int seed) {
        this.seed = seed;
    }

    public RealmList<TeamInMatchData> getMatchData() {
        return matchData;
    }

    public void setMatchData(RealmList<TeamInMatchData> matchData) {
        this.matchData = matchData;
    }

    public CalculatedTeamData getCalculatedData() {
        return calculatedData;
    }

    public void setCalculatedData(CalculatedTeamData calculatedData) {
        this.calculatedData = calculatedData;
    }

    public UploadedTeamData getUploadedData() {
        return uploadedData;
    }

    public void setUploadedData(UploadedTeamData uploadedData) {
        this.uploadedData = uploadedData;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeed() {
        return seed;
    }
}
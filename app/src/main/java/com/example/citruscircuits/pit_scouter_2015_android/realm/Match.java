package com.example.citruscircuits.pit_scouter_2015_android.realm;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

/**
 * Created by Citrus Circuits on 10/15/2014.
 */

@RealmClass
public class Match extends RealmObject {
    private String match;
    private RealmList<Team> redTeams;
    private RealmList<Team> blueTeams;
    private RealmList<TeamInMatchData> teamInMatchDatas;
    private int officialRedScore;
    private int officialBlueScore;
    private CalculatedMatchData calculatedData;

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    public RealmList<Team> getRedTeams() {
        return redTeams;
    }

    public void setRedTeams(RealmList<Team> redTeams) {
        this.redTeams = redTeams;
    }

    public RealmList<Team> getBlueTeams() {
        return blueTeams;
    }

    public void setBlueTeams(RealmList<Team> blueTeams) {
        this.blueTeams = blueTeams;
    }

    public RealmList<TeamInMatchData> getTeamInMatchDatas() {
        return teamInMatchDatas;
    }

    public void setTeamInMatchDatas(RealmList<TeamInMatchData> teamInMatchDatas) {
        this.teamInMatchDatas = teamInMatchDatas;
    }

    public int getOfficialRedScore() {
        return officialRedScore;
    }

    public void setOfficialRedScore(int officialRedScore) {
        this.officialRedScore = officialRedScore;
    }

    public int getOfficialBlueScore() {
        return officialBlueScore;
    }

    public void setOfficialBlueScore(int officialBlueScore) {
        this.officialBlueScore = officialBlueScore;
    }

    public CalculatedMatchData getCalculatedData() {
        return calculatedData;
    }

    public void setCalculatedData(CalculatedMatchData calculatedData) {
        this.calculatedData = calculatedData;
    }
}

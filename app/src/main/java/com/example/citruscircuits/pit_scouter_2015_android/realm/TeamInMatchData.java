package com.example.citruscircuits.pit_scouter_2015_android.realm;

import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

/**
 * Created by Citrus Circuits on 10/15/2014.
 */

@RealmClass
public class TeamInMatchData extends RealmObject {
    private Team team;
    private Match match;
    private UploadedTeamInMatchData uploadedData;
    private CalculatedTeamInMatchData calculatedData;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public UploadedTeamInMatchData getUploadedData() {
        return uploadedData;
    }

    public void setUploadedData(UploadedTeamInMatchData uploadedData) {
        this.uploadedData = uploadedData;
    }

    public CalculatedTeamInMatchData getCalculatedData() {
        return calculatedData;
    }

    public void setCalculatedData(CalculatedTeamInMatchData calculatedData) {
        this.calculatedData = calculatedData;
    }
}

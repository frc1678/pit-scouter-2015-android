package com.example.citruscircuits.pit_scouter_2015_android.realm;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

/**
 * Created by citruscircuits on 10/23/14.
 */

@RealmClass
public class Competition extends RealmObject {
    private String name;
    private String competitionCode;
    private RealmList<Match> matches;
    private RealmList<Team> attendingTeams;
    private CalculatedCompetitionData calculatedData;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmList<Match> getMatches() {
        return matches;
    }

    public void setMatches(RealmList<Match> matches) {
        this.matches = matches;
    }

    public RealmList<Team> getAttendingTeams() {
        return attendingTeams;
    }

    public void setAttendingTeams(RealmList<Team> attendingTeams) {
        this.attendingTeams = attendingTeams;
    }

    public CalculatedCompetitionData getCalculatedData() {
        return calculatedData;
    }

    public void setCalculatedData(CalculatedCompetitionData calculatedData) {
        this.calculatedData = calculatedData;
    }

    public String getCompetitionCode() {
        return competitionCode;
    }

    public void setCompetitionCode(String competitionCode) {
        this.competitionCode = competitionCode;
    }
}

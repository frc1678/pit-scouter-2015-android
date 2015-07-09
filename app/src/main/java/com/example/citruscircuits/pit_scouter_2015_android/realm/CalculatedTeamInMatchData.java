package com.example.citruscircuits.pit_scouter_2015_android.realm;

import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

/**
 * Created by Citrus Circuits on 10/15/2014.
 */

@RealmClass
public class CalculatedTeamInMatchData extends RealmObject {
    private int numReconsPickedUp;

    public int getNumReconsPickedUp() {
        return numReconsPickedUp;
    }

    public void setNumReconsPickedUp(int numReconsPickedUp) {
        this.numReconsPickedUp = numReconsPickedUp;
    }
}

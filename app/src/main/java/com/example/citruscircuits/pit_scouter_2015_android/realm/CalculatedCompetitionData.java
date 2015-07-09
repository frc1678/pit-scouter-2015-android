package com.example.citruscircuits.pit_scouter_2015_android.realm;

import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

/**
 * Created by Citrus Circuits on 10/15/2014.
 */

@RealmClass
public class CalculatedCompetitionData extends RealmObject {
    private int cachedData;

    public int getCachedData() {
        return cachedData;
    }

    public void setCachedData(int cachedData) {
        this.cachedData = cachedData;
    }
}


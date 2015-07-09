package com.example.citruscircuits.pit_scouter_2015_android.realm;

import io.realm.RealmObject;

/**
 * Created by citruscircuits on 1/19/15.
 */
public class CoopAction extends RealmObject {
    private int uniqueID;
    private int numTotes;
    private boolean onTop;
    private boolean didSucceed;


    public int getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(int uniqueID) {
        this.uniqueID = uniqueID;
    }

    public int getNumTotes() {
        return numTotes;
    }

    public void setNumTotes(int numTotes) {
        this.numTotes = numTotes;
    }

    public boolean isOnTop() {
        return onTop;
    }

    public void setOnTop(boolean onTop) {
        this.onTop = onTop;
    }

    public boolean isDidSucceed() {
        return didSucceed;
    }

    public void setDidSucceed(boolean didSucceed) {
        this.didSucceed = didSucceed;
    }
}

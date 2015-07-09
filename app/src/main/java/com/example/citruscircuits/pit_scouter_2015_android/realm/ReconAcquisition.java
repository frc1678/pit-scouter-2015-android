package com.example.citruscircuits.pit_scouter_2015_android.realm;

import io.realm.RealmObject;

/**
 * Created by citruscircuits on 1/19/15.
 */
public class ReconAcquisition extends RealmObject {
    private int uniqueID;
    private int numReconsAcquired;
    private boolean acquiredMiddle;

    public int getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(int uniqueID) {
        this.uniqueID = uniqueID;
    }

    public int getNumReconsAcquired() {
        return numReconsAcquired;
    }

    public void setNumReconsAcquired(int numReconsAcquired) {
        this.numReconsAcquired = numReconsAcquired;
    }

    public boolean isAcquiredMiddle() {
        return acquiredMiddle;
    }

    public void setAcquiredMiddle(boolean acquiredMiddle) {
        this.acquiredMiddle = acquiredMiddle;
    }
}

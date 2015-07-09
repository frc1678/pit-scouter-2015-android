package com.example.citruscircuits.pit_scouter_2015_android.realm;

import java.lang.String;
import java.lang.reflect.Array;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

/**
 * Created by Citrus Circuits on 10/15/2014.
 */

@RealmClass
public class UploadedTeamData extends RealmObject {
    private String pitOrganization;
    private String programmingLanguage;
    private String pitNotes;
    private boolean canMountMechanism;
    private boolean willingToMount;
    private float easeOfMounting;

    public String getPitOrganization() {
        return pitOrganization;
    }

    public void setPitOrganization(String pitOrganization) {
        this.pitOrganization = pitOrganization;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

    public String getPitNotes() {
        return pitNotes;
    }

    public void setPitNotes(String pitNotes) {
        this.pitNotes = pitNotes;
    }

    public boolean isCanMountMechanism() {
        return canMountMechanism;
    }

    public void setCanMountMechanism(boolean canMountMechanism) {
        this.canMountMechanism = canMountMechanism;
    }

    public boolean isWillingToMount() {
        return willingToMount;
    }

    public void setWillingToMount(boolean willingToMount) {
        this.willingToMount = willingToMount;
    }

    public float getEaseOfMounting() {
        return easeOfMounting;
    }

    public void setEaseOfMounting(float easeOfMounting) {
        this.easeOfMounting = easeOfMounting;
    }
}

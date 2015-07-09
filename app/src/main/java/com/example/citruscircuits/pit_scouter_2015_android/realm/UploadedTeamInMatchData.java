package com.example.citruscircuits.pit_scouter_2015_android.realm;

import java.lang.String;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

/**
 * Created by Citrus Circuits on 10/15/2014.
 */

@RealmClass
public class UploadedTeamInMatchData extends RealmObject {
    private boolean stackedToteSet;
    private int numContainersMovedIntoAutoZone;

    private int numTotesStacked;
    private int numReconLevels;
    private int numNoodlesContributed;
    private int numReconsStacked;
    private int numHorizontalReconsPickedUp;
    private int numVerticalReconsPickedUp;
    private int numTotesPickedUpFromGround;
    private int numLitterDropped;
    private int numStacksDamaged;
    private RealmList<CoopAction> coopActions;
    private int maxFieldToteHeight;
    private int maxReconHeight;
    private int numTeleopReconsFromStep;
    private int numTotesFromHP;

    private RealmList<ReconAcquisition> reconAcquisitions;

    private int agility;
    private int stackPlacing; // From the time they create a stack, how secure does it look
    private int humanPlayerLoading;
    private boolean incapacitated;
    private boolean disabled;
    private String miscellaneousNotes;
    private int numStepReconAcquisitionsFailed;
    private int numSixStacksCapped;

    public boolean isStackedToteSet() {
        return stackedToteSet;
    }

    public void setStackedToteSet(boolean stackedToteSet) {
        this.stackedToteSet = stackedToteSet;
    }

    public int getNumContainersMovedIntoAutoZone() {
        return numContainersMovedIntoAutoZone;
    }

    public void setNumContainersMovedIntoAutoZone(int numContainersMovedIntoAutoZone) {
        this.numContainersMovedIntoAutoZone = numContainersMovedIntoAutoZone;
    }

    public int getNumTotesStacked() {
        return numTotesStacked;
    }

    public void setNumTotesStacked(int numTotesStacked) {
        this.numTotesStacked = numTotesStacked;
    }

    public int getNumReconLevels() {
        return numReconLevels;
    }

    public void setNumReconLevels(int numReconLevels) {
        this.numReconLevels = numReconLevels;
    }

    public int getNumNoodlesContributed() {
        return numNoodlesContributed;
    }

    public void setNumNoodlesContributed(int numNoodlesContributed) {
        this.numNoodlesContributed = numNoodlesContributed;
    }

    public int getNumReconsStacked() {
        return numReconsStacked;
    }

    public void setNumReconsStacked(int numReconsStacked) {
        this.numReconsStacked = numReconsStacked;
    }

    public int getNumHorizontalReconsPickedUp() {
        return numHorizontalReconsPickedUp;
    }

    public void setNumHorizontalReconsPickedUp(int numHorizontalReconsPickedUp) {
        this.numHorizontalReconsPickedUp = numHorizontalReconsPickedUp;
    }

    public int getNumVerticalReconsPickedUp() {
        return numVerticalReconsPickedUp;
    }

    public void setNumVerticalReconsPickedUp(int numVerticalReconsPickedUp) {
        this.numVerticalReconsPickedUp = numVerticalReconsPickedUp;
    }

    public int getNumTotesPickedUpFromGround() {
        return numTotesPickedUpFromGround;
    }

    public void setNumTotesPickedUpFromGround(int numTotesPickedUpFromGround) {
        this.numTotesPickedUpFromGround = numTotesPickedUpFromGround;
    }

    public int getNumLitterDropped() {
        return numLitterDropped;
    }

    public void setNumLitterDropped(int numLitterDropped) {
        this.numLitterDropped = numLitterDropped;
    }

    public int getNumStacksDamaged() {
        return numStacksDamaged;
    }

    public void setNumStacksDamaged(int numStacksDamaged) {
        this.numStacksDamaged = numStacksDamaged;
    }

    public RealmList<CoopAction> getCoopActions() {
        return coopActions;
    }

    public void setCoopActions(RealmList<CoopAction> coopActions) {
        this.coopActions = coopActions;
    }

    public int getMaxFieldToteHeight() {
        return maxFieldToteHeight;
    }

    public void setMaxFieldToteHeight(int maxFieldToteHeight) {
        this.maxFieldToteHeight = maxFieldToteHeight;
    }

    public int getMaxReconHeight() {
        return maxReconHeight;
    }

    public void setMaxReconHeight(int maxReconHeight) {
        this.maxReconHeight = maxReconHeight;
    }

    public int getNumTeleopReconsFromStep() {
        return numTeleopReconsFromStep;
    }

    public void setNumTeleopReconsFromStep(int numTeleopReconsFromStep) {
        this.numTeleopReconsFromStep = numTeleopReconsFromStep;
    }

    public int getNumTotesFromHP() {
        return numTotesFromHP;
    }

    public void setNumTotesFromHP(int numTotesFromHP) {
        this.numTotesFromHP = numTotesFromHP;
    }

    public RealmList<ReconAcquisition> getReconAcquisitions() {
        return reconAcquisitions;
    }

    public void setReconAcquisitions(RealmList<ReconAcquisition> reconAcquisitions) {
        this.reconAcquisitions = reconAcquisitions;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getStackPlacing() {
        return stackPlacing;
    }

    public void setStackPlacing(int stackPlacing) {
        this.stackPlacing = stackPlacing;
    }

    public int getHumanPlayerLoading() {
        return humanPlayerLoading;
    }

    public void setHumanPlayerLoading(int humanPlayerLoading) {
        this.humanPlayerLoading = humanPlayerLoading;
    }

    public boolean isIncapacitated() {
        return incapacitated;
    }

    public void setIncapacitated(boolean incapacitated) {
        this.incapacitated = incapacitated;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getMiscellaneousNotes() {
        return miscellaneousNotes;
    }

    public void setMiscellaneousNotes(String miscellaneousNotes) {
        this.miscellaneousNotes = miscellaneousNotes;
    }

    public int getNumStepReconAcquisitionsFailed() {
        return numStepReconAcquisitionsFailed;
    }

    public void setNumStepReconAcquisitionsFailed(int numStepReconAcquisitionsFailed) {
        this.numStepReconAcquisitionsFailed = numStepReconAcquisitionsFailed;
    }

    public int getNumSixStacksCapped() {
        return numSixStacksCapped;
    }

    public void setNumSixStacksCapped(int numSixStacksCapped) {
        this.numSixStacksCapped = numSixStacksCapped;
    }
}

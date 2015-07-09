package com.example.citruscircuits.pit_scouter_2015_android.realm;

import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

/**
 * Created by Citrus Circuits on 10/15/2014.
 */

@RealmClass
public class CalculatedTeamData extends RealmObject {
    private int predictedSeed;
    private int totalScore;
    private float averageScore;
    private float predictedTotalScore;
    private float predictedAverageScore;
    private float firstPickAbility;
    private float secondPickAbility;
    private float thirdPickAbilityLandfill;
    private float thirdPickAbility;
    private float stackingAbility;
    private float noodleReliability;
    private float avgNumMaxHeightStacks;
    private float reconAbility;
    private float reconReliability;
    private float isRobotMoveIntoAutoZonePercentage;
    private float isStackedToteSetPercentage;
    private float avgNumReconsMovedIntoAutoZone;
    private float avgNumTotesStacked;
    private float avgNumReconLevels;
    private float avgNumNoodlesContributed;
    private float avgNumReconsStacked;
    private float avgNumTotesPickedUpFromGround;
    private float avgNumLitterDropped;
    private float avgNumStacksDamaged;
    private float avgMaxFieldToteHeight;
    private float avgMaxReconHeight;
    private float avgAgility;
    private float driverAbility;
    private float avgStackPlacing;
    private float avgHumanPlayerLoading;
    private float incapacitatedPercentage;
    private float disabledPercentage;
    private float reliability;
    private String reconAcquisitionTypes;
    private String mostCommonReconAcquisitionType;
    private float avgCoopPoints;
    private float stepReconSuccessRateInAuto;
    private float avgStepReconsAcquiredInAuto;
    private float bottomPlacingSuccessRate;
    private float avgNumHorizontalReconsPickedUp;
    private float avgNumVerticalReconsPickedUp;
    private float avgNumReconsPickedUp;
    private float avgNumCappedSixStacks;
    private float avgNumTotesFromHP;
    private float avgNumTeleopReconsFromStep;

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public float getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(float averageScore) {
        this.averageScore = averageScore;
    }

    public float getPredictedTotalScore() {
        return predictedTotalScore;
    }

    public void setPredictedTotalScore(float predictedTotalScore) {
        this.predictedTotalScore = predictedTotalScore;
    }

    public float getPredictedAverageScore() {
        return predictedAverageScore;
    }

    public void setPredictedAverageScore(float predictedAverageScore) {
        this.predictedAverageScore = predictedAverageScore;
    }

    public float getFirstPickAbility() {
        return firstPickAbility;
    }

    public void setFirstPickAbility(float firstPickAbility) {
        this.firstPickAbility = firstPickAbility;
    }

    public float getSecondPickAbility() {
        return secondPickAbility;
    }

    public void setSecondPickAbility(float secondPickAbility) {
        this.secondPickAbility = secondPickAbility;
    }

    public float getThirdPickAbilityLandfill() {
        return thirdPickAbilityLandfill;
    }

    public void setThirdPickAbilityLandfill(float thirdPickAbilityLandfill) {
        this.thirdPickAbilityLandfill = thirdPickAbilityLandfill;
    }

    public float getThirdPickAbility() {
        return thirdPickAbility;
    }

    public void setThirdPickAbility(float thirdPickAbility) {
        this.thirdPickAbility = thirdPickAbility;
    }

    public float getStackingAbility() {
        return stackingAbility;
    }

    public void setStackingAbility(float stackingAbility) {
        this.stackingAbility = stackingAbility;
    }

    public float getNoodleReliability() {
        return noodleReliability;
    }

    public void setNoodleReliability(float noodleReliability) {
        this.noodleReliability = noodleReliability;
    }

    public float getAvgNumMaxHeightStacks() {
        return avgNumMaxHeightStacks;
    }

    public void setAvgNumMaxHeightStacks(float avgNumMaxHeightStacks) {
        this.avgNumMaxHeightStacks = avgNumMaxHeightStacks;
    }

    public float getReconAbility() {
        return reconAbility;
    }

    public void setReconAbility(float reconAbility) {
        this.reconAbility = reconAbility;
    }

    public float getReconReliability() {
        return reconReliability;
    }

    public void setReconReliability(float reconReliability) {
        this.reconReliability = reconReliability;
    }

    public float getIsRobotMoveIntoAutoZonePercentage() {
        return isRobotMoveIntoAutoZonePercentage;
    }

    public void setIsRobotMoveIntoAutoZonePercentage(float isRobotMoveIntoAutoZonePercentage) {
        this.isRobotMoveIntoAutoZonePercentage = isRobotMoveIntoAutoZonePercentage;
    }

    public float getIsStackedToteSetPercentage() {
        return isStackedToteSetPercentage;
    }

    public void setIsStackedToteSetPercentage(float isStackedToteSetPercentage) {
        this.isStackedToteSetPercentage = isStackedToteSetPercentage;
    }

    public float getAvgNumReconsMovedIntoAutoZone() {
        return avgNumReconsMovedIntoAutoZone;
    }

    public void setAvgNumReconsMovedIntoAutoZone(float avgNumReconsMovedIntoAutoZone) {
        this.avgNumReconsMovedIntoAutoZone = avgNumReconsMovedIntoAutoZone;
    }

    public float getAvgNumTotesStacked() {
        return avgNumTotesStacked;
    }

    public void setAvgNumTotesStacked(float avgNumTotesStacked) {
        this.avgNumTotesStacked = avgNumTotesStacked;
    }

    public float getAvgNumReconLevels() {
        return avgNumReconLevels;
    }

    public void setAvgNumReconLevels(float avgNumReconLevels) {
        this.avgNumReconLevels = avgNumReconLevels;
    }

    public float getAvgNumNoodlesContributed() {
        return avgNumNoodlesContributed;
    }

    public void setAvgNumNoodlesContributed(float avgNumNoodlesContributed) {
        this.avgNumNoodlesContributed = avgNumNoodlesContributed;
    }

    public float getAvgNumReconsStacked() {
        return avgNumReconsStacked;
    }

    public void setAvgNumReconsStacked(float avgNumReconsStacked) {
        this.avgNumReconsStacked = avgNumReconsStacked;
    }

    public float getAvgNumTotesPickedUpFromGround() {
        return avgNumTotesPickedUpFromGround;
    }

    public void setAvgNumTotesPickedUpFromGround(float avgNumTotesPickedUpFromGround) {
        this.avgNumTotesPickedUpFromGround = avgNumTotesPickedUpFromGround;
    }

    public float getAvgNumLitterDropped() {
        return avgNumLitterDropped;
    }

    public void setAvgNumLitterDropped(float avgNumLitterDropped) {
        this.avgNumLitterDropped = avgNumLitterDropped;
    }

    public float getAvgNumStacksDamaged() {
        return avgNumStacksDamaged;
    }

    public void setAvgNumStacksDamaged(float avgNumStacksDamaged) {
        this.avgNumStacksDamaged = avgNumStacksDamaged;
    }

    public float getAvgMaxFieldToteHeight() {
        return avgMaxFieldToteHeight;
    }

    public void setAvgMaxFieldToteHeight(float avgMaxFieldToteHeight) {
        this.avgMaxFieldToteHeight = avgMaxFieldToteHeight;
    }

    public float getAvgMaxReconHeight() {
        return avgMaxReconHeight;
    }

    public void setAvgMaxReconHeight(float avgMaxReconHeight) {
        this.avgMaxReconHeight = avgMaxReconHeight;
    }

    public float getAvgAgility() {
        return avgAgility;
    }

    public void setAvgAgility(float avgAgility) {
        this.avgAgility = avgAgility;
    }

    public float getDriverAbility() {
        return driverAbility;
    }

    public void setDriverAbility(float driverAbility) {
        this.driverAbility = driverAbility;
    }

    public float getAvgStackPlacing() {
        return avgStackPlacing;
    }

    public void setAvgStackPlacing(float avgStackPlacing) {
        this.avgStackPlacing = avgStackPlacing;
    }

    public float getAvgHumanPlayerLoading() {
        return avgHumanPlayerLoading;
    }

    public void setAvgHumanPlayerLoading(float avgHumanPlayerLoading) {
        this.avgHumanPlayerLoading = avgHumanPlayerLoading;
    }

    public float getIncapacitatedPercentage() {
        return incapacitatedPercentage;
    }

    public void setIncapacitatedPercentage(float incapacitatedPercentage) {
        this.incapacitatedPercentage = incapacitatedPercentage;
    }

    public float getDisabledPercentage() {
        return disabledPercentage;
    }

    public void setDisabledPercentage(float disabledPercentage) {
        this.disabledPercentage = disabledPercentage;
    }

    public float getReliability() {
        return reliability;
    }

    public void setReliability(float reliability) {
        this.reliability = reliability;
    }

    public String getReconAcquisitionTypes() {
        return reconAcquisitionTypes;
    }

    public void setReconAcquisitionTypes(String reconAcquisitionTypes) {
        this.reconAcquisitionTypes = reconAcquisitionTypes;
    }

    public String getMostCommonReconAcquisitionType() {
        return mostCommonReconAcquisitionType;
    }

    public void setMostCommonReconAcquisitionType(String mostCommonReconAcquisitionType) {
        this.mostCommonReconAcquisitionType = mostCommonReconAcquisitionType;
    }

    public float getAvgCoopPoints() {
        return avgCoopPoints;
    }

    public void setAvgCoopPoints(float avgCoopPoints) {
        this.avgCoopPoints = avgCoopPoints;
    }

    public float getStepReconSuccessRateInAuto() {
        return stepReconSuccessRateInAuto;
    }

    public void setStepReconSuccessRateInAuto(float stepReconSuccessRateInAuto) {
        this.stepReconSuccessRateInAuto = stepReconSuccessRateInAuto;
    }

    public float getAvgStepReconsAcquiredInAuto() {
        return avgStepReconsAcquiredInAuto;
    }

    public void setAvgStepReconsAcquiredInAuto(float avgStepReconsAcquiredInAuto) {
        this.avgStepReconsAcquiredInAuto = avgStepReconsAcquiredInAuto;
    }

    public float getBottomPlacingSuccessRate() {
        return bottomPlacingSuccessRate;
    }

    public void setBottomPlacingSuccessRate(float bottomPlacingSuccessRate) {
        this.bottomPlacingSuccessRate = bottomPlacingSuccessRate;
    }

    public int getPredictedSeed() {
        return predictedSeed;
    }

    public void setPredictedSeed(int predictedSeed) {
        this.predictedSeed = predictedSeed;
    }

    public float getAvgNumHorizontalReconsPickedUp() {
        return avgNumHorizontalReconsPickedUp;
    }

    public void setAvgNumHorizontalReconsPickedUp(float avgNumHorizontalReconsPickedUp) {
        this.avgNumHorizontalReconsPickedUp = avgNumHorizontalReconsPickedUp;
    }

    public float getAvgNumVerticalReconsPickedUp() {
        return avgNumVerticalReconsPickedUp;
    }

    public void setAvgNumVerticalReconsPickedUp(float avgNumVerticalReconsPickedUp) {
        this.avgNumVerticalReconsPickedUp = avgNumVerticalReconsPickedUp;
    }

    public float getAvgNumReconsPickedUp() {
        return avgNumReconsPickedUp;
    }

    public void setAvgNumCappedSixStacks(float avgNumCappedSixStacks) {
        this.avgNumCappedSixStacks = avgNumCappedSixStacks;
    }

    public float getAvgNumTotesFromHP() {
        return avgNumTotesFromHP;
    }

    public void setAvgNumTotesFromHP(float avgNumTotesFromHP) {
        this.avgNumTotesFromHP = avgNumTotesFromHP;
    }

    public float getAvgNumTeleopReconsFromStep() {
        return avgNumTeleopReconsFromStep;
    }

    public void setAvgNumTeleopReconsFromStep(float avgNumTeleopReconsFromStep) {
        this.avgNumTeleopReconsFromStep = avgNumTeleopReconsFromStep;
    }

    public void setAvgNumReconsPickedUp(float avgNumReconsPickedUp) {
        this.avgNumReconsPickedUp = avgNumReconsPickedUp;
    }

    public float getAvgNumCappedSixStacks() {
        return avgNumCappedSixStacks;
    }
}


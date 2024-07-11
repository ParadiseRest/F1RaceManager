package com.f1.race;

import java.io.Serializable;

public class Formula1Driver extends Driver implements Serializable {

    private int noOfRaceParticipated;
    private int noOf1stPositions;
    private int noOf2ndPositions;
    private int noOf3rdPositions;
    private int totalPoints;

    public Formula1Driver() {
        this.noOf1stPositions = 0;
        this.noOf2ndPositions = 0;
        this.noOf3rdPositions = 0;
        this.totalPoints = 0;
    }

    public Formula1Driver(int noOfRaceParticipated, int noOf1stPositions, int noOf2ndPositions, int noOf3rdPositions, int totalPoints) {
        this.noOfRaceParticipated = noOfRaceParticipated;
        this.noOf1stPositions = noOf1stPositions;
        this.noOf2ndPositions = noOf2ndPositions;
        this.noOf3rdPositions = noOf3rdPositions;
        this.totalPoints = totalPoints;
    }

    public int getNoOfRaceParticipated() {
        return noOfRaceParticipated;
    }

    public void setNoOfRaceParticipated(int noOfRaceParticipated) {
        this.noOfRaceParticipated = noOfRaceParticipated;
    }

    public int getNoOf1stPositions() {
        return noOf1stPositions;
    }

    public void setNoOf1stPositions(int noOf1stPositions) {
        this.noOf1stPositions = noOf1stPositions;
    }

    public int getNoOf2ndPositions() {
        return noOf2ndPositions;
    }

    public void setNoOf2ndPositions(int noOf2ndPositions) {
        this.noOf2ndPositions = noOf2ndPositions;
    }

    public int getNoOf3rdPositions() {
        return noOf3rdPositions;
    }

    public void setNoOf3rdPositions(int noOf3rdPositions) {
        this.noOf3rdPositions = noOf3rdPositions;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void driverWinRaceAtPosition(int position){
        switch (position){
            case 1:
                totalPoints += 25;
                noOf1stPositions ++;
                break;
            case 2:
                totalPoints += 18;
                noOf2ndPositions ++;
                break;
            case 3:
                totalPoints += 15;
                noOf3rdPositions ++;
                break;
            case 4:
                totalPoints += 12;
                break;
            case 5:
                totalPoints += 10;
                break;
            case 6:
                totalPoints += 8;
                break;
            case 7:
                totalPoints += 6;
                break;
            case 8:
                totalPoints += 4;
                break;
            case 9:
                totalPoints += 2;
                break;
            case 10:
                totalPoints += 1;
                break;
        }
    }

}

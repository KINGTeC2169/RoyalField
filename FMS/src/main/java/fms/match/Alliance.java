package main.java.fms.match;

import main.java.fms.scoring.ScoreConstants;

import java.util.ArrayList;

public class Alliance {

    public enum AllianceColor {
        RED, BLUE
    }

    private ArrayList<Integer> teams;
    private AllianceColor color;
    private int relics = 0;
    private int moonRocks = 0;
    private int flags = 0;
    private int rankingPoints = 0;
    private int totalScore = 0;

    //Penalty Points value is actually pulled from the other alliance
    private int penaltyPoints = 0;

    //False if loss, True is win
    private boolean win = false;

    Alliance(AllianceColor color_){
        teams = new ArrayList<>();
        color = color_;
    }

    void setTeams(int team1, int team2){

        if(team1 == 0){
            teams.add(team1);
        }
        else{
            System.out.println("[WARNING] Setting " + color + " alliance's first partner to empty since no number was input");
        }

        if(team2 == 0){
            teams.add(team2);
        }
        else{
            System.out.println("[WARNING] Setting " + color +  "alliance's second partner to empty since no number was input");
        }

    }

    // Returns alliance-match info in CSV form in order of;
    // Team Red 1, Team Red 2, Red Relic Score, Red Moon Rock Score, Red Flag Score, Red Total Score, Red Penalty Points, Red Win (true/false)
    public String generateAllianceResults(){

        String out = "";

        if(teams.get(0) != 0){
            out += teams.get(0) + ",";
        }

        if(teams.get(1) != 0){
            out += teams.get(1) + ",";
        }

        return out + getRelics() + "," + getMoonRocks()+ "," + getFlags() + "," + getTotalScore() + "," + getRankingPoints() + "," + getWin();

    }

    public void calculateTotalScore(){
        setTotalScore(getRelics() * ScoreConstants.relicPoints + getMoonRocks() * ScoreConstants.moonRockPoints +  getFlags() * ScoreConstants.flagPoints - getPenaltyPoints());
    }

    //Getters

    int getRelics(){
        return relics;
    }

    int getMoonRocks(){
        return moonRocks;
    }

    int getFlags(){
        return flags;
    }

    int getTotalScore(){
        return totalScore;
    }

    private int getRankingPoints(){
        return rankingPoints;
    }

    private int getPenaltyPoints(){
        return penaltyPoints;
    }

    private boolean getWin(){
        return win;
    }


    //Setters

    public void setRelics(int relicPoints_){
        relics = relicPoints_;
    }

    public void setMoonRocks(int moonRockPoints_){
        moonRocks = moonRockPoints_;
    }

    public void setFlags(int flagPoints_){
        flags = flagPoints_;
    }

    private void setTotalScore(int totalScore_){
        totalScore = totalScore_;
    }

    public void setRankingPoints(int rankingPoints_){
        rankingPoints = rankingPoints_;
    }

    public void setPenaltyPoints(int penaltyPoints_){
        penaltyPoints = penaltyPoints_;
    }

    public void setWin(boolean win_){
        win = win_;
    }

}

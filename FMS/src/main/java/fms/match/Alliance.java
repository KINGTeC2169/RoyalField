package main.java.fms.match;

import main.java.fms.match.robot.Robot;
import main.java.fms.scoring.ScoreConstants;
import main.java.fms.scoring.team.Team;
import main.java.networkHandler.sensorHandler.SensorUnit;
import main.java.networkHandler.tabletHandler.FieldTablet;
import main.java.networkHandler.tabletHandler.RobotTablet;
import main.java.networkHandler.tabletHandler.TabletManager;

import java.util.ArrayList;
import java.util.Objects;
import java.util.StringJoiner;

public class Alliance {

    //Instance Variables

    public enum AllianceColor {
        RED, BLUE, NONE
    }
    private ArrayList<Team> teams;
    private ArrayList<Robot> robots;
    private AllianceColor color;
    private int opponentMajorPenalties = 0;
    private int opponentMinorPenalties = 0;
    private int fallenRelics = 0;
    private int standingRelics = 0;
    private int moonRocks = 0;
    private int flags = 0;
    private int rankingPoints = 0;
    private int totalScore = 0;
    private boolean isLinked;
    private int enemyPenaltyPoints = 0;
    private boolean win = false;
    private boolean tie = false;
    //Initializers

    Alliance(AllianceColor color_){
        teams = new ArrayList<>();
        robots = new ArrayList<>();
        color = color_;
        isLinked = false;
    }

    public Alliance(boolean empty){
        isLinked = false;
    }

    void setTeams(Team team1, Team team2){

        if(team1.getNumber() != 0){
            teams.add(team1);
        }
        else{
            System.out.println("[WARNING] Setting " + color + " alliance's first partner to empty since no number was input");
        }

        if(team2.getNumber() != 0){
            teams.add(team2);
        }
        else{
            System.out.println("[WARNING] Setting " + color +  "alliance's second partner to empty since no number was input");
        }

        if(!robots.add(new Robot(this, teams.get(0).getNumber()))){
            System.out.println("Error creating first " + color + " robot for team " + teams.get(0));
        }

        if(!robots.add(new Robot(this, teams.get(1).getNumber()))){
            System.out.println("Error creating second " + color + " robot for team " + teams.get(1));
        }

    }

    //Getters

    public AllianceColor getColor(){
        return color;
    }

    public char getAllianceCode(){
        if(getColor() == AllianceColor.RED){
            return 'r';
        }
        else if(getColor() == AllianceColor.BLUE){
            return 'b';
        }
        return 'w';
    }

    public static char getAllianceCode(AllianceColor a){
        if(a == AllianceColor.RED){
            return 'r';
        }
        else if(a == AllianceColor.BLUE){
            return 'b';
        }
        return 'w';
    }

    int getTeam(int num){
        try{
            return teams.get(num).getNumber();
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("Team Doesn't Exist!");
        }
        return 0;

    }

    int getTeamRank(int num){
        try{
            return teams.get(num).getRanking();
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("Team Doesn't Exist!");
        }
        return 0;

    }

    int getFallenRelics(){
        return fallenRelics;
    }

    int getStandingRelics(){
        return standingRelics;
    }

    int getMoonRocks(){
        return moonRocks;
    }

    public int getFlags(){
        return flags;
    }

    int getTotalScore(){
        return totalScore;
    }

    private int getRankingPoints(){
        return rankingPoints;
    }

    private int getOpponentMajorPenalties(){
        return opponentMajorPenalties;
    }

    private int getOpponentMinorPenalties(){
        return opponentMinorPenalties;
    }

    public boolean getWin(){
        return win;
    }

    public char getWLT(){
        if(win){
            setWin(true);
            setRankingPoints(2);
            return 'W';
        }
        else if(tie){
            setRankingPoints(1);
            setWin(false);
            return 'T';
        }
        setRankingPoints(0);
        setWin(false);
        return 'L';
    }

    public ArrayList<Robot> getRobots() {
        return robots;
    }

    public boolean getTie(){
        return this.tie;
    }

    //Setters

    public void setTie(boolean b) {
        this.tie = true;
    }

    private void setFallenRelics(int fallenRelics){
        this.fallenRelics = fallenRelics;
    }

    private void setStandingRelics(int standingRelics){
        this.standingRelics = standingRelics;
    }

    public void setMoonRocks(int moonRockPoints_){
        moonRocks = moonRockPoints_;
    }

    private void setFlags(int flagPoints_){
        flags = flagPoints_;
    }

    private void setTotalScore(int totalScore_){
        totalScore = totalScore_;
    }

    public void setRankingPoints(int rankingPoints_){
        rankingPoints = rankingPoints_;
    }

    private void setOpponentMajorPenalties(int opponentMajorPenalties){
        this.opponentMajorPenalties = opponentMajorPenalties;
    }

    private void setOpponentMinorPenalties(int opponentMinorPenalties){
        this.opponentMinorPenalties = opponentMinorPenalties;
    }

    public void setWin(boolean win_){
        win = win_;
    }

    //Linking

    private void interpretTabletData(){
        for(RobotTablet t:TabletManager.getAllianceRobotTablets(getColor())){
            setOpponentMajorPenalties(t.getMajorPenalties());
            setOpponentMinorPenalties(t.getMinorPenalties());
        }
        for(FieldTablet t:TabletManager.getAllianceFieldTablets(getColor())){
            setFlags(t.getFlags());
            setFallenRelics(t.getFallenRelics());
            setStandingRelics(t.getStandingRelics());
        }
        if(SensorUnit.sensorsConnected){
            setMoonRocks(TabletManager.getAllianceSensorData(getColor()));
        }
    }

    public void attemptToLink(){
        if(!isLinked){
            isLinked = true;
        }
    }

    public void unlink(){
        isLinked = false;
    }

    public boolean isLinked() {
        return isLinked;
    }

    void linkTablets(){
        for(Robot r:robots){
            if(!r.isLinked()){
                try{
                    Objects.requireNonNull(TabletManager.getUnlinkedRobotTablet()).link(r);
                }
                catch(NullPointerException ignored){
                }
            }
        }
        if(!this.isLinked()){
            try{
                Objects.requireNonNull(TabletManager.getUnlinkedFieldTablet()).link(this);
            }
            catch(NullPointerException ignored){
            }
        }
    }

    //Outputs

    void calculateTotalScore(){
        getWLT();
        interpretTabletData();
        enemyPenaltyPoints = getOpponentMinorPenalties() * ScoreConstants.minorPenaltyPoints + getOpponentMajorPenalties()
                * ScoreConstants.majorPenaltyPoints;
        setTotalScore(getFallenRelics() * ScoreConstants.fallenRelicPoints + getStandingRelics() *
                ScoreConstants.standingRelicPoints + getMoonRocks() * ScoreConstants.moonRockPoints +
                getFlags() * ScoreConstants.flagPoints + enemyPenaltyPoints);
    }

    // Returns alliance-match info in CSV form in order of;
    // Team Red 1, Team Red 2, Red Relic Score, Red Moon Rock Score, Red Flag Score, Red Total Score, Red Penalty Points, Red Win (true/false)
    public String generateAllianceResults(){

        String out = "";

        if(teams.get(0).getNumber() != 0){
            out += teams.get(0) + ",";
        }

        if(teams.get(1).getNumber() != 0){
            out += teams.get(1) + ",";
        }

        return out + getStandingRelics() + "," + getFallenRelics() + "," + getMoonRocks()+ "," + getFlags() + "," + getTotalScore() + "," + getRankingPoints() + "," + getWin();

    }

    void updateTeamScores(){
        for(Team t:teams){
            t.setMoonRocks(t.getMoonRocks() + this.getMoonRocks());
            t.setFlags(t.getFlags() + this.getFlags());
            t.setFallenRelics(t.getFallenRelics() + this.getFallenRelics());
            t.setStandingRelics(t.getStandingRelics() + this.getStandingRelics());
            t.setRp(t.getRp() + this.getRankingPoints());
            if(getWin()){
                t.setWins(t.getWins() + 1);
            }
            else{
                t.setLosses(t.getLosses() + 1);
            }
        }
    }

    public String toString(){
        StringJoiner b = new StringJoiner(",");
        b.add(teams.get(0).getNumber() + "");
        b.add(teams.get(1).getNumber() + "");
        b.add(this.getMoonRocks() + "");
        b.add(this.getFlags() + "");
        b.add(this.getFallenRelics() + "");
        b.add(this.getStandingRelics() + "");
        b.add(this.enemyPenaltyPoints + "");
        b.add(this.getTotalScore() + "");
        b.add(this.getWin() + "");
        b.add(getRankingPoints() + "");
        return b.toString();
    }

}

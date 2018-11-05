package main.java.fms.match;

import main.java.UI.text.UIStateMachine;

import java.util.StringJoiner;

public class Match {

    private static int matchID = 0;
    private int matchNum;
    private MatchType matchType;

    private static int getNewMatchID(){
        matchID++;
        return matchID;
    }

    public enum MatchType{
        QUAL, Q1, Q2, Q3, Q4, S1, S2, F
    }

    private Alliance blue;
    private Alliance red;


    public Match(int matchNum_, MatchType matchType_){

        blue = new Alliance(Alliance.AllianceColor.BLUE);
        red = new Alliance(Alliance.AllianceColor.RED);

        red.setTeams(1, 2);
        blue.setTeams(3, 4);

        matchNum = matchNum_;
        matchType = matchType_;

    }

    public String toString(){
        StringJoiner j = new StringJoiner(",");
        j.add(getNewMatchID() + "");
        j.add(matchType + "");
        j.add(matchNum + "");
        j.add(blue.toString());
        j.add(red.toString());
        return j.toString();
    }

    public void update(){
        blue.calculateTotalScore();
        red.calculateTotalScore();
    }

    public void updateScoreBoard(){

        //Update Blue Values
        UIStateMachine.setBlueScore(blue.getTotalScore());
        UIStateMachine.setBlueRelic(blue.getRelics());
        UIStateMachine.setBlueFlag(blue.getFlags());
        UIStateMachine.setBluePingPong(blue.getMoonRocks());

        //Update Red Values
        UIStateMachine.setRedScore(red.getTotalScore());
        UIStateMachine.setRedRelic(red.getRelics());
        UIStateMachine.setRedFlag(red.getFlags());
        UIStateMachine.setRedPingPong(red.getMoonRocks());

    }

    public Alliance getBlue() {
        return blue;
    }

    public Alliance getRed() {
        return blue;
    }
}

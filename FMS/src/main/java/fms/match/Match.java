package main.java.fms.match;

import main.java.UI.text.UIStateMachine;

public class Match {

    enum MatchType{
        QUAL, Q1, Q2, Q3, Q4, S1, S2, F
    }

    private Alliance blue;
    private Alliance red;
    private int matchNum;
    private MatchType matchType;


    public Match(int matchNum_, MatchType matchType_){

        blue = new Alliance(Alliance.AllianceColor.BLUE);
        red = new Alliance(Alliance.AllianceColor.RED);
        matchNum = matchNum_;
        matchType = matchType_;

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

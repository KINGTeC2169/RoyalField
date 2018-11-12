package main.java.fms.match;

import main.java.UI.text.UIStateMachine;
import main.java.fms.scoring.ScoreConstants;

import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;

public class Match {

    private boolean scoresLocked = false;
    private static int matchID = 0;
    private int matchNum;
    private MatchType matchType;
    private long startingTime = 0;

    public enum MatchState{
        AUTO, BREAK, TELE, DONE
    }
    public MatchState matchState = MatchState.AUTO;


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

    private int getElapsedSeconds(){
        return (int) TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - startingTime);
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

    public void start(){

        startingTime = System.currentTimeMillis();
        scoresLocked = false;

        UIStateMachine.setGameMode("AUTO");

    }

    public void update(){

        switch(matchState){

            case AUTO:
                if(getElapsedSeconds() >= ScoreConstants.autoDuration){
                    matchState = MatchState.BREAK;
                    break;
                }
                break;
            case BREAK:
                System.out.println("Teleop Starting");
                UIStateMachine.setGameMode("TELE");
                matchState = MatchState.TELE;
                break;
            case TELE:
                if(getElapsedSeconds() >= (ScoreConstants.autoDuration + ScoreConstants.teleDuration)) {
                    matchState = MatchState.DONE;
                }
                break;
            case DONE:
                stop();
        }

        blue.calculateTotalScore();
        red.calculateTotalScore();
        updateScoreBoard();
    }

    public void stop(){

        scoresLocked = true;

    }

    private void updateScoreBoard(){

        if(!scoresLocked){

            //Update Time
            UIStateMachine.setTime((ScoreConstants.autoDuration + ScoreConstants.teleDuration) - getElapsedSeconds());

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
    }

}

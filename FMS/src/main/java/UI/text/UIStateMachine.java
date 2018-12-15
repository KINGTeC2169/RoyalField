package main.java.UI.text;

import java.util.ArrayList;

@SuppressWarnings("ALL")
public class UIStateMachine {

    //Massive set of getters and setters for statically adjusting scoreboard data without needing to interact with the
    //JavaFX thread, which keeps things from getting thread-dangerous.

    private static String gameMode = "AUTO";
    private static String time = "00:00";
    private static int blueScore = 0;
    private static int blueStandingRelic = 0;
    private static int blueFallenRelic = 0;
    private static int blueMoonRocks = 0;
    private static int blueFlag = 0;
    private static int redScore = 0;
    private static int redStandingRelic = 0;
    private static int redFallenRelic = 0;
    private static int redMoonRocks = 0;
    private static int redFlag = 0;


    //Match State Setters
    public static void setTime(double s){

        if(s <= 0){
            time = "00:00";
        }
        else {
            int minutes;
            int seconds;

            seconds = (int) (s) % 60;
            minutes = (int) (s - seconds) / 60;
            time = addZero(minutes) + ":" + addZero(seconds);
        }
    }

    public static void setGameMode(String s){
        gameMode = s;
    }

    //Blue Score Setters
    public static void setBlueScore(int s){
        blueScore = s;
    }

    public static void setBlueMoonRocks(int blueMoonRocks) {
        UIStateMachine.blueMoonRocks = blueMoonRocks;
    }

    public static void setBlueFlag(int blueFlag) {
        UIStateMachine.blueFlag = blueFlag;
    }

    public static void setBlueStandingRelic(int blueStandingRelic) {
        UIStateMachine.blueStandingRelic = blueStandingRelic;
    }

    public static void setBlueFallenRelic(int blueFallenRelic) {
        UIStateMachine.blueFallenRelic = blueFallenRelic;
    }

    //Red Score Setters
    public static void setRedScore(int redScore) {
        UIStateMachine.redScore = redScore;
    }

    public static void setRedMoonRocks(int redMoonRocks) {
        UIStateMachine.redMoonRocks = redMoonRocks;
    }

    public static void setRedFlag(int redFlag) {
        UIStateMachine.redFlag = redFlag;
    }

    public static void setRedStandingRelic(int redStandingRelic) {
        UIStateMachine.redStandingRelic = redStandingRelic;
    }

    public static void setRedFallenRelic(int redFallenRelic) {
        UIStateMachine.redFallenRelic = redFallenRelic;
    }

    //Blue Score Getters
    public static int getBlueScore() {
        return blueScore;
    }

    public static int getBlueMoonRocks() {
        return blueMoonRocks;
    }

    public static int getBlueFlag() {
        return blueFlag;
    }

    public static int getBlueStandingRelic() {
        return blueStandingRelic;
    }

    public static int getBlueFallenRelic() {
        return blueFallenRelic;
    }


    //Red Score Getters
    public static int getRedScore() {
        return redScore;
    }

    public static int getRedMoonRocks() {
        return redMoonRocks;
    }

    public static int getRedFlag() {
        return redFlag;
    }

    public static int getRedStandingRelic() {
        return redStandingRelic;
    }

    public static int getRedFallenRelic() {
        return redFallenRelic;
    }

    //Match Status Getters
    public static String getTime(){
        return time;
    }

    public static String getGameMode(){
        return gameMode;
    }

    private static String addZero(int n){
        if(n < 10){
            return "0" + n;
        }
        return "" + n;
    }

    public static class Results{

        private static int blueOneNum = 0;
        private static int blueTwoNum = 0;
        private static int blueOneRank = 0;
        private static int blueTwoRank = 0;

        private static String blueOneName = "";
        private static String blueTwoName = "";
        private static String blueWLT = "";

        private static int redOneNum = 0;
        private static int redTwoNum = 0;
        private static int redOneRank = 0;
        private static int redTwoRank = 0;
        private static String redOneName = "";
        private static String redTwoName = "";
        private static String redWLT = "";


        public static String getBlueOneName() {
            return blueOneName;
        }

        public static void setBlueOneName(String blueOneName) {
            Results.blueOneName = blueOneName;
        }

        public static String getBlueTwoName() {
            return blueTwoName;
        }

        public static void setBlueTwoName(String blueTwoName) {
            Results.blueTwoName = blueTwoName;
        }

        public static String getRedOneName() {
            return redOneName;
        }

        public static void setRedOneName(String redOneName) {
            Results.redOneName = redOneName;
        }

        public static String getRedTwoName() {
            return redTwoName;
        }

        public static void setRedTwoName(String redTwoName) {
            Results.redTwoName = redTwoName;
        }

        public static int getBlueOneNum() {
            return blueOneNum;
        }

        public static void setBlueOneNum(int blueOneNum) {
            Results.blueOneNum = blueOneNum;
        }

        public static int getBlueTwoNum() {
            return blueTwoNum;
        }

        public static void setBlueTwoNum(int blueTwoNum) {
            Results.blueTwoNum = blueTwoNum;
        }

        public static int getBlueOneRank() {
            return blueOneRank;
        }

        public static void setBlueOneRank(int blueOneRank) {
            Results.blueOneRank = blueOneRank;
        }

        public static int getBlueTwoRank() {
            return blueTwoRank;
        }

        public static void setBlueTwoRank(int blueTwoRank) {
            Results.blueTwoRank = blueTwoRank;
        }

        public static void setBlueWLT(char c){
            if(c == 'W'){
                Results.blueWLT = "W";
            }
            else if (c == 'L'){
                Results.blueWLT = "L";
            }
            else if(c == 'T'){
                Results.blueWLT = "T";
            }
        }

        public static String getBlueWLT() {
            return blueWLT;
        }


        public static int getRedOneNum() {
            return redOneNum;
        }

        public static void setRedOneNum(int redOneNum) {
            Results.redOneNum = redOneNum;
        }

        public static int getRedTwoNum() {
            return redTwoNum;
        }

        public static void setRedTwoNum(int redTwoNum) {
            Results.redTwoNum = redTwoNum;
        }

        public static int getRedOneRank() {
            return redOneRank;
        }

        public static void setRedOneRank(int redOneRank) {
            Results.redOneRank = redOneRank;
        }

        public static int getRedTwoRank() {
            return redTwoRank;
        }

        public static void setRedTwoRank(int redTwoRank) {
            Results.redTwoRank = redTwoRank;
        }

        public static void setRedWLT(char c){
            if(c == 'W'){
                Results.blueWLT = "W";
            }
            else if (c == 'L'){
                Results.blueWLT = "L";
            }
            else if(c == 'T'){
                Results.blueWLT = "T";
            }
        }

        public static String getRedWLT() {
            return redWLT;
        }

    }

    public static class Rankings{
        public static ArrayList<String> teamNames = new ArrayList<>();
        public static ArrayList<Integer> teamNumbers = new ArrayList<>();

    }

}

package main.java.UI.text;

@SuppressWarnings("ALL")
public class UIStateMachine {

    //Massive set of getters and setters for statically adjusting scoreboard data without needing to interact with the
    //JavaFX thread, which keeps things from getting thread-dangerous.

    private static String gameMode = "AUTO";
    private static String time = "00:00";
    private static int blueScore = 0;
    private static int blueRelic = 0;
    private static int bluePingPong = 0;
    private static int blueFlag = 0;
    private static int redScore = 0;
    private static int redRelic = 0;
    private static int redPingPong = 0;
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

    public static void setBluePingPong(int bluePingPong) {
        UIStateMachine.bluePingPong = bluePingPong;
    }

    public static void setBlueFlag(int blueFlag) {
        UIStateMachine.blueFlag = blueFlag;
    }

    public static void setBlueRelic(int blueRelic) {
        UIStateMachine.blueRelic = blueRelic;
    }

    //Red Score Setters
    public static void setRedScore(int redScore) {
        UIStateMachine.redScore = redScore;
    }

    public static void setRedPingPong(int redPingPong) {
        UIStateMachine.redPingPong = redPingPong;
    }

    public static void setRedFlag(int redFlag) {
        UIStateMachine.redFlag = redFlag;
    }

    public static void setRedRelic(int redRelic) {
        UIStateMachine.redRelic = redRelic;
    }


    //Blue Score Getters
    public static int getBlueScore() {
        return blueScore;
    }

    public static int getBluePingPong() {
        return bluePingPong;
    }

    public static int getBlueFlag() {
        return blueFlag;
    }

    public static int getBlueRelic() {
        return blueRelic;
    }


    //Red Score Getters
    public static int getRedScore() {
        return redScore;
    }

    public static int getRedPingPong() {
        return redPingPong;
    }

    public static int getRedFlag() {
        return redFlag;
    }

    public static int getRedRelic() {
        return redRelic;
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

}

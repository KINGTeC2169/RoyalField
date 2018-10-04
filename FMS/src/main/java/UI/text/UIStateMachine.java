package main.java.UI.text;

public class UIStateMachine {

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

    public static void setGameMode(String s){
        gameMode = s;
    }

    public static void setTime(double s){

        if(s <= 0){
            time = "00:00";
        }
        else {
            int minutes = 0;
            int seconds = 0;

            seconds = (int) (s) % 60;
            minutes = (int) (s - seconds) / 60;
            time = addZero(minutes) + ":" + addZero(seconds);
        }
    }

    public static String getTime(){
        return time;
    }


    private static String addZero(int n){
        if(n < 10){
            return "0" + n;
        }
        return "" + n;
    }
}

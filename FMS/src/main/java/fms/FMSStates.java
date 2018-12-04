package main.java.fms;

public class FMSStates {

    public enum FMSState{
        INIT, PREMATCH, MATCH, VERIFICATION, POSTMATCH, INTER
    }
    public static FMSState state = FMSState.INIT;

    public enum MatchStatus{
        PRE, TELE, AUTO
    }
    public static MatchStatus matchStatus = MatchStatus.PRE;

    public static int stateToCode(){
        if(matchStatus == MatchStatus.PRE){
            return 0;
        }
        else if(matchStatus == MatchStatus.TELE){
            return 2;
        }
        else if(matchStatus == MatchStatus.AUTO){
            return 1;
        }
        return 1;
    }


}

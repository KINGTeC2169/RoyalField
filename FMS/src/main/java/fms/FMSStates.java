package main.java.fms;

public class FMSStates {

    public enum FMSState{
        INIT, PREMATCH, MATCH, VERIFICATION, POSTMATCH
    }
    public static FMSState state = FMSState.INIT;

    public enum MatchStatus{
        PRE, TELE, AUTO
    }
    public static MatchStatus matchStatus = MatchStatus.PRE;


}

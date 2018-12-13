package main.java.fms;

import main.java.fms.match.Match;
import main.java.fms.scoring.team.TeamMachine;

import java.util.Scanner;

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

    public static int getNextMatch(Scanner scan){

        System.out.println("[MATCH] Type NEXT for next match, custom, or enter a match ID");
        String matchQuery = scan.nextLine();
        if(matchQuery.equalsIgnoreCase("next")){
            return -1;
        }
        else if(matchQuery.equalsIgnoreCase("custom")){
            return -2;
        }
        else if(matchQuery.matches("-?\\d+(\\.\\d+)?")){
            return Integer.parseInt(matchQuery);
        }
        else{
            System.out.println("Please enter a valid input");
            getNextMatch(scan);
        }
        return -1;
    }


}

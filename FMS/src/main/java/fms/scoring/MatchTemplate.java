package main.java.fms.scoring;

import main.java.fms.match.Alliance;
import main.java.fms.match.Match;

public class MatchTemplate {

    private int matchID;
    private int matchNum;
    private Match.MatchType matchType;
    private int red1;
    private int red2;
    private int blue1;
    private int blue2;

    MatchTemplate(int matchID, int matchNum, Match.MatchType matchType, int red1, int red2, int blue1, int blue2){
        this.matchID = matchID;
        this.matchNum = matchNum;
        this.matchType = matchType;
        this.red1 = red1;
        this.red2 = red2;
        this.blue1 = blue1;
        this.blue2 = blue2;
    }

    public int getMatchID(){
        return matchID;
    }

    public int getMatchNum(){
        return matchNum;
    }

    public Match.MatchType getType(){
        return matchType;
    }

    public int getTeam(Alliance.AllianceColor color, int n){
        if(color == Alliance.AllianceColor.BLUE){
            if(n == 1){
                return blue1;
            }
            else if(n == 2){
                return blue2;
            }
        }
        else if(color == Alliance.AllianceColor.RED){
            if(n == 1){
                return red1;
            }
            else if(n == 2){
                return red2;
            }
        }
        return 0;
    }

}

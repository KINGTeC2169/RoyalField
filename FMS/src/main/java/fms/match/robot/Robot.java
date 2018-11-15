package main.java.fms.match.robot;

import main.java.fms.match.Alliance;

public class Robot {

    private boolean isLinked;
    private Alliance alliance;
    private int teamNum;
    private double ID;

    public Robot(Alliance a, int num){
        alliance = a;
        teamNum = num;
        isLinked = false;
        ID = (int) (Math.random() * 1000);
    }

    public Robot(){
        isLinked = false;
    }


    public boolean isLinked(){
        return isLinked;
    }

    public boolean attemptToLink(){
        if(!isLinked){
            isLinked = true;
            return true;
        }
        return false;
    }

    public void unlink(){
        isLinked = false;
    }

    public Alliance.AllianceColor getAlliance() {

        if(alliance.getColor() == Alliance.AllianceColor.RED){
            return Alliance.AllianceColor.BLUE;
        }
        else if(alliance.getColor() == Alliance.AllianceColor.BLUE){
            return Alliance.AllianceColor.RED;
        }
        return null;
    }

    public String toString(){
        return "" + alliance.getColor() +  ID;
    }
}

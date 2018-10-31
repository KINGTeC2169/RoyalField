package main.java.fms.match.robot;

import main.java.fms.match.Alliance;

public class Robot {

    private boolean isLinked;
    private Alliance alliance;
    private int teamNum;

    public Robot(Alliance a, int num){
        alliance = a;
        teamNum = num;
        isLinked = true;
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

}

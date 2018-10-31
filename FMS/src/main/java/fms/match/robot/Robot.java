package main.java.fms.match.robot;

public class Robot {

    public enum RobotSelection{
        RED1, RED2, BLUE1, BLUE2, ERROR
    }
    private RobotSelection robotSelection;

    private boolean isLinked = false;

    Robot(RobotSelection r){
        robotSelection = r;
    }

    public Robot attemptToLink(){
        if(!isLinked){
            isLinked = true;
            return this;
        }
        return new Robot(RobotSelection.ERROR);
    }

    public void unlink(){
        isLinked = false;
    }

}

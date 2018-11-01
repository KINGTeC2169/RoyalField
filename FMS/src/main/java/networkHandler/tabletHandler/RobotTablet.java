package main.java.networkHandler.tabletHandler;

import main.java.fms.match.robot.Robot;
import main.java.networkHandler.clientBase.Client;

import java.net.Socket;

public class RobotTablet extends Client {

    private double ID = 0;
    private Robot robot;
    private boolean linked = false;

    public RobotTablet(Socket s){
        super(s);
        System.out.println("Created Robot Tablet");
    }

    public void link(Robot r){
        r.attemptToLink();
        System.out.println("Linking");
        robot = r;
        linked = true;
        ID = (int) (Math.random() * 100);

    }

    protected void useData(String s){
        if(robot.isLinked()){
            //TODO This tabletHandler is now linked to a robot.  Score stuff
        }
    }

    protected void disconnect(){
        linked = false;
        robot = null;
        System.out.println("Connection to " + ID + " lost");
    }

    boolean isLinked(){
        return linked;
    }

    public String toString(){
        if(linked){
            return "" +ID + ": " + robot;
        }
        else{
            return "not linked";
        }
    }

}

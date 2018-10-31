package main.java.networkHandler.client.tablet;

import main.java.fms.match.robot.Robot;
import main.java.networkHandler.client.Client;

import java.net.Socket;

public class RobotTablet extends Client {

    private Robot robot;
    private boolean linked;

    public RobotTablet(Socket s){
        super(s);
    }

    public void link(Robot r){

        robot = r;
        linked = true;

    }

    protected void useData(String s){
        if(robot.isLinked()){
            //TODO This tablet is now linked to a robot.  Score stuff
        }
    }

    public boolean isLinked(){
        return linked;
    }

}

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

    protected void useData(String s){
    }

    public boolean isLinked(){
        return linked;
    }

}

package main.java.networkHandler.client.tablet;

import main.java.fms.match.Alliance;
import main.java.fms.match.Match;
import main.java.fms.match.Robot;
import main.java.fms.match.RobotManager;
import main.java.networkHandler.client.Client;

import java.net.Socket;

import static main.java.fms.match.RobotManager.codeToRobot;
import static main.java.fms.match.RobotManager.requestRobot;

public class FieldTablet extends Client {

    //Create alliance for tablet to interact with
    private Alliance alliance;

    //For now we'll hand it an empty robot
    private boolean linked = false;

    //Constructor to create a new tablet, spawned by Server on a new thread.
    public FieldTablet(Socket s){
        //Super takes care of networking inside of superclass
        super(s);
    }

    public void setAlliance(String s){
        //TODO Create an alliance claiming system that references live matches
        //IDEA: What if the match was static and the info in it rotated?
        //Gross
    }

    //The super method that interprets the data from the actual tablet.
    public void useData(String s){
        //If we are linked, and the requested link hasn't changed, process penalty data
        if(alliance.isActive()){
            String[] data = s.split(";");

        }
        else{
            setAlliance(s);
        }
    }

    public boolean isLinked(){
        return linked;
    }

}
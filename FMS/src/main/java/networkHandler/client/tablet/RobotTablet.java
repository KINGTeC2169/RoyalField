package main.java.networkHandler.client.tablet;

import main.java.fms.match.Alliance;
import main.java.fms.match.Match;
import main.java.fms.match.Robot;
import main.java.fms.match.RobotManager;
import main.java.networkHandler.client.Client;

import java.net.Socket;

import static main.java.fms.match.RobotManager.*;

public class RobotTablet extends Client {

    //We actually don't need to interact with home alliance, so expect to pull in data from opponent.
    private Alliance opponentAlliance;
    //For now we'll hand it an empty robot
    private Robot robot = new Robot(true);
    private boolean linked = false;

    //Constructor to create a new tablet, spawned by Server on a new thread.
    public RobotTablet(Socket s){
        //Super takes care of networking inside of superclass
        super(s);
        //Try to connect.  Primarily for the purpose of handing the empty robot over.
        setRobot();
    }

    //Attempt to connect to a robot
    private void setRobot(){
        // Convert the code to a robot, see if it is avaliable.  If it is, give that to robot.
        // If not, give it the empty robot
        robot = requestRobot(codeToRobot(latestData));
        // If we're linked to a real robot, set linked to true.  If we aren't it's false.
        linked = !robot.getEmpty();
    }

    //Code for converting from alliance to alliance (since they have different teams attached)
    @SuppressWarnings("unused")
    public void newMatch(Match m){
        if(robot.getAllianceColor() == Alliance.AllianceColor.BLUE){
            opponentAlliance = m.getRed();
        }
        else if(robot.getAllianceColor() == Alliance.AllianceColor.RED){
            opponentAlliance = m.getBlue();
        }
    }

    //The super method that interprets the data from the actual tablet.
    public void useData(String s){
        //If we are linked, and the requested link hasn't changed, process penalty data
        if(RobotManager.codeToRobot(latestData) == robot && linked){
            String[] data = s.split(";");
            int minorPenalties = Integer.parseInt(data[0]);
            int majorPenalties = Integer.parseInt(data[1]);
            opponentAlliance.setOpponentMinorPenalties(minorPenalties);
            opponentAlliance.setOpponentMajorPenalties(majorPenalties);

        }
        //If we aren't linked or the tablet has requested a different robot, attempt to link
        else{
            setRobot();
        }
    }

    public boolean isLinked(){
        return linked;
    }

}
package main.java.networkHandler.client.tablet;

import main.java.fms.match.Alliance;
import main.java.fms.match.Match;
import main.java.fms.match.Robot;
import main.java.fms.match.RobotManager;
import main.java.networkHandler.client.Client;

import java.net.Socket;

import static main.java.fms.match.RobotManager.*;

public class RobotTablet extends Client {

    private Alliance opponentAlliance;
    private Robot robot;
    private boolean linked = false;

    public RobotTablet(Socket s){
        super(s);
        setRobot();
    }

    private void setRobot(){
        robot = requestRobot(codeToRobot(latestData));
        linked = !robot.getEmpty();
    }

    @SuppressWarnings("unused")
    public void newMatch(Match m){
        if(robot.getAllianceColor() == Alliance.AllianceColor.BLUE){
            opponentAlliance = m.getRed();
        }
        else if(robot.getAllianceColor() == Alliance.AllianceColor.RED){
            opponentAlliance = m.getBlue();
        }
    }

    public void useData(String s){
        if(RobotManager.codeToRobot(latestData) == robot && linked){
            String[] data = s.split(";");
            int minorPenalties = Integer.parseInt(data[0]);
            int majorPenalties = Integer.parseInt(data[1]);
            opponentAlliance.setOpponentMinorPenalties(minorPenalties);
            opponentAlliance.setOpponentMajorPenalties(majorPenalties);

        }
        else{
            setRobot();
        }
    }

    public boolean isLinked(){
        return linked;
    }

}
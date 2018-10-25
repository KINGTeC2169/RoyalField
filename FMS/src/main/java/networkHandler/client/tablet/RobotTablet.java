package main.java.networkHandler.client.tablet;

import main.java.fms.match.Alliance;
import main.java.fms.match.Match;
import main.java.fms.match.Robot;
import main.java.fms.match.RobotManager;
import main.java.networkHandler.client.Client;

import java.net.Socket;

public class RobotTablet extends Client {

    private Alliance opponentAlliance;
    private Robot robot;

    public RobotTablet(Socket s){
        super(s);
        setRobot();
    }

    private void setRobot(){
        robot = RobotManager.requestRobot(RobotManager.codeToRobot(latestData));
        if(robot.getEmpty()){
            setRobot();
        }
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
        String[] data = s.split(";");
        int minorPenalties = Integer.parseInt(data[0]);
        int majorPenalties = Integer.parseInt(data[1]);
        opponentAlliance.setOpponentMinorPenalties(minorPenalties);
        opponentAlliance.setOpponentMajorPenalties(majorPenalties);
    }

}
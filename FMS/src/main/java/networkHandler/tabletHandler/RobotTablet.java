package main.java.networkHandler.tabletHandler;

import main.java.fms.match.Alliance;
import main.java.fms.match.robot.Robot;
import main.java.networkHandler.clientBase.Client;

import java.net.Socket;

public class RobotTablet extends Client {

    private int majorPenalties = 0;
    private int minorPenalties = 0;
    private double ID = 0;
    private Robot robot;
    private boolean linked = false;

    public RobotTablet(Socket s){
        super(s);
        this.start();
        System.out.println("[INFO] Created Robot Tablet");
    }

    public void link(Robot r){
        r.attemptToLink();
        robot = r;
        linked = true;
        ID = (int) (Math.random() * 100);
        System.out.println("[INFO] Linked to " + getAllianceColor() + " robot #: " + r.getRobot());

    }

    private void setMajorPenalties(int majorPenalties){
        this.majorPenalties = majorPenalties;
    }

    public int getMajorPenalties(){
        return majorPenalties;
    }

    private void setMinorPenalties(int minorPenalties){
        this.minorPenalties = minorPenalties;
    }

    public int getMinorPenalties(){
        return minorPenalties;
    }

    private void parsePenaltyData(String s) throws IndexOutOfBoundsException{
        String data[] = s.split(";");
        setMinorPenalties(Integer.parseInt(data[1]));
        setMajorPenalties(Integer.parseInt(data[2]));
    }

    public void useData(String s){
        if(robot.isLinked()){
            //TODO This tabletHandler is now linked to a robot.  Score stuff
            try{
                parsePenaltyData(s);
            }
            catch(IndexOutOfBoundsException e){
                System.out.println("[ERROR] Error parsing score data in " + toString());
            }
        }
    }

    protected void disconnect(){
        TabletManager.removeRobotTablet(this);
        linked = false;
        robot.unlink();
        robot = null;
        System.out.println("[INFO] Connection to " + ID + " lost");
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

    Alliance.AllianceColor getAllianceColor() {
        return robot.getAlliance();
    }
}

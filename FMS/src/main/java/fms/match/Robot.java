package main.java.fms.match;

public class Robot {

    // Create an instance of the AllianceColor
    private Alliance.AllianceColor color_;

    // Define characteristics about the robot
    private boolean linked = false;
    private boolean empty_ = false;

    // Option for RobotNumber.  Not an int to avoid accidental non-acceptable number
    public enum RobotNumber{
        ONE, TWO
    }

    // Create an instance of RobotNumber
    private RobotNumber robotNumber_;

    // Take a string and convert it to alliance/number data
    public Robot(String s){
        if(s.substring(0,1).equalsIgnoreCase("r")){
            color_ = Alliance.AllianceColor.RED;
        }
        else if(s.substring(0,1).equalsIgnoreCase("b")){
            color_ = Alliance.AllianceColor.BLUE;
        }
        if(Integer.parseInt(s.substring(1,2)) == 1){
            robotNumber_ = RobotNumber.ONE;
        }
        else if(Integer.parseInt(s.substring(1,2)) == 2){
            robotNumber_ = RobotNumber.TWO;
        }
    }

    // Directly pull in characteristics about robot
    Robot(Alliance.AllianceColor color, RobotNumber n){
        color_ = color;
        robotNumber_ = n;
    }

    // WARNING: ONLY USE FOR CREATING EMPTY ROBOTS
    public Robot(boolean empty){
        empty_ = empty;
    }

    public RobotNumber getRobotNumber(){
        return robotNumber_;
    }

    public boolean getEmpty(){
        return empty_;
    }

    // Handle incoming request.  If linking is not an option, return false.  Otherwise, allow the link and set
    // linked to true.
    boolean requestLink(){
        if(!linked){
            linked = true;
            return true;
        }
        return false;
    }

    public void unlink(){
        linked = false;
    }

    public Alliance.AllianceColor getAllianceColor(){
        return color_;
    }

}

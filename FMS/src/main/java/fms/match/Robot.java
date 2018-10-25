package main.java.fms.match;

public class Robot {

    private Alliance.AllianceColor color_;
    private boolean linked = false;
    private boolean empty_ = false;
    public enum RobotNumber{
        ONE, TWO
    }
    private RobotNumber robotNumber_;

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

    Robot(Alliance.AllianceColor color, RobotNumber n){
        color_ = color;
        robotNumber_ = n;
    }

    Robot(boolean empty){
        empty_ = empty;
    }

    public RobotNumber getRobotNumber(){
        return robotNumber_;
    }


    public boolean getEmpty(){
        return empty_;
    }

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

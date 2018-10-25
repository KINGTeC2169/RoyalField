package main.java.fms.match;

public class Robot {

    private Alliance.AllianceColor color_;
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

    public RobotNumber getRobotNumber(){
        return robotNumber_;
    }


    public Alliance.AllianceColor getAllianceColor(){
        return color_;
    }

}

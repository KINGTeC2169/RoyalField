package main.java.fms.match;

public class RobotManager {

    private static Robot r1 = new Robot(Alliance.AllianceColor.RED, Robot.RobotNumber.ONE);
    private static Robot r2 = new Robot(Alliance.AllianceColor.RED, Robot.RobotNumber.TWO);
    private static Robot b1 = new Robot(Alliance.AllianceColor.BLUE, Robot.RobotNumber.ONE);
    private static Robot b2 = new Robot(Alliance.AllianceColor.BLUE, Robot.RobotNumber.TWO);
    private static Robot disconnected = new Robot(true);

    public static Robot requestRobot(Robot r){
        if(r.requestLink()){
            return r;
        }
        return disconnected;
    }

    public static Robot codeToRobot(String s) {
        s = s.substring(9, 11);
        if (s.substring(0, 1).equalsIgnoreCase("r")) {
            if (Integer.parseInt(s.substring(1, 2)) == 1) {
                return r1;
            } else if (Integer.parseInt(s.substring(1, 2)) == 2) {
                return r2;
            }
        } else if (s.substring(0, 1).equalsIgnoreCase("b")) {
            if (Integer.parseInt(s.substring(1, 2)) == 1) {
                return b1;
            } else if (Integer.parseInt(s.substring(1, 2)) == 2) {
                return b2;
            }
        }

        return disconnected;

    }

}

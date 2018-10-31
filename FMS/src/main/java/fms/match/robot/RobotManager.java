package main.java.fms.match.robot;

import static main.java.fms.match.robot.Robot.RobotSelection.*;
public class RobotManager {

    //Take a full data input string and convert to a robot instance.
    //If something is wrong or the data is invalid, return a disconnected robot instance.
    public static Robot.RobotSelection codeToRobot(String s) {
        s = s.substring(9, 11);
        if (s.substring(0, 1).equalsIgnoreCase("r")) {
            if (Integer.parseInt(s.substring(1, 2)) == 1) {
                return RED1;
            } else if (Integer.parseInt(s.substring(1, 2)) == 2) {
                return RED2;
            }
        } else if (s.substring(0, 1).equalsIgnoreCase("b")) {
            if (Integer.parseInt(s.substring(1, 2)) == 1) {
                return BLUE1;
            } else if (Integer.parseInt(s.substring(1, 2)) == 2) {
                return BLUE2;
            }
        }

        return ERROR;

    }

}

package main.java.networkHandler.tabletHandler;

import main.java.fms.match.Alliance;
import main.java.networkHandler.sensorHandler.SensorUnit;

import java.util.ArrayList;

public class TabletManager {

    private static ArrayList<RobotTablet> robotTablets = new ArrayList<>();
    private static ArrayList<FieldTablet> fieldTablets = new ArrayList<>();
    private static ArrayList<SensorUnit> sensorUnits = new ArrayList<>();

    public static void addFieldTablet(FieldTablet fT){
        fieldTablets.add(fT);
    }

    public static void addRobotTablet(RobotTablet rT){
        robotTablets.add(rT);
    }

    public static void addSensorUnit(SensorUnit s){
        sensorUnits.add(s);
    }

    public static RobotTablet getUnlinkedRobotTablet(){
        for(RobotTablet rT: robotTablets){
            if(!rT.isLinked()){
                return rT;
            }
        }
        return null;
    }

    public static ArrayList<FieldTablet> getAllianceFieldTablets(Alliance.AllianceColor color){

        ArrayList<FieldTablet> out = new ArrayList<>();
        for(FieldTablet fT:fieldTablets){
            if(fT.getAllianceColor() == color){
                out.add(fT);
            }

        }
        return out;
    }


    public static ArrayList<RobotTablet> getAllianceRobotTablets(Alliance.AllianceColor color){

        ArrayList<RobotTablet> out = new ArrayList<>();
        for(RobotTablet rT:robotTablets){
            if(rT.getAllianceColor() == color){
                out.add(rT);
            }

        }
        return out;
    }

    public static int getAllianceSensorData(Alliance.AllianceColor color){

        for(SensorUnit s:sensorUnits){
            if(color == Alliance.AllianceColor.RED){
                return s.getRedMoonRocks();
            }
            else if(color == Alliance.AllianceColor.BLUE){
                return s.getBlueMoonRocks();
            }
        }
        return -1;


    }

    public static FieldTablet getUnlinkedFieldTablet(){
        for(FieldTablet fT: fieldTablets){
            if(!fT.isLinked()){
                System.out.println(fT);
                return fT;
            }
        }
        return null;
    }

    static void printFieldTablets() {
        for (FieldTablet fT : fieldTablets) {
            System.out.println(fT);
        }
    }

    static void removeRobotTablet(RobotTablet robotTablet) {
        robotTablets.remove(robotTablet);
        System.out.println(robotTablets);
    }

    static void removeFieldTablet(FieldTablet fieldTablet) {
        fieldTablets.remove(fieldTablet);
    }
}

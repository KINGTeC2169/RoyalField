package main.java.networkHandler.tabletHandler;

import java.util.ArrayList;

public class TabletManager {

    private static ArrayList<RobotTablet> robotTablets = new ArrayList<>();
    private static ArrayList<FieldTablet> fieldTablets = new ArrayList<>();

    public static void addFieldTablet(FieldTablet fT){
        fieldTablets.add(fT);
    }


    public static void addRobotTablet(RobotTablet rT){
        robotTablets.add(rT);
    }

    public static RobotTablet getUnlinkedRobotTablet(){
        for(RobotTablet rT: robotTablets){
            System.out.println(rT);
            if(!rT.isLinked()){
                return rT;
            }
        }
        return null;
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

    public static void printRobotTablets(){
        for(RobotTablet rT: robotTablets){
            System.out.println(rT);
        }
    }

    public static void printNumRobotTablets(){
        System.out.println(robotTablets.size());
    }

    public static void printFieldTablets() {
        for (FieldTablet fT : fieldTablets) {
            System.out.println(fT);
        }
    }

}

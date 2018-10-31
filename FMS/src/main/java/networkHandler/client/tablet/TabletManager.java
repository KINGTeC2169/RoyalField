package main.java.networkHandler.client.tablet;

import java.util.ArrayList;

public class TabletManager {

    private ArrayList<RobotTablet> robotTablets;
    private ArrayList<FieldTablet> fieldTablets;

    public TabletManager(){

        robotTablets = new ArrayList<>();
        fieldTablets = new ArrayList<>();

    }

    public void addFieldTablet(FieldTablet fT){
        fieldTablets.add(fT);
    }


    public void addRobotTablet(RobotTablet rT){
        robotTablets.add(rT);
    }

    public void printRobotTablets(){
        for(RobotTablet rT: robotTablets){
            System.out.println(rT);
        }
    }


    public void printFieldTablets(){
        for(FieldTablet fT: fieldTablets){
            System.out.println(fT);
        }
    }

}

package main.java.networkHandler.sensorHandler;

import main.java.fms.FMSStates;
import main.java.fms.match.Alliance;
import main.java.fms.match.robot.Robot;
import main.java.networkHandler.clientBase.Client;
import main.java.networkHandler.tabletHandler.TabletManager;

import java.net.Socket;

public class SensorUnit extends Client {

    private int redMoonRocks = 0;
    private int blueMoonRocks = 0;
    public static boolean sensorsConnected = false;

    public SensorUnit(Socket s) {
        super(s);
        sensorsConnected = true;
    }

    public void useData(String s){
        System.out.println(FMSStates.matchStatus);
        this.setResponse(FMSStates.matchStatus + "");
        String[] values = s.split(";");
        redMoonRocks = Integer.parseInt(values[0]);
        blueMoonRocks = Integer.parseInt(values[1]);
    }

    public int getRedMoonRocks(){
        return redMoonRocks;
    }

    public int getBlueMoonRocks(){
        return blueMoonRocks;
    }

    protected void disconnect(){
        sensorsConnected = false;
    }
}

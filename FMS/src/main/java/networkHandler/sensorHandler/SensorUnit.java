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
        this.start();
        sensorsConnected = true;
    }

    public void useData(String s){
        this.setResponse(FMSStates.stateToCode()+ "");
        String[] values = s.split(";");
        redMoonRocks = Integer.parseInt(values[1]);
        blueMoonRocks = Integer.parseInt(values[2]);
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

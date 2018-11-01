package main.java.networkHandler.tabletHandler;

import main.java.fms.match.Alliance;
import main.java.networkHandler.clientBase.Client;

import java.net.Socket;

public class FieldTablet extends Client {

    private double ID = 0;
    private Alliance alliance;
    private boolean linked = false;

    public FieldTablet(Socket s){
        super(s);
        System.out.println("Created Field Tablet");
    }

    public void link(Alliance a){
        a.attemptToLink();
        System.out.println("Linking");
        alliance  = a;
        linked = true;
        ID = (int) (Math.random() * 100);

    }

    protected void useData(String s){
        if(alliance.isLinked()){
            //TODO This tabletHandler is now linked to an alliance.  Score stuff
        }
    }

    protected void disconnect(){
        linked = false;
        alliance = null;
        System.out.println("Connection to " + ID + " lost");
    }

    boolean isLinked(){
        return linked;
    }

    public String toString(){
        if(linked){
            return "" +ID + ": " + alliance;
        }
        else{
            return "not linked";
        }
    }

}

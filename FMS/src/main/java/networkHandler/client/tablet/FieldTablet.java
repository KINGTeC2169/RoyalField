package main.java.networkHandler.client.tablet;

import main.java.fms.match.Alliance;
import main.java.networkHandler.client.Client;

import java.net.Socket;

public class FieldTablet extends Client {

    private Alliance alliance;
    private boolean linked;

    public FieldTablet(Socket s){
        super(s);
    }

    protected void useData(String s){

    }

    public boolean isLinked(){
        return linked;
    }

}

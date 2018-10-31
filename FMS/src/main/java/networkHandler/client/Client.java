package main.java.networkHandler.client;

import java.net.Socket;

// Client class
public class Client extends Thread {
    private final Socket s;

    // Constructor
    public Client(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {

    }

    protected void useData(String s){

    }
}


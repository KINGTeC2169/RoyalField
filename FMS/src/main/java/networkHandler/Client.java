package main.java.networkHandler;

import java.io.*;
import java.net.Socket;

// Client class
public class Client extends Thread {
    private final Socket s;
    protected String latestData = "";


    // Constructor
    public Client(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {

        BufferedReader in;

        try {
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));

        String data;
        while (s.isConnected()) {
            //Check if we've got new data from our client.
            if ((data = in.readLine()) != null) {
                //If we do, do something with it!
                latestData = "";
                useData(data);
            }
        }
    } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void useData(String s){

    }
}


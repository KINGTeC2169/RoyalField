package main.java.networkHandler.clientBase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

// Client class
public class Client extends Thread {
    private final Socket s;

    // Constructor
    public Client(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {

        try {

            String data;
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

            while (true) {
                //Check if we've got new data from our clientBase.
                if ((data = in.readLine()) != null) {
                    System.out.println(data);
                    useData(data);
                }
            }

        }
        catch(SocketException f) {
                disconnect();
                System.out.println(this + " has Disconnected!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    protected void disconnect(){

    }

    public void useData(String s){
        System.out.println("Client Print: " + s);
    }
}


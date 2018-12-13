package main.java.networkHandler.clientBase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

// Client class
public class Client extends Thread {
    private final Socket s;
    private boolean connected = false;
    private String response = "";
    private boolean getConnected(){
        return connected;
    }
    private void setConnected(boolean connected){
        this.connected = connected;
    }

    // Constructor
    public Client(Socket s) {
        this.s = s;
        setConnected(true);
    }

    @Override
    public void run() {

        try {

            String data;
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);


            while (connected) {
                //Check if we've got new data from our clientBase.
                long startTime = System.currentTimeMillis();
                out.println(this.response);
                out.flush();
                while(!in.ready()){
                    long elapsedTime = System.currentTimeMillis() - startTime;
                    if(elapsedTime > 3000){
                        if(getConnected()) {
                            disconnect();
                            System.out.println(getConnected());
                            setConnected(false);
                        }
                    }
                    Thread.sleep(250);

                }
                useData(in.readLine());
                Thread.sleep(250);
            }

        }
        catch(SocketException f) {
                disconnect();
                System.out.println(this + " has Disconnected!");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    protected void disconnect(){
        if(getConnected()){
            System.out.println("Device Disconnected!");
            setConnected(false);
        }
    }

    public void useData(String s){
        System.out.println("[Client:80]" + s);
    }

    public void setResponse(String response) {
        this.response = response;
    }

}


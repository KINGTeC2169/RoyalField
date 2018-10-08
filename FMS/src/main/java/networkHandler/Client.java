package main.java.networkHandler;

import main.java.UI.text.UIStateMachine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

class Client {

    private PrintWriter out;
    private String ip;
    private String UUID;
    private Socket socket_;
    private boolean hasDisconnected;

    Client(Socket socket){

        //Take in information about the client.
        socket_ = socket;
        ip = socket.getInetAddress().getHostAddress();
        long time = System.currentTimeMillis();

        //Create UUID, removing any strange characters
        UUID = socket_.getInetAddress().getHostName().replaceAll("[^A-Za-z0-9]", "")+ time;
        hasDisconnected = false;

        //Start a new communication thread
        Thread n = new Thread(() -> {
            try {
                //Try to talk.
                talk();
            }
            catch(SocketException f) {
                //If we can't talk, disconnect.  Something has gone wrong.
                disconnect();
                System.out.println(UUID + " has Disconnected!");
            }
            catch (IOException e) {
                //If the problem wasn't connection, something has gone horribly wrong.  Print error.
                e.printStackTrace();
            }

        });
        n.start();

    }

    //IP Address getter
    String getIP(){
        return ip;
    }

    //Universal User IDentification (UUID) getter
    String getUUID(){
        return UUID;
    }

    //Connection health checker
    @SuppressWarnings("unused")
    boolean isConnected() throws IOException {
        return socket_.getInetAddress().isReachable(10);
    }

    //Connection state getter
    boolean isHasDisconnected(){
        return hasDisconnected;
    }

    //Method to disconnect
    private void disconnect(){
        hasDisconnected = true;
    }

    //Method to write things to the client
    private void writeMessage(String s){
        out.println(s);
        out.flush();
    }

    //Talk method.  Main means of communication
    private void talk() throws IOException {
        String data;
        BufferedReader in = new BufferedReader(new InputStreamReader(socket_.getInputStream()));
        out = new PrintWriter(socket_.getOutputStream(), true);
        //While the socket is connected,
        while (socket_.isConnected()) {
            //Check if we've got new data from our client.
            if ((data = in.readLine()) != null) {
                //If we do, do something with it!
                interpretMessage(data);
            }
        }
    }

    //Temporary method for parsing human input into UI changes.
    private void interpretMessage(String s){
        System.out.println(s);
        if(s.contains(" ")){
            String[] splited = s.split(" ");
            if(splited[0].toLowerCase().contains("score")){
                if(splited[1].toLowerCase().contains("blue")){
                    System.out.println("Setting blue score to " + splited[2]);
                    UIStateMachine.setBlueScore(Integer.parseInt(splited[2]));
                }
                else if(splited[1].toLowerCase().contains("red")){
                    System.out.println("Setting red score to " + splited[2]);
                    UIStateMachine.setRedScore(Integer.parseInt(splited[2]));
                }
            }
            else if(splited[0].toLowerCase().contains("flag")){
                if(splited[1].toLowerCase().contains("blue")){
                    System.out.println("Setting blue flag to " + splited[2]);
                    UIStateMachine.setBlueFlag(Integer.parseInt(splited[2]));
                }
                else if(splited[1].toLowerCase().contains("red")){
                    System.out.println("Setting red flag to " + splited[2]);
                    UIStateMachine.setRedFlag(Integer.parseInt(splited[2]));
                }
            }
            else if(splited[0].toLowerCase().contains("relic")){
                if(splited[1].toLowerCase().contains("blue")){
                    System.out.println("Setting blue relic to " + splited[2]);
                    UIStateMachine.setBlueRelic(Integer.parseInt(splited[2]));
                }
                else if(splited[1].toLowerCase().contains("red")){
                    System.out.println("Setting red relic to " + splited[2]);
                    UIStateMachine.setRedRelic(Integer.parseInt(splited[2]));
                }
            }
            else if(splited[0].toLowerCase().contains("ping")){
                if(splited[1].toLowerCase().contains("blue")){
                    System.out.println("Setting blue ping pong to " + splited[2]);
                    UIStateMachine.setBluePingPong(Integer.parseInt(splited[2]));
                }
                else if(splited[1].toLowerCase().contains("red")){
                    System.out.println("Setting red ping pong to " + splited[2]);
                    UIStateMachine.setRedPingPong(Integer.parseInt(splited[2]));
                }
            }
            else if(splited[0].toLowerCase().contains("time")){
                UIStateMachine.setTime(Integer.parseInt(splited[1]));
            }

            else if(splited[0].toLowerCase().contains("game")){
                UIStateMachine.setGameMode(splited[1]);
            }
        }
        else{
            writeMessage("Please use the correct format");
        }

    }

}

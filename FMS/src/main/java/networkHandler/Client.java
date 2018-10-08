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
        socket_ = socket;
        ip = socket.getInetAddress().getHostAddress();
        long time = System.currentTimeMillis();
        UUID = socket_.getInetAddress().getHostName().replaceAll("[^A-Za-z0-9]", "")+ time;
        hasDisconnected = false;
        Thread n = new Thread(() -> {
            try {
                talk();
            }
            catch(SocketException f) {
                disconnect();
                System.out.println(UUID + " has Disconnected!");
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        });
        n.start();

    }

    String getIP(){
        return ip;
    }

    String getUUID(){
        return UUID;
    }

    @SuppressWarnings("unused")
    boolean isConnected() throws IOException {
        return socket_.getInetAddress().isReachable(10);
    }

    boolean isHasDisconnected(){
        return hasDisconnected;
    }

    private void disconnect(){
        hasDisconnected = true;
    }

    private void writeMessage(String s){
        out.println(s);
        out.flush();
    }
    
    private void talk() throws IOException {
        String data;
        BufferedReader in = new BufferedReader(new InputStreamReader(socket_.getInputStream()));
        out = new PrintWriter(socket_.getOutputStream(), true);
        writeMessage("Enter: time, mode, score, ping, flag, relic");
        writeMessage("space");
        writeMessage("desired value");
        while (socket_.isConnected()) {
            if ((data = in.readLine()) != null) {
                interpretMessage(data);
            }
        }
    }

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

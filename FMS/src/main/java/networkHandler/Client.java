package main.java.networkHandler;

import main.java.UI.text.UIStateMachine;

import java.io.*;
import java.net.Socket;
// Client class
class Client extends Thread {
    final Socket s;
    final String ip;
    private boolean connected;


    // Constructor
    public Client(Socket s) {
        this.s = s;
        this.ip = s.getInetAddress().getHostAddress();
        this.connected = true;
    }

    @Override
    public void run() {

        BufferedReader in = null;

        try {
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));

        String data;
        while (s.isConnected()) {
            //Check if we've got new data from our client.
            if ((data = in.readLine()) != null) {
                //If we do, do something with it!
                interpretMessage(data);
            }
        }
    } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Temporary method for parsing human input into UI changes.
    private void interpretMessage(String s) {
        System.out.println(s);
        if (s.contains(" ")) {
            String[] splited = s.split(" ");
            if (splited[0].toLowerCase().contains("score")) {
                if (splited[1].toLowerCase().contains("blue")) {
                    System.out.println("Setting blue score to " + splited[2]);
                    UIStateMachine.setBlueScore(Integer.parseInt(splited[2]));
                } else if (splited[1].toLowerCase().contains("red")) {
                    System.out.println("Setting red score to " + splited[2]);
                    UIStateMachine.setRedScore(Integer.parseInt(splited[2]));
                }
            } else if (splited[0].toLowerCase().contains("flag")) {
                if (splited[1].toLowerCase().contains("blue")) {
                    System.out.println("Setting blue flag to " + splited[2]);
                    UIStateMachine.setBlueFlag(Integer.parseInt(splited[2]));
                } else if (splited[1].toLowerCase().contains("red")) {
                    System.out.println("Setting red flag to " + splited[2]);
                    UIStateMachine.setRedFlag(Integer.parseInt(splited[2]));
                }
            } else if (splited[0].toLowerCase().contains("relic")) {
                if (splited[1].toLowerCase().contains("blue")) {
                    System.out.println("Setting blue relic to " + splited[2]);
                    UIStateMachine.setBlueRelic(Integer.parseInt(splited[2]));
                } else if (splited[1].toLowerCase().contains("red")) {
                    System.out.println("Setting red relic to " + splited[2]);
                    UIStateMachine.setRedRelic(Integer.parseInt(splited[2]));
                }
            } else if (splited[0].toLowerCase().contains("ping")) {
                if (splited[1].toLowerCase().contains("blue")) {
                    System.out.println("Setting blue ping pong to " + splited[2]);
                    UIStateMachine.setBluePingPong(Integer.parseInt(splited[2]));
                } else if (splited[1].toLowerCase().contains("red")) {
                    System.out.println("Setting red ping pong to " + splited[2]);
                    UIStateMachine.setRedPingPong(Integer.parseInt(splited[2]));
                }
            } else if (splited[0].toLowerCase().contains("time")) {
                UIStateMachine.setTime(Integer.parseInt(splited[1]));
            } else if (splited[0].toLowerCase().contains("game")) {
                UIStateMachine.setGameMode(splited[1]);
            }
        } else {
            //out.write("Please use the correct format");
        }

    }

    public String getIP() {
        return ip;
    }

    public boolean isHasDisconnected() {
        return !connected;
    }
}


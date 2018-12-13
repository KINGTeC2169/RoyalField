package main.java.networkHandler.clientBase;

import main.java.UI.text.UIStateMachine;

import java.net.Socket;

// Client class
@SuppressWarnings("unused")
class DemoClient extends Client {

    // Constructor
    public DemoClient(Socket s) {
        super(s);
    }

    public void useData(String s) {
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
                    UIStateMachine.setBlueStandingRelic(Integer.parseInt(splited[2]));
                } else if (splited[1].toLowerCase().contains("red")) {
                    System.out.println("Setting red relic to " + splited[2]);
                    UIStateMachine.setRedStandingRelic(Integer.parseInt(splited[2]));
                }
            } else if (splited[0].toLowerCase().contains("ping")) {
                if (splited[1].toLowerCase().contains("blue")) {
                    System.out.println("Setting blue ping pong to " + splited[2]);
                    UIStateMachine.setBlueMoonRocks(Integer.parseInt(splited[2]));
                } else if (splited[1].toLowerCase().contains("red")) {
                    System.out.println("Setting red ping pong to " + splited[2]);
                    UIStateMachine.setRedMoonRocks(Integer.parseInt(splited[2]));
                }
            } else if (splited[0].toLowerCase().contains("time")) {
                UIStateMachine.setTime(Integer.parseInt(splited[1]));
            } else if (splited[0].toLowerCase().contains("game")) {
                UIStateMachine.setGameMode(splited[1]);
            }
        }

    }
}


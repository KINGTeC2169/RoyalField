package main.java;

import main.java.fms.match.Match;
import main.java.networkHandler.NetworkMain;
import main.java.networkHandler.tabletHandler.TabletManager;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {


        Thread loop = new Thread(Main::loop);
        loop.start();


        //Launch the Network System.
        NetworkMain.start();

        //Launch the UI system
        //UIMain.start(args);

    }

    public static int shutdown(){
        return 0;
    }

    public static void loop() {

        Match m = new Match(1, Match.MatchType.QUAL);
        /*switch(FMSStates.state){

            case INIT:
                break;
            case PREMATCH:
                FMSStates.state = FMSStates.FMSState.MATCH;
                break;
            case MATCH:
                break;
            case VERIFICATION:
                break;
            case POSTMATCH:
                break;
            default:
                break;
        }
        */
        while(true){
            m.update();
            TabletManager.printRobotTablets();
            TabletManager.printFieldTablets();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

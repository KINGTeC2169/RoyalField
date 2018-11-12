package main.java;

import main.java.UI.AudioManager;
import main.java.UI.text.UIStateMachine;
import main.java.fms.FMSStates;
import main.java.fms.match.Match;
import main.java.fms.scoring.DatabaseHandler;
import main.java.fms.scoring.team.TeamMachine;
import main.java.networkHandler.NetworkMain;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        Thread loop = new Thread(() -> {
            while(System.currentTimeMillis() > 0){
                loop();
            }
        });
        loop.start();


        //Launch the Network System.
        NetworkMain.start();

        //Launch the UI system
        //UIMain.start(args);

    }

    public static int shutdown(){
        return 0;
    }

    private static Match m = null;

    private static void loop() {

        Scanner scan = new Scanner(System.in);
        DatabaseHandler handler = new DatabaseHandler();

        switch(FMSStates.state){

            case INIT:

                //Wait For Connections until told to advance
                try {
                    TeamMachine.generateTeamList();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                FMSStates.state = FMSStates.FMSState.PREMATCH;
                System.out.println("Init Complete");
                break;

            case PREMATCH:

                //Check Sensors
                //Check Tablet Count
                UIStateMachine.setGameMode("AUTO");
                while(!scan.hasNext()){

                }
                System.out.println("Prematch Started");
                try {
                    m = handler.readNextMatch();
                    m.start();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch(NullPointerException e){
                    System.out.println("ERROR: Match Not Found!");
                    break;
                }
                FMSStates.state = FMSStates.FMSState.MATCH;
                System.out.println("Prematch Complete");
                AudioManager.playCharge();
                break;

            case MATCH:

                if(m.matchState == Match.MatchState.DONE){
                    m.stop();
                    System.out.println("Match Complete");
                    FMSStates.state = FMSStates.FMSState.VERIFICATION;
                    break;
                }
                m.update();
                break;

            case VERIFICATION:

                UIStateMachine.setGameMode("DONE");
                System.out.println("Awaiting Score Verification");
                System.out.println("Are these score correct?");
                while (!scan.nextLine().equals("yes")) {
                    System.out.println("Are these score correct?");
                }
                FMSStates.state = FMSStates.FMSState.POSTMATCH;
                break;

            case POSTMATCH:

                try {
                    handler.archiveMatch(m);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Match Completed");
                System.out.println("*SHOWING RESULTS*");
                System.out.println("Returning to prematch");
                m.stop();
                FMSStates.state = FMSStates.FMSState.PREMATCH;
                break;

            default:
                break;
        }
    }
}

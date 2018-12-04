package main.java;

import main.java.UI.AudioManager;
import main.java.UI.UIMain;
import main.java.UI.text.UIStateMachine;
import main.java.fms.FMSStates;
import main.java.fms.match.Match;
import main.java.fms.scoring.DatabaseHandler;
import main.java.fms.scoring.team.TeamMachine;
import main.java.networkHandler.NetworkMain;

import java.io.IOException;
import java.util.Scanner;

import static main.java.UI.UIMain.UIState.*;

public class Main {

    static UIMain ui;
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
        ui = new UIMain();
        ui.start(args);

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
                System.out.println("[MATCH] Init Complete");
                break;

            case PREMATCH:

                //Check Sensors
                //Check Tablet Count
                UIStateMachine.setGameMode("AUTO");
                System.out.println("[MATCH] Prematch Started");
                try {
                    m = handler.readNextMatch();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("[ERROR] Match Read/Write Failed!");
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    System.out.println("[ERROR] Match Not Found!");
                }

                Thread loop = new Thread(() -> {
                    while(System.currentTimeMillis() > 0){
                        m.connectTablets();
                    }
                });
                loop.start();

                while(!scan.hasNext()){

                    m.connectTablets();

                }

                try {
                    m.start();
                }
                catch(Exception e) {
                    System.out.println("[ERROR] Error Starting Match");
                }

                ui.setUIState(MATCH);
                FMSStates.state = FMSStates.FMSState.MATCH;
                System.out.println("[MATCH] Prematch Complete");
                System.out.println("[MATCH] Starting Auto");
                AudioManager.playCharge();
                break;

            case MATCH:
                if(m.matchState == Match.MatchState.DONE){
                    System.out.println("[MATCH] Match Complete");
                    FMSStates.state = FMSStates.FMSState.VERIFICATION;
                    break;
                }
                m.update();
                break;

            case VERIFICATION:

                ui.setUIState(POST);
                AudioManager.playEnd();
                UIStateMachine.setGameMode("DONE");
                System.out.println("[MATCH] Awaiting Score Verification");
                System.out.println("[MATCH] Are these score correct?");
                while (!scan.nextLine().equals("yes")) {
                    System.out.println("[MATCH] Are these score correct?");
                }
                FMSStates.state = FMSStates.FMSState.POSTMATCH;
                break;

            case POSTMATCH:
                try {
                    TeamMachine.updateRankings();
                    handler.archiveMatch(m);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("[MATCH] Match Completed");
                System.out.println("[MATCH] *SHOWING RESULTS*");
                m.lockScores();
                FMSStates.state = FMSStates.FMSState.INTER;
                break;

            case INTER:
                System.out.println("[MATCH] Entering Intermatch");
                while (!scan.nextLine().equals("yes")) {
                    System.out.println("[MATCH] Would you like to start the next match?");
                }
                FMSStates.state = FMSStates.FMSState.PREMATCH;

            default:
                break;
        }
    }
}

package com.team2169.reftablet;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Main extends AppCompatActivity {

    /*
     * connection can be set to three possible values
     * 0 means that no alliance has been selected
     * r means that the tablet is connected to the red alliance
     * b connected to the blue alliance
     * */

    String ip = "192.168.0.97";
    Socket s;
    private String incomingData = "";
    private int textColor = Color.WHITE;
    private int major = 0;
    private int minor = 0;
    private String gameState;

    private void ioThread(Socket s){
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader((s.getInputStream())));
            out = new PrintWriter(s.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        out.println("JTB");
        out.flush();
        while(true){
            String data;
            try {
                if((data = in.readLine()) != null){
                    incomingData = data;
                }
                visualsUpdate();
                System.out.println("JTB;"+ minor + ";" + major);
                out.println("JTB;"+ minor + ";" + major + ";");
                out.flush();
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void attemptToConnect() {
        try {
            s = new Socket(ip, 2169);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to Connect!");
        }
        System.out.println("Successfully Connected!");
        Thread in = new Thread(() -> ioThread(s));
        in.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Thread t = new Thread(this::attemptToConnect);
        t.start();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button add_standing_relic = findViewById(R.id.majorPlus);
        add_standing_relic.setOnClickListener(view -> {
            major++;
            ((TextView) findViewById(R.id.majorAdd)).setText(Integer.toString(major));
        });

        Button remove_standing_relic = findViewById(R.id.majorSub);
        remove_standing_relic.setOnClickListener(view -> {
            if (major > 0) major--;
            ((TextView) findViewById(R.id.majorAdd)).setText(Integer.toString(major));
        });

        Button addFlag = findViewById(R.id.minorAdd);
        addFlag.setOnClickListener(view -> {
           minor++;
            ((TextView) findViewById(R.id.minorVal)).setText(Integer.toString(minor));
        });

        Button subtractFlag = findViewById(R.id.minorSub);
        subtractFlag.setOnClickListener(view -> {
            if (minor - 1 >= 0) minor--;
            ((TextView) findViewById(R.id.minorVal)).setText(Integer.toString(minor));
        });


    }

    public void visualsUpdate(){

        /*
         * Output is in format connection;standingRelics;tippedRelics
         * Connection is the name of the alliance the user has selected (r, b)
         * */

        /*
         * Input will be in format alliance;gameState
         * Alliance is a 2 digit binary code using t and f
         * where t means taken or true, and f means free or false
         * gameState can be set to a value 0-3
         * 0: Pre-game
         * 1: Autonomous
         * 2: TeleOp
         * 3: Endgame
         *
         * This plus the team number at the end
         * */
            String teamNumber = "";
            String inputs[] = incomingData.split(";", 3);
            try{

                String alliance = inputs[0];
                if(alliance.equals("r")){
                    textColor = Color.RED;
                }
                else if(alliance.equals("b")){
                    textColor = Color.BLUE;
                }

                String gameState = inputs[1];
                if(gameState.equals("0")){
                    this.gameState = "Pre-Match";
                    major = 0;
                    minor = 0;
                }
                else if(gameState.equals("1")){
                    this.gameState = "Auto";
                }
                else if(gameState.equals("2")){
                    this.gameState = "Tele";
                }
                else if(gameState.equals("3")){
                    this.gameState = "End";
                }

                teamNumber = inputs[2];
                System.out.println(this.gameState);


            }
            catch (ArrayIndexOutOfBoundsException e) {
                textColor = Color.WHITE;
            }
            catch(NullPointerException e){
                System.out.println("Gamemode Not Found!");
            }


        String finalTeamNumber = teamNumber;
        runOnUiThread(() -> {

                ((TextView) findViewById(R.id.mode)).setText(this.gameState);
                ((TextView) findViewById(R.id.mode)).setTextColor(this.textColor);
                ((TextView) findViewById(R.id.teamNum)).setTextColor(this.textColor);
                ((TextView) findViewById(R.id.teamNum)).setText(finalTeamNumber);

            });

        }}

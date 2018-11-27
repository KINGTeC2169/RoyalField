package com.team2169.reftablet;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    private int standing_relics = 0;
    private int tipped_relics = 0;
    private int flags = 0;
    private String connection = "0";
    private String gameState;

    private void inThread(Socket s){
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader((s.getInputStream())));
        } catch (IOException e) {
            e.printStackTrace();
        }

        while(true){
            String data;
            try {
                if((data = in.readLine()) != null){
                    incomingData = data;
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

    private void outThread(Socket s){
        PrintWriter out = null;
        try {
            out = new PrintWriter(s.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while(true){
            out.println("JTB;"+ flags+ ";" + tipped_relics + ";" + standing_relics + ";");
            out.flush();
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
        Thread out = new Thread(() -> outThread(s));
        Thread in = new Thread(() -> inThread(s));
        in.start();
        out.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Thread t = new Thread(this::attemptToConnect);
        t.start();

        Thread v = new Thread(this::visualsUpdate);
        v.start();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread run = new Thread(() -> visualsUpdate());
        run.start();

        runOnUiThread(new Runnable() {

            @Override
            public void run() {

                ((TextView) findViewById(R.id.mode)).setText(gameState);
                ((TextView) findViewById(R.id.mode)).setTextColor(textColor);

            }
        });

        Button add_standing_relic = findViewById(R.id.standingAdd);
        add_standing_relic.setOnClickListener(view -> {
            if (standing_relics + tipped_relics != 2) standing_relics++;
            ((TextView) findViewById(R.id.standingVal)).setText(Integer.toString(standing_relics));
        });

        Button add_tipped_relic = findViewById(R.id.fallenAdd);
        add_tipped_relic.setOnClickListener(view -> {
            if (standing_relics + tipped_relics != 2) tipped_relics++;
            ((TextView) findViewById(R.id.fallenVal)).setText(Integer.toString(tipped_relics));
        });

        Button remove_standing_relic = findViewById(R.id.standingSub);
        remove_standing_relic.setOnClickListener(view -> {
            if (standing_relics > 0) standing_relics--;
            ((TextView) findViewById(R.id.standingVal)).setText(Integer.toString(standing_relics));
        });

        Button remove_tipped_relic = findViewById(R.id.fallenSub);
        remove_tipped_relic.setOnClickListener(view -> {
            if (tipped_relics > 0) tipped_relics--;
            ((TextView) findViewById(R.id.fallenVal)).setText(Integer.toString(tipped_relics));
        });

        Button addFlag = findViewById(R.id.flagsAdd);
        addFlag.setOnClickListener(view -> {
            if (flags + 1 <= 2) flags++;
            ((TextView) findViewById(R.id.flagsVal)).setText(Integer.toString(flags));
        });

        Button subtractFlag = findViewById(R.id.flagsSub);
        subtractFlag.setOnClickListener(view -> {
            if (flags - 1 >= 0) flags--;
            ((TextView) findViewById(R.id.flagsVal)).setText(Integer.toString(flags));
        });


    }

    public void visualsUpdate(){

        while(true){
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
         * */

            System.out.println("Receiving");
            String inputs[] = incomingData.split(";", 2);
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
            }
            catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
                textColor = Color.WHITE;
            }

        }}}

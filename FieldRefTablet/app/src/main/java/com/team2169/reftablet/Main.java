package com.team2169.reftablet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Main extends AppCompatActivity {

    /*
    * connection can be set to three possible values
    * 0 means that no alliance has been selected
    * r means that the tablet is connected to the red alliance
    * b connected to the blue alliance
    * */

    private int standing_relics = 0;
    private int tipped_relics = 0;
    private String connection = "0";

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    private boolean blueTaken;
    private boolean redTaken;

    private String gameState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button add_standing_relic = findViewById(R.id.add_standing_relic);
        add_standing_relic.setOnClickListener(view -> {
            if(standing_relics + tipped_relics != 2) standing_relics++;
            ((TextView)findViewById(R.id.standing_relics)).setText(Integer.toString(standing_relics));
        });

        Button add_tipped_relic = findViewById(R.id.add_tipped_relic);
        add_tipped_relic.setOnClickListener(view -> {
            if(standing_relics + tipped_relics != 2) tipped_relics++;
            ((TextView)findViewById(R.id.tipped_relics)).setText(Integer.toString(tipped_relics));
        });

        Button remove_standing_relic = findViewById(R.id.remove_standing_relic);
        remove_standing_relic.setOnClickListener(view -> {
            if(standing_relics > 0) standing_relics--;
            ((TextView)findViewById(R.id.standing_relics)).setText(Integer.toString(standing_relics));
        });

        Button remove_tipped_relic = findViewById(R.id.remove_tipped_relic);
        remove_tipped_relic.setOnClickListener(view -> {
            if(tipped_relics > 0) tipped_relics--;
            ((TextView)findViewById(R.id.tipped_relics)).setText(Integer.toString(tipped_relics));
        });

        // Call update every 50 millis
        Thread n1 = new Thread(() -> {
                while(true) {
                    try {
                        Thread.sleep(50);
                        update();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        });

        // Connect to port 90
        Thread n2 = new Thread(() -> {
            try {
                socket = new Socket(InetAddress.getLocalHost().getHostAddress(), 90);
                out = new PrintWriter(new FileOutputStream("output.txt"), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            n1.start();
        });
        n2.start();
    }

    public void update() throws IOException {
        /*
        * Output is in format connection;standingRelics;tippedRelics
        * Connection is the name of the alliance the user has selected (r, b)
        * */

        out.printf("%s;%d;%d", connection, standing_relics, tipped_relics);
        out.flush();

        /*
        * Input will be in format robotsTaken;gameState
        * Alliance is a 2 digit binary code using t and f
        * where t means taken or true, and f means free or false
        * gameState can be set to a value 0-3
        * 0: Pre-game
        * 1: Autonomous
        * 2: TeleOp
        * 3: Endgame
        * */

        String input;
        System.out.println(in.readLine());
        if((input = in.readLine()) != null) {
            System.out.println("Receiving");
            String inputs[] = input.split(";", 2);

            gameState = inputs[1];
            ((TextView) findViewById(R.id.gamestate)).setText(gameState);

            ((TextView) findViewById(R.id.heartbeat)).setText("Device is Connected");
        }else{
            ((TextView) findViewById(R.id.heartbeat)).setText("Device is Disconnected");
        }
    }
}

package com.team2169.reftablet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Main extends AppCompatActivity {

    /*
    * connection can be set to five possible values
    * 00 means that no robot has been selected
    * r1 means that the tablet is connected to the first robot on the red alliance
    * r2 second robot on the red alliance
    * b1 and b2, blue alliance
    * */

    private int minor_penalties = 0;
    private int major_penalties = 0;
    private String connection = "00";

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    private boolean r1Taken;
    private boolean r2Taken;
    private boolean b1Taken;
    private boolean b2Taken;

    private String gameState;

    private Button r1;
    private Button r2;
    private Button b1;
    private Button b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button add_major_penalty = findViewById(R.id.add_major_penalty);
        add_major_penalty.setOnClickListener(view -> {
            major_penalties++;
            ((TextView)findViewById(R.id.major_penalties)).setText(Integer.toString(major_penalties));
        });

        Button add_minor_penalties = findViewById(R.id.add_minor_penalty);
        add_minor_penalties.setOnClickListener(view -> {
            minor_penalties++;
            ((TextView)findViewById(R.id.minor_penalties)).setText(Integer.toString(minor_penalties));
        });

        Button remove_major_penalty = findViewById(R.id.remove_major_penalty);
        remove_major_penalty.setOnClickListener(view -> {
            if(major_penalties > 0) major_penalties--;
            ((TextView)findViewById(R.id.major_penalties)).setText(Integer.toString(major_penalties));
        });

        Button remove_minor_penalty = findViewById(R.id.remove_minor_penalty);
        remove_minor_penalty.setOnClickListener(view -> {
            if(minor_penalties > 0) minor_penalties--;
            ((TextView)findViewById(R.id.minor_penalties)).setText(Integer.toString(minor_penalties));
        });

        r1 = findViewById(R.id.r1);
        r1.setOnClickListener(view -> connection = "r1");

        r2 = findViewById(R.id.r2);
        r2.setOnClickListener(view -> connection = "r2");

        b1 = findViewById(R.id.b1);
        b1.setOnClickListener(view -> connection = "b1");

        b2 = findViewById(R.id.b2);
        b2.setOnClickListener(view -> connection = "b2");

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
                socket = new Socket(InetAddress.getByName("192.168.1.14").getHostAddress(), 90);
                out = new PrintWriter(socket.getOutputStream(), true);
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
        * Output is in format connection;minorPenalties;majorPenalties
        * Connection is the name of the robot the user has selected (r1, r2, b1, b2)
        * */

        out.printf("%s;%d;%d", connection, minor_penalties, major_penalties);
        out.flush();

        /*
        * Input will be in format robotsTaken;gameState
        * robotsTaken is a 4 digit binary code using t and f
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
            r1Taken = inputs[0].charAt(0) == 't';
            r2Taken = inputs[0].charAt(1) == 't';
            b1Taken = inputs[0].charAt(2) == 't';
            b2Taken = inputs[0].charAt(3) == 't';

            r1.setEnabled(false);
            r2.setEnabled(false);
            b1.setEnabled(false);
            b2.setEnabled(false);

            if (!r1Taken) r1.setEnabled(true);
            if (!r2Taken) r2.setEnabled(true);
            if (!b1Taken) b1.setEnabled(true);
            if (!b2Taken) b2.setEnabled(true);

            gameState = inputs[1];
            ((TextView) findViewById(R.id.gamestate)).setText(gameState);

            ((TextView) findViewById(R.id.heartbeat)).setText("Device is Connected");
        }else{
            ((TextView) findViewById(R.id.heartbeat)).setText("Device is Disconnected");
        }
    }
}

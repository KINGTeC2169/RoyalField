package com.team2169.reftablet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Main extends AppCompatActivity {

    private int minor_penalties = 0;
    private int major_penalties = 0;
    private String connection = "00";

    private Socket socket;
    private Scanner scanner;
    private PrintWriter out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button add_major_penalty = findViewById(R.id.add_major_penalty);
        add_major_penalty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                major_penalties++;
                ((TextView)findViewById(R.id.major_penalties)).setText(Integer.toString(major_penalties));
            }
        });

        Button add_minor_penalties = findViewById(R.id.add_minor_penalty);
        add_minor_penalties.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                minor_penalties++;
                ((TextView)findViewById(R.id.minor_penalties)).setText(Integer.toString(minor_penalties));
            }
        });

        Button remove_major_penalty = findViewById(R.id.remove_major_penalty);
        remove_major_penalty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(major_penalties > 0) major_penalties--;
                ((TextView)findViewById(R.id.major_penalties)).setText(Integer.toString(major_penalties));
            }
        });

        Button remove_minor_penalty = findViewById(R.id.remove_minor_penalty);
        remove_minor_penalty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(minor_penalties > 0) minor_penalties--;
                ((TextView)findViewById(R.id.minor_penalties)).setText(Integer.toString(minor_penalties));
            }
        });

        Button r1 = findViewById(R.id.r1);
        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connection = "r1";
            }
        });

        Button r2 = findViewById(R.id.r2);
        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connection = "r2";
            }
        });

        Button b1 = findViewById(R.id.b1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connection = "b1";
            }
        });

        Button b2 = findViewById(R.id.b2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connection = "b2";
            }
        });

        try {
            socket = new Socket(InetAddress.getLocalHost().getHostAddress(), 90);
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        scanner = new Scanner(System.in);

        Thread n = new Thread(() -> {
            while(true){
                update();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        n.start();
    }

    public void update(){
        String input = scanner.nextLine();
        out.printf("%s;%i;%i", connection, minor_penalties, major_penalties);
        out.flush();
        System.out.println(System.currentTimeMillis());
    }
}

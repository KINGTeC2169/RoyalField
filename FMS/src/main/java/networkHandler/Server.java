package main.java.networkHandler;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

class Server extends Thread{

    private ArrayList<Client> clients = new ArrayList<>();
    private ServerSocket server;

    //Constructor that creates the ServerSocket
    Server() throws IOException {
        server = new ServerSocket(90, 1, InetAddress.getLocalHost());
    }

    //Listener function that grabs new clients and hands them information.
    public void run() {

        // running infinite loop for getting
        // client request
        while (true)
        {
            Socket s = null;

            try
            {
                // socket object to receive incoming client requests
                s = server.accept();

                System.out.println("A new client is connected : " + s);

                // obtaining input and out streams

                System.out.println("Assigning new thread for this client");

                // create a new thread object
                Thread t = new Client(s);

                // Invoking the start() method
                t.start();

            }
            catch (Exception e){
                try {
                    s.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                e.printStackTrace();
            }
        }

    }

    private Client checkIfClientExists(String ip){
        //Search the list of clients
        for(Client cl:clients){
            //If the IP is found
            if(ip.equals(cl.getIP())){
                //If the client hadn't disconnected
                if(!cl.isHasDisconnected()) {
                    System.out.println("Client Already Connected!");
                    //Client never left.
                    return cl;
                }
                else{
                    //Client left and came back
                    System.out.println("Client Reconnecting!  Welcome Back!");
                    return cl;
                }

            }
        }
        //Conditions weren't met.  Don't return any userdata
        return null;
    }
}

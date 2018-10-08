package main.java.networkHandler;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

class Server {

    private ArrayList<Client> clients = new ArrayList<>();
    private ServerSocket server;

    //Constructor that creates the ServerSocket
    Server() throws IOException {
        server = new ServerSocket(90, 1, InetAddress.getLocalHost());
    }

    Thread l = new Thread(){
        void listenerSpawn() throws IOException {
            listen();
        }
    };

    //Listener function that grabs new clients and hands them information.
    void listen() throws IOException {
        //When client connects, accept them.
        Socket client = server.accept();
        client.setKeepAlive(true);

        //Donate this thread to the client and spawn a new one for listening.
        Thread t = new Thread(() -> {
            try {
                System.out.println("Grabbed New Client: " + client.getInetAddress().getHostAddress());
                listen();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        t.start();

        //Check database for this client to see if they have already connected at some point during this session.
        String ip = client.getInetAddress().getHostAddress();
        if(checkIfClientExists(ip) == null){
            //Client Doesn't Exists, make a new one and add it to the array
            clients.add(new Client(client));
        }
        else{
            //Client exists!  Give them their old info back
            Client c = checkIfClientExists(ip);
            assert c != null;
            System.out.println("Client " + c.getUUID() + " already exists!");
        }

    }

    //EXPERIMENTAL: Thread for starting listener thread.
    void startListening(){
        l.start();
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

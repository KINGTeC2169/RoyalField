package main.java.networkHandler;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

class Server {

    private ArrayList<Client> clients = new ArrayList<>();
    private ServerSocket server;

    Server() throws IOException {
        server = new ServerSocket(90, 1, InetAddress.getLocalHost());
    }

    void listen() throws IOException {
        Socket client = server.accept();
        client.setKeepAlive(true);
        Thread t = new Thread(() -> {
            try {
                System.out.println("Grabbed New Client: " + client.getInetAddress().getHostAddress());
                listen();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        t.start();

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

    private Client checkIfClientExists(String ip){
        for(Client cl:clients){
            if(ip.equals(cl.getIP())){
                if(!cl.isHasDisconnected()) {
                    System.out.println("Client Already Connected!");
                    return cl;
                }
                else{
                    System.out.println("Client Reconnecting!  Welcome Back!");
                    return cl;
                }

            }
        }
        return null;
    }
}

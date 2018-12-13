package main.java.networkHandler;

import main.java.networkHandler.sensorHandler.SensorUnit;
import main.java.networkHandler.tabletHandler.FieldTablet;
import main.java.networkHandler.tabletHandler.RobotTablet;
import main.java.networkHandler.tabletHandler.TabletManager;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

class ClientServer extends Thread{

    private ServerSocket server;

    //Constructor that creates the ServerSocket
    ClientServer() throws IOException {
        server = new ServerSocket(2169);
    }

    //Listener function that grabs new clients and hands them information.
    public void run() {

        // running infinite loop for getting
        // clientBase request
        while (System.currentTimeMillis() > 0)
        {
            Socket s = null;

            try
            {
                // socket object to receive incoming clientBase requests
                s = server.accept();

                System.out.println("[INFO] A new client is connected : " + s);

                // obtaining input and out streams

                BufferedReader in;

                try {
                    in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    String data;

                    while (true) {
                        //Check if we've got new data from our clientBase.
                        if ((data = in.readLine()) != null) {
                            if(data.startsWith("JTB")){
                                System.out.println("[INFO] Recieved Request for new RobotTablet");
                                RobotTablet t = new RobotTablet(s);
                                TabletManager.addRobotTablet(t);
                                break;
                            }
                            else if(data.startsWith("FTB")){
                                System.out.println("[INFO] Recieved Request for new FieldTablet");
                                TabletManager.addFieldTablet(new FieldTablet(s));
                                break;
                            }
                            else if(data.startsWith("FMB")){
                                System.out.println("Connected to Field Management Box");
                                new SensorUnit(s);
                                break;
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            catch (Exception e){
                try {
                    assert s != null;
                    s.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                e.printStackTrace();
            }
        }

    }

}

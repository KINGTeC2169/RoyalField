package main.java.networkHandler;

import java.io.IOException;

public class NetworkMain {

    public static void start() {
        //Create an empty ClientServer object.
        ClientServer m = null;

        //Try to create the server.
        try {
            m = new ClientServer();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Start a listener on the server.
        assert m != null;
        m.start();
    }

}

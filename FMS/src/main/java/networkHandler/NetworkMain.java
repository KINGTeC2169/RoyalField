package main.java.networkHandler;

import java.io.IOException;

public class NetworkMain {

    public static void start() throws IOException {
        //Create an empty Server object.
        Server m = null;

        //Try to create the server.
        try {
            m = new Server();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Start a listener on the server.
        assert m != null;
        m.start();
    }

}

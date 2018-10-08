package main.java.networkHandler;

import java.io.IOException;

public class NetworkMain {

    public static void start(){
        Server m = null;
        try {
            m = new Server();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            assert m != null;
            m.listen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

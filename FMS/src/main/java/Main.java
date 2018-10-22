package main.java;

import main.java.UI.UIMain;
import main.java.networkHandler.NetworkMain;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        //Launch the Network System.
        NetworkMain.start();

        //Launch the UI system
        UIMain.start(args);

    }

    public static int shutdown(){
        return 0;
    }

}

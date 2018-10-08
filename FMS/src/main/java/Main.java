package main.java;

import main.java.UI.UIMain;
import main.java.networkHandler.NetworkMain;

public class Main {

    public static void main(String[] args) {

        NetworkMain.start();
        UIMain.start(args);

    }

}

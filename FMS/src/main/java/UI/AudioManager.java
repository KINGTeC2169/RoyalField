package main.java.UI;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AudioManager {


    public static void playCharge(){
        playSound("src/bin/media/sounds/charge.mp3");
    }

    public static void playTeleSound(){
        playSound("src/bin/media/sounds/tele.mp3");
    }

    public static void playFail(){
        playSound("src/bin/media/sounds/fail.mp3");
    }

    public static void playEnd(){
        playSound("src/bin/media/sounds/end.mp3");
    }

    private static void playSound(String file)
    {
        Thread playSound = new Thread(() -> {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Player playMP3;
            try {
                assert fis != null;
                playMP3 = new Player(fis);
                playMP3.play();
            } catch (JavaLayerException e) {
                e.printStackTrace();
            }
        });
        playSound.start();
    }
}

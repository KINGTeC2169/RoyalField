package main.java.UI;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AudioManager {


    public static void playCharge(){
        playSound("src/bin/media/sounds/charge.mp3");
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

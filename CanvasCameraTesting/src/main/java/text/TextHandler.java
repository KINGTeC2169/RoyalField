package main.java.text;

import javafx.scene.layout.Pane;

class TextHandler {

    //General Data
    private ScoreText gameMode = new ScoreText(1920f/2f,200);
    private ScoreText time = new ScoreText(1920f/2f,100);

    //Blue Data
    private ScoreText blueScore = new ScoreText(1250,200, 100);
    private ScoreText blueRelic = new ScoreText(1725,215);
    private ScoreText bluePingPong = new ScoreText(1510,205);
    private ScoreText blueFlag = new ScoreText(1725,100);

    //Red Data
    private ScoreText redScore = new ScoreText(680,200, 100);
    private ScoreText redRelic = new ScoreText(200,215);
    private ScoreText redPingPong = new ScoreText(425,205);
    private ScoreText redFlag = new ScoreText(200,100);

    void init(Pane p){

        //Initialize All Text
        time.updateText("2:15");
        gameMode.updateText("AUTO");
        blueScore.updateText("420");
        blueRelic.updateText("1");
        bluePingPong.updateText("100");
        blueFlag.updateText("2");
        redScore.updateText("69");
        redRelic.updateText("0");
        redPingPong.updateText("102");
        redFlag.updateText("1");
        p.getChildren().addAll(gameMode, time, blueScore, blueRelic, bluePingPong, blueFlag, redScore, redRelic, redPingPong, redFlag);
    }
}

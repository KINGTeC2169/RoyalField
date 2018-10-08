package main.java.UI.text;

import javafx.scene.layout.Pane;

class TextHandler {

    //General Data
    private ScoreText gameMode = new ScoreText(1920f/2f,200);
    private ScoreText time = new ScoreText(1920f/2f,100);

    //Blue Data
    private ScoreText blueScore = new ScoreText(1250,200, 100);
    private ScoreText blueRelic = new ScoreText(1725,215);
    private ScoreText bluePingPong = new ScoreText(1502,205);
    private ScoreText blueFlag = new ScoreText(1725,100);

    //Red Data
    private ScoreText redScore = new ScoreText(680,200, 100);
    private ScoreText redRelic = new ScoreText(200,215);
    private ScoreText redPingPong = new ScoreText(421,205);
    private ScoreText redFlag = new ScoreText(200,100);

    void init(Pane p){

        //Initialize All Text
        time.updateText("00:00");
        gameMode.updateText("INIT");
        blueScore.updateText("000");
        blueRelic.updateText("0");
        bluePingPong.updateText("000");
        blueFlag.updateText("0");
        redScore.updateText("000");
        redRelic.updateText("0");
        redPingPong.updateText("000");
        redFlag.updateText("0");
        p.getChildren().addAll(gameMode, time, blueScore, blueRelic, bluePingPong, blueFlag, redScore, redRelic, redPingPong, redFlag);
    }

    void update(){
        gameMode.updateText(UIStateMachine.getGameMode());
        time.updateText(UIStateMachine.getTime());
        blueScore.updateText("" + UIStateMachine.getBlueScore());
        blueRelic.updateText("" + UIStateMachine.getBlueRelic());
        bluePingPong.updateText("" + UIStateMachine.getBluePingPong());
        blueFlag.updateText("" + UIStateMachine.getBlueFlag());
        redScore.updateText("" + UIStateMachine.getRedScore());
        redRelic.updateText("" + UIStateMachine.getRedRelic());
        redPingPong.updateText("" + UIStateMachine.getRedPingPong());
        redFlag.updateText("" + UIStateMachine.getRedFlag());


    }

}
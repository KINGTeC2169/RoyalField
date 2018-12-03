package main.java.UI.text;

import javafx.scene.layout.Pane;

class TextHandler {

    //General Data
    private ScoreText gameMode = new ScoreText(1920f/2f,200);
    private ScoreText time = new ScoreText(1920f/2f,100);

    //Blue Data
    private ScoreText blueScore = new ScoreText(1250,200, 100);
    private ScoreText blueStandingRelic = new ScoreText(1752,208);
    private ScoreText blueFallenRelic = new ScoreText(1487,208);
    private ScoreText bluePingPong = new ScoreText(1487,92);
    private ScoreText blueFlag = new ScoreText(1752,92);

    //Red Data
    private ScoreText redScore = new ScoreText(680,200, 100);
    private ScoreText redStandingRelic = new ScoreText(173,208);
    private ScoreText redFallenRelic = new ScoreText(436,208);
    private ScoreText redPingPong = new ScoreText(436,92);
    private ScoreText redFlag = new ScoreText(173,92);

    void init(Pane p){

        //Initialize All Text
        time.updateText("00:00");
        gameMode.updateText("INIT");
        blueScore.updateText("0");
        blueStandingRelic.updateText("0");
        blueFallenRelic.updateText("0");
        bluePingPong.updateText("0");
        blueFlag.updateText("0");
        redScore.updateText("0");
        redStandingRelic.updateText("0");
        redFallenRelic.updateText("0");
        redPingPong.updateText("0");
        redFlag.updateText("0");
        p.getChildren().addAll(gameMode, time, blueScore, blueStandingRelic, blueFallenRelic, bluePingPong, blueFlag, redScore, redStandingRelic, redFallenRelic, redPingPong, redFlag);
    }

    void update(){
        gameMode.updateText(UIStateMachine.getGameMode());
        time.updateText(UIStateMachine.getTime());
        blueScore.updateText("" + UIStateMachine.getBlueScore());
        blueStandingRelic.updateText("" + UIStateMachine.getBlueStandingRelic());
        blueFallenRelic.updateText("" + UIStateMachine.getBlueFallenRelic());
        bluePingPong.updateText("" + UIStateMachine.getBluePingPong());
        blueFlag.updateText("" + UIStateMachine.getBlueFlag());
        redScore.updateText("" + UIStateMachine.getRedScore());
        redStandingRelic.updateText("" + UIStateMachine.getRedStandingRelic());
        redFallenRelic.updateText("" + UIStateMachine.getRedFallenRelic());
        redPingPong.updateText("" + UIStateMachine.getRedPingPong());
        redFlag.updateText("" + UIStateMachine.getRedFlag());


    }

}
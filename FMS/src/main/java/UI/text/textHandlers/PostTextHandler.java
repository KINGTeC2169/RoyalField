package main.java.UI.text.textHandlers;

import javafx.scene.layout.Pane;
import main.java.UI.text.ScoreText;
import main.java.UI.text.UIStateMachine;

public class PostTextHandler extends TextHandler{

    //General Data
    private ScoreText redScore = new ScoreText(470,395, 250);
    private ScoreText redWinLoss = new ScoreText(243,640, 175);
    private ScoreText redElementOne = new ScoreText(512, 525, 40, true);
    private ScoreText redElementTwo = new ScoreText(512, 587, 40, true);
    private ScoreText redElementThree = new ScoreText(512, 649, 40, true);
    private ScoreText redElementOneVal = new ScoreText(728, 525, 40, true);
    private ScoreText redElementTwoVal = new ScoreText(728, 587, 40, true);
    private ScoreText redElementThreeVal = new ScoreText(728, 649, 40, true);
    private ScoreText redTeamOne = new ScoreText(370, 790, 70, true);
    private ScoreText redTeamTwo  = new ScoreText(370, 930, 70, true);
    private ScoreText redTeamOneRank = new ScoreText(1640-960, 790, 70, true);
    private ScoreText redTeamTwoRank  = new ScoreText(1640-960, 930, 70, true);
    private ScoreText blueScore = new ScoreText(1430,395, 250);
    private ScoreText blueWinLoss = new ScoreText(1677,640, 175);
    private ScoreText blueElementOne = new ScoreText(1250, 525, 40, true);
    private ScoreText blueElementTwo = new ScoreText(1250, 587, 40, true);
    private ScoreText blueElementThree = new ScoreText(1250, 649, 40, true);
    private ScoreText blueElementOneVal = new ScoreText(1468, 525, 40, true);
    private ScoreText blueElementTwoVal = new ScoreText(1468, 587, 40, true);
    private ScoreText blueElementThreeVal = new ScoreText(1468, 649, 40, true);
    private ScoreText blueTeamOne = new ScoreText(1330, 790, 70, true);
    private ScoreText blueTeamTwo  = new ScoreText(1330, 930, 70, true);
    private ScoreText blueTeamOneRank = new ScoreText(1640, 790, 70, true);
    private ScoreText blueTeamTwoRank  = new ScoreText(1640, 930, 70, true);


    public void init(Pane p){

        //Initialize All Text
        update();
        p.getChildren().addAll(redScore, redWinLoss, redElementOne, redElementTwo, redElementThree,redElementOneVal, redElementTwoVal, redElementThreeVal,
                redTeamOne, redTeamTwo,redTeamOneRank, redTeamTwoRank, blueScore, blueWinLoss, blueElementOne, blueElementTwo, blueElementThree,
                blueElementOneVal, blueElementTwoVal, blueElementThreeVal, blueTeamOne, blueTeamTwo, blueTeamOneRank, blueTeamTwoRank);
    }

    public void update(){

        redScore.updateText(UIStateMachine.getRedScore() + "");
        redWinLoss.updateText(UIStateMachine.Results.getBlueWLT());
        redElementOne.updateText("Moon Rocks");
        redElementTwo.updateText("Flags");
        redElementThree.updateText("Aliens");
        redElementOneVal.updateText(UIStateMachine.getRedMoonRocks() + "");
        redElementTwoVal.updateText(UIStateMachine.getRedFlag() + "");
        redElementThreeVal.updateText(UIStateMachine.getRedStandingRelic()
                + " : " + UIStateMachine.getRedFallenRelic());
        redTeamOne.updateText(UIStateMachine.Results.getRedOneNum() + "");
        redTeamTwo.updateText(UIStateMachine.Results.getRedTwoNum() + "");
        redTeamOneRank.updateText(UIStateMachine.Results.getRedOneRank());
        redTeamTwoRank.updateText(UIStateMachine.Results.getRedTwoRank());
        blueScore.updateText(UIStateMachine.getBlueScore() + "");
        blueWinLoss.updateText(UIStateMachine.Results.getBlueWLT());
        blueElementOne.updateText("Moon Rocks");
        blueElementTwo.updateText("Flags");
        blueElementThree.updateText("Aliens");
        blueElementOneVal.updateText(UIStateMachine.getBlueMoonRocks() + "");
        blueElementTwoVal.updateText(UIStateMachine.getBlueFlag() + "");
        blueElementThreeVal.updateText(UIStateMachine.getBlueStandingRelic()
                + " : " + UIStateMachine.getBlueFallenRelic());
        blueTeamOne.updateText(UIStateMachine.Results.getBlueOneNum() + "");
        blueTeamTwo.updateText(UIStateMachine.Results.getBlueTwoNum() + "");
        blueTeamOneRank.updateText(UIStateMachine.Results.getBlueOneRank());
        blueTeamTwoRank.updateText(UIStateMachine.Results.getBlueTwoRank());

    }

//        Point p = MouseInfo.getPointerInfo().getLocation();
//        System.out.println(p.x + "," + (p.y-1080));


}
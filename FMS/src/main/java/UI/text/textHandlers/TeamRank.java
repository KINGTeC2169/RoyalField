package main.java.UI.text.textHandlers;

import main.java.UI.text.ScoreText;

import java.util.ArrayList;

public class TeamRank {

    private ScoreText teamRank;
    private ScoreText teamNum;
    private ScoreText teamName;

    public TeamRank(int rank, int x){
        int y = 143 + (rank%9) * 96;
        teamNum = new ScoreText(x, y, 50, true);
        teamRank = new ScoreText(x + 550, y, 50, true);
        teamName = new ScoreText(x + 290, y - 5, 35, true);
        teamRank.updateText(rank + "");
    }

    public TeamRank(int rank, int x, int y){
        teamNum = new ScoreText(x, y, 50, true);
        teamRank = new ScoreText(x + 550, y, 50, true);
        teamName = new ScoreText(x + 290, y - 5, 35, true);
        teamRank.updateText(rank + "");
    }

    public void updateData(int num, String name) throws IndexOutOfBoundsException{
        teamNum.updateText(num + "");
        teamName.updateText(name);
    }

    ArrayList<ScoreText> getText(){
        ArrayList<ScoreText> out = new ArrayList<>();
        out.add(teamName);
        out.add(teamNum);
        out.add(teamRank);
        return out;
    }


}

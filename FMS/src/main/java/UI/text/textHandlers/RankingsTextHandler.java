package main.java.UI.text.textHandlers;

import javafx.scene.layout.Pane;
import main.java.UI.text.UIStateMachine;
import main.java.fms.scoring.team.Team;
import main.java.fms.scoring.team.TeamMachine;

import java.util.ArrayList;

public class RankingsTextHandler extends TextHandler {

    ArrayList<TeamRank> teamRanks = new ArrayList<>();

    private TeamRank team1 = new TeamRank(1, 225);
    private TeamRank team2 = new TeamRank(2, 225);
    private TeamRank team3 = new TeamRank(3, 225, 423);
    private TeamRank team4 = new TeamRank(4, 225, 515);
    private TeamRank team5 = new TeamRank(5, 225, 607);
    private TeamRank team6 = new TeamRank(6, 225, 699);
    private TeamRank team7 = new TeamRank(7, 225, 791);
    private TeamRank team8 = new TeamRank(8, 225, 880);
    private TeamRank team9 = new TeamRank(9, 225, 975);

    private TeamRank team10 = new TeamRank(10, 1185);
    private TeamRank team11 = new TeamRank(11, 1185);
    private TeamRank team12 = new TeamRank(12, 1185, 423);
    private TeamRank team13 = new TeamRank(13, 1185, 515);
    private TeamRank team14 = new TeamRank(14, 1185, 607);
    private TeamRank team15 = new TeamRank(15, 1185, 699);
    private TeamRank team16 = new TeamRank(16, 1185, 791);
    private TeamRank team17 = new TeamRank(17, 1185, 880);
    private TeamRank team18 = new TeamRank(18, 1185, 975);


    private Pane p;

    @Override
    public void init(Pane p) {
        teamRanks.add(team1);
        teamRanks.add(team2);
        teamRanks.add(team3);
        teamRanks.add(team4);
        teamRanks.add(team5);
        teamRanks.add(team6);
        teamRanks.add(team7);
        teamRanks.add(team8);
        teamRanks.add(team9);

        teamRanks.add(team10);
        teamRanks.add(team11);
        teamRanks.add(team12);
        teamRanks.add(team13);
        teamRanks.add(team14);
        teamRanks.add(team15);
        teamRanks.add(team16);
        teamRanks.add(team17);
        teamRanks.add(team18);

        for(TeamRank teamRank:teamRanks){
            p.getChildren().addAll(teamRank.getText());
        }
    }

    @Override
    public void update(){

        int i = 0;
        for(Team t: TeamMachine.teamMap){
            teamRanks.get(i).updateData(t.getNumber(), t.getName());
            i++;
        }

    }
}

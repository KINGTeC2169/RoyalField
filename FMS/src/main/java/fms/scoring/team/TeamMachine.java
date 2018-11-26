package main.java.fms.scoring.team;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class TeamMachine {

    private static HashMap<Integer, Team> teamMap = new HashMap<>();

    public static void generateTeamList() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/bin/data/Teams.csv"));

        String line;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            Team t = new Team(Integer.parseInt(values[0]));
            t.setName(values[1]);
            teamMap.put(t.getNumber(), t);
        }
        br.close();
    }

    public static void printTeamList(){
        for(int i = 1; i <= teamMap.size(); i++){
            System.out.println(teamMap.get(i));
        }
    }

    public static void getTeamByRank(int rank){
        
    }

    public static Team getTeam(int num){
        return teamMap.get(num);
    }

    public static void generateRankings(){

    }

    public static void saveTeamList(){

    }

}

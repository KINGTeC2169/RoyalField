package main.java.fms.scoring.team;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class TeamMachine {

    public static ArrayList<Team> teamMap = new ArrayList<>();

    public static void generateTeamList() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/bin/data/Teams.csv"));

        String line;
        int i = 0;
        while ((br.readLine()) != null) {
            if(i != 0){
                System.out.println(i);
                teamMap.add(readTeam(i));
            }
                i++;
        }
        br.close();
    }

    public static void printTeamList(){
        for(int i = 0; i < teamMap.size(); i++){
            System.out.println(teamMap.get(i));
        }
    }

    public static Team readTeam(int number) throws IOException {
        Team t = new Team(number);
        BufferedReader br = new BufferedReader(new FileReader("src/bin/data/Teams.csv"));

        String teamData = "fail";
        String line;
        while ((line = br.readLine()) != null) {
            if(line.startsWith(number + "")){
                System.out.println("This is it: " + line);
                teamData = line;
                break;
            }
        }
        br.close();

        if(teamData.equals("fail")){
            System.out.println("Failed to read team!");
            return new Team(0);
        }

        System.out.println("Teamdata: " + teamData);
        String[] data = teamData.split(",");
        t.setName(data[1]);
        t.setRp(Integer.parseInt(data[2]));
        t.setWins(Integer.parseInt(data[3]));
        t.setLosses(Integer.parseInt(data[4]));
        t.setMoonRocks(Integer.parseInt(data[5]));
        t.setFlags(Integer.parseInt(data[6]));
        t.setFallenRelics(Integer.parseInt(data[7]));
        t.setStandingRelics(Integer.parseInt(data[8]));

        return t;
    }

    public static Team getTeamByRank(int rank){
        return teamMap.get(1 + rank);
    }

    public static Team getTeam(int num){
        for(Team t:teamMap){
            if(t.getNumber() == num){
                return t;
            }
        }
        return null;
    }

    public static void updateRankings() throws IOException {
        Collections.sort(teamMap);
        Collections.reverse(teamMap);
        int i = 1;
        for(Team t:teamMap){
            t.setRanking(i);
            i++;
        }
        saveTeamList();
    }

    public static void saveTeamList() throws IOException {
        System.out.println("[DATABASE] Saving Match Data");
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/bin/data/Teams.csv"));
        writer.write("Number,Name,RP,Win,Loss,MR,Flags,FR,SR");
        writer.newLine();
        for(Team t:teamMap){
            writer.write(t.toCSV());
            writer.newLine();
        }

        writer.close();
    }

}

package main.java.fms.scoring;

import main.java.fms.match.Match;
import main.java.fms.scoring.team.TeamMachine;

import java.io.*;

public class DatabaseHandler {

    private static int currentMatch = 0;

    public FileWriter createPW(){
        try {
            return new FileWriter("src/bin/data/MatchResults.csv",true);
        } catch (IOException e) {
            System.out.println("Error Reading MatchResults.csv! Is it in /bin/data/?");
            e.printStackTrace();
        }
        return null;
    }

    //Save a match onto the disk and add the match data to the respective teams
    public void archiveMatch(Match m) throws IOException {
        FileWriter writer = createPW();
        writer.append(m.toString());
        writer.append('\n');
        writer.flush();
        writer.close();
        m.updateTeamScores();
    }

    //Read the next match from the disk and load it into a Match object
    public Match readNextMatch() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/bin/data/Schedule.csv"));

        String line;
        int i = 0;
        currentMatch++;
        return getMatch(currentMatch, br, i);
    }

    //Read a match by ID from the disk and load it into a Match object
    public Match readMatch(int matchID) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/bin/data/Schedule.csv"));
        String line;
        int i = 0;
        Match values = getMatch(matchID, br, i);
        if (values != null) return values;
        br.close();
        return null;
    }

    private Match getMatch(int matchID, BufferedReader br, int i) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            if(i == matchID){
                String[] values = line.split(",");
                System.out.println("Created Match # " + values[1]);
                br.close();
                return new Match(Integer.parseInt(values[1]), codeToType(values[0]), TeamMachine.getTeam(Integer.parseInt(values[2])), TeamMachine.getTeam(Integer.parseInt(values[3])),
                        TeamMachine.getTeam(Integer.parseInt(values[4])), TeamMachine.getTeam(Integer.parseInt(values[5])));
            }
            else{
                i++;
            }

        }
        br.close();
        return null;
    }


    private Match.MatchType codeToType(String code){
        switch(code){
            case("QUAL"):
                return Match.MatchType.QUAL;
            case("Q1"):
                return Match.MatchType.Q1;
            case("Q2"):
                return Match.MatchType.Q2;
            case("Q3"):
                return Match.MatchType.Q3;
            case("Q4"):
                return Match.MatchType.Q4;
            case("S1"):
                return Match.MatchType.S1;
            case("S2"):
                return Match.MatchType.S2;
            case("F"):
                return Match.MatchType.F;
        }
        return Match.MatchType.QUAL;
    }


}

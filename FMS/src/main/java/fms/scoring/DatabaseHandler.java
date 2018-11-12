package main.java.fms.scoring;

import main.java.fms.match.Match;

import java.io.FileWriter;
import java.io.IOException;

public class DatabaseHandler {


    public FileWriter createPW(){
        try {
            return new FileWriter("bin/data/MatchResults.csv",true);
        } catch (IOException e) {
            System.out.println("Error Reading MatchResults.csv! Is it in /bin/data/?");
            e.printStackTrace();
        }
        return null;
    }

    public void archiveMatch(Match m){
        FileWriter out = createPW();
    }

    public MatchTemplate readMatch(){

        return null;

    }


}

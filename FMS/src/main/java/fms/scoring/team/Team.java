package main.java.fms.scoring.team;

public class Team implements Comparable<Team> {

    Team(int number){
        setNumber(number);
    }

    //Team Data
    private int number;
    private String name;
    private int ranking;

    //Team Score Data
    private int rp = 0;
    private int wins = 0;
    private int losses = 0;
    private int moonRocks = 0;
    private int flags = 0;
    private int fallenRelics = 0;
    private int standingRelics = 0;

    //Getters and Setters
    public int getNumber() {
        return number;
    }

    private void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public int getRp() {
        return rp;
    }

    public void setRp(int rp) {
        this.rp = rp;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getMoonRocks() {
        return moonRocks;
    }

    public void setMoonRocks(int moonRocks) {
        this.moonRocks = moonRocks;
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public int getFallenRelics() {
        return fallenRelics;
    }

    public int getStandingRelics() {
        return standingRelics;
    }

    public void setFallenRelics(int relics) {
        this.fallenRelics = relics;
    }

    public void setStandingRelics(int relics) {
        this.standingRelics = relics;
    }

    public String toString(){
        return number + ": "+ name;
    }

    @Override
    public int compareTo(Team o) {
        if(this.getRp() > o.getRp()){
            return 1;
        }
        else if(this.getRp() < o.getRp()){
            return -1;
        }
        else{
            if(this.getMoonRocks() > o.getMoonRocks()){
                return 1;
            }
            else if(this.getMoonRocks() < o.getMoonRocks()){
                return -1;
            }
            else{
                return Integer.compare(this.getFlags(), o.getFlags());
            }
        }
    }
}

package main.java.fms.scoring.team;

public class Team {

    public Team(int number){
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
    private int relics = 0;

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

    public void setName(String name) {
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

    public int getRelics() {
        return relics;
    }

    public void setRelics(int relics) {
        this.relics = relics;
    }

    public String toString(){
        return number + ": "+ name;
    }

}

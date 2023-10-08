package Server.DataClass;
import Interfaces.CandidateInterf;

public class Candidate implements CandidateInterf {

    static  int currentRank =0;
    int rank;
    String name;

    public Candidate(String name) {
        currentRank+=1;
        this.rank=currentRank;
        this.name = name;
    }

    public String getPitch() {
        return "no pitch for this candidate";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getNumber() {
        return rank;
    }
}

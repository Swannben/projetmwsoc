package Server.DataClass;

public class CandidateText extends Candidate{

    String pitch;
    public CandidateText(String name, String pitch) {
        super(name);
        this.pitch= pitch;
    }

    @Override
    public String getPitch() {
        return pitch;
    }

}

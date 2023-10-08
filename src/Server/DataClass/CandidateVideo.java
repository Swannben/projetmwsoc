package Server.DataClass;

public class CandidateVideo extends Candidate{
    String pitch;
    public CandidateVideo(String name, String pitch) {
        super(name);
        this.pitch= pitch;
    }

    @Override
    public String getPitch() {
        return pitch;
    }
}

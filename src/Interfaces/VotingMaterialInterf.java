package Interfaces;

import java.rmi.Remote;
import java.util.List;

public interface VotingMaterialInterf extends Remote {

    public String giveOTP();

    public Boolean verifyOTP(String otp);

    public boolean alreadyVoted();

    public void processVotes(List<int[]> singleUserVotes);
    public String getIndividualVotes();
}

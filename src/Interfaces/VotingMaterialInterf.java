package Interfaces;

import java.rmi.Remote;
import java.util.List;

public interface VotingMaterialInterf extends Remote {

    public void addNewVoter(int studentNumber);

    public String giveOTP(int clientVoteInterface);

    public void processVotes(List<int[]> singleUserVotes);
}

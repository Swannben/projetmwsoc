package Interfaces;

import java.rmi.Remote;
import java.util.List;

public interface VotingMaterialInterf extends Remote {

    public void addNewVoter(ClientVoteInterface clientVoteInterface);

    public String giveOTP(ClientVoteInterface clientVoteInterface);

    public void processVotes(List<int[]> singleUserVotes);
}

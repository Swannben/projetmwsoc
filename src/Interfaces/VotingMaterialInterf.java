package Interfaces;

import java.rmi.Remote;
import java.util.List;

public interface VotingMaterialInterf extends Remote {

    public String giveOTP();

    public void processVotes(List<int[]> singleUserVotes);
}

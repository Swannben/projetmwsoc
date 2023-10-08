package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ClientVoteInterface extends Remote {

    public List<int[]> Voting(List<CandidateInterf> candidates) throws RemoteException;
}

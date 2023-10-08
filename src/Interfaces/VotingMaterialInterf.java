package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface VotingMaterialInterf extends Remote {

    public String giveOTP() throws RemoteException;

    public Boolean verifyOTP(String otp) throws RemoteException;

    public boolean alreadyVoted() throws RemoteException;

    public void processVotes(List<int[]> singleUserVotes) throws RemoteException;
    public String getIndividualVotes() throws RemoteException;
}

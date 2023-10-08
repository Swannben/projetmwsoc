package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface AuthentificatorIntef extends Remote {
    public boolean authentify(int studentNumber,String password) throws RemoteException;

    public List<CandidateInterf> getCandidateList() throws RemoteException;

    VotingMaterialInterf logIn() throws RemoteException;
}

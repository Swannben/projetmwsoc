package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface DisplayCandidateInterf extends Remote {
    public List<CandidateInterf> getCan() throws RemoteException;

    public String getShownCandidates() throws RemoteException;
}

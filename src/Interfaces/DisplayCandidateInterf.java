package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface DisplayCandidateInterf extends Remote {
    public void DisplayCan() throws RemoteException;

    public List<CandidateInterf> getCan() throws RemoteException;
}

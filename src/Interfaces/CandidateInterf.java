package Interfaces;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CandidateInterf extends  Remote  {
    public String getName() throws RemoteException;
    public int getNumber() throws  RemoteException;

    String getPitch() throws RemoteException;
}

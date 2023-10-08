package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;

public interface AuthentificatorIntef extends Remote {
    public boolean authentify(int studentNumber,String password);

    public List<CandidateInterf> getCandidateList();

    VotingMaterialInterf logIn(Scanner scanner) throws RemoteException;
}

package Interfaces;

import java.rmi.Remote;
import java.util.List;
import java.util.Scanner;

public interface AuthentificatorIntef extends Remote {
    public boolean authentify(int studentNumber,String password);

    public VotingMaterialInterf getOtherRef(int cvi);

    public List<CandidateInterf> getCandidateList();

    VotingMaterialInterf logIn(Scanner scanner);
}

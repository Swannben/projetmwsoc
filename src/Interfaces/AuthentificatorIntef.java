package Interfaces;

import Server.DataClass.Candidate;

import java.rmi.Remote;
import java.util.List;

public interface AuthentificatorIntef extends Remote {
    public boolean authentify(int studentNumber,String password);

    public VotingMaterialInterf getOtherRef(ClientVoteInterface cvi);

    public List<CandidateInterf> getCandidateList();
}

package Server.DataClass;

import Interfaces.CandidateInterf;
import Interfaces.VotingMaterialInterf;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import static Server.ServerMain.*;

public class VotingMaterial extends UnicastRemoteObject implements VotingMaterialInterf {
    private int studentNumber;

    public VotingMaterial(int studentNumber) throws RemoteException {
        super();
        this.studentNumber=studentNumber;
    }

    @Override
    public String giveOTP() {
        return voterAndOTP.get(this.studentNumber);
    }

    @Override
    public void processVotes(List<int[]> singleUserVotes) {
        individualVotes.put(this.studentNumber,singleUserVotes);
        for(int[] voteforCandidate:singleUserVotes){
            CandidateInterf candidateInterf=DisplayCandidate.candidates.stream()
                    .filter(c -> c.getNumber()==voteforCandidate[0])
                    .findFirst().orElseThrow();
            totalVotes.put((Candidate) candidateInterf,totalVotes.get(candidateInterf)+voteforCandidate[1]);
        }
    }
}

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
    public Boolean verifyOTP(String otp) {


        return voterAndOTP.containsKey(studentNumber) && voterAndOTP.get(studentNumber).equals(otp);
    }

    @Override
    public boolean alreadyVoted() {
        return (individualVotes.containsKey(studentNumber));

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

    @Override
    public String getIndividualVotes() {
        String yourVotes="Vous avez voté comme suit: \n";
        for(CandidateInterf candidate:DisplayCandidate.candidates){
            yourVotes+=candidate.getName();
            yourVotes+=" : ";
            yourVotes+=individualVotes.get(this.studentNumber).stream()
                    .filter(vote -> vote[0]==candidate.getNumber())
                    .findFirst().orElseThrow()[1];
            yourVotes+="\n";
        }
        return yourVotes;
    }
}

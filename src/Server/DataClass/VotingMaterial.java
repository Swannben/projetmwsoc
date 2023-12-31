package Server.DataClass;

import Interfaces.CandidateInterf;
import Interfaces.VotingMaterialInterf;
import Server.ServerMain;

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
        if(!individualVotes.containsKey(this.studentNumber)){
            individualVotes.put(this.studentNumber, singleUserVotes);
            for (int[] voteforCandidate : singleUserVotes) {
                    CandidateInterf candidateInterf = DisplayCandidate.candidates.stream()
                            .filter(c -> {
                                try {
                                    return c.getNumber() == voteforCandidate[0];
                                } catch (RemoteException e) {
                                    throw new RuntimeException(e);
                                }
                            })
                            .findFirst().orElseThrow();
                    totalVotes.put((Candidate) candidateInterf, totalVotes.get(candidateInterf) + voteforCandidate[1]);
            }
        }
        else{
            for (int[] voteforCandidate : individualVotes.get(studentNumber)) {
                CandidateInterf candidateInterf = DisplayCandidate.candidates.stream()
                        .filter(c -> {
                            try {
                                return c.getNumber() == voteforCandidate[0];
                            } catch (RemoteException e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .findFirst().orElseThrow();
                totalVotes.put((Candidate) candidateInterf, totalVotes.get(candidateInterf) - voteforCandidate[1]);
            }
            individualVotes.put(this.studentNumber, singleUserVotes);
            for (int[] voteforCandidate : singleUserVotes) {
                CandidateInterf candidateInterf = DisplayCandidate.candidates.stream()
                        .filter(c -> {
                            try {
                                return c.getNumber() == voteforCandidate[0];
                            } catch (RemoteException e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .findFirst().orElseThrow();
                totalVotes.put((Candidate) candidateInterf, totalVotes.get(candidateInterf) + voteforCandidate[1]);
            }
        }
        voterAndOTP.put(this.studentNumber,"alreadyVoted");
    }
    @Override
    public String getIndividualVotes() throws RemoteException {
        String yourVotes="Vous avez voté comme suit: \n";
        for(CandidateInterf candidate:DisplayCandidate.candidates){
            yourVotes+=candidate.getName();
            yourVotes+=" : ";
            yourVotes+=individualVotes.get(this.studentNumber).stream()
                    .filter(vote -> {
                        try {
                            return vote[0]==candidate.getNumber();
                        } catch (RemoteException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .findFirst().orElseThrow()[1];
            yourVotes+="\n";
        }
        return yourVotes;
    }
}

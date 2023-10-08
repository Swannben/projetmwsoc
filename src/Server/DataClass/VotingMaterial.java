package Server.DataClass;

import Interfaces.CandidateInterf;
import Interfaces.ClientVoteInterface;
import Interfaces.VotingMaterialInterf;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static Server.ServerMain.totalVotes;

public class VotingMaterial extends UnicastRemoteObject implements VotingMaterialInterf {
    private Map<ClientVoteInterface,String> voterAndOTP=new HashMap<>();
    String AlphaNumericStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz0123456789";
    private Random random=new Random();

    public VotingMaterial(int port) throws RemoteException {
        super(port);
    }

    private String generateOtp(){
        String otp="";
        for(int i=0;i<10;i++){
            otp+=AlphaNumericStr.charAt(random.nextInt(AlphaNumericStr.length()));
        }
        if(voterAndOTP.values().contains(otp)) return generateOtp();
        return otp;
    }

    @Override
    public void addNewVoter(ClientVoteInterface clientVoteInterface) {
        voterAndOTP.put(clientVoteInterface,generateOtp());
    }

    @Override
    public String giveOTP(ClientVoteInterface clientVoteInterface) {
        return voterAndOTP.get(clientVoteInterface);
    }

    @Override
    public void processVotes(List<int[]> singleUserVotes) {
        for(int[] voteforCandidate:singleUserVotes){
            CandidateInterf candidateInterf=DisplayCandidate.candidates.stream()
                    .filter(c -> c.getNumber()==voteforCandidate[0])
                    .findFirst().orElseThrow();
            totalVotes.put((Candidate) candidateInterf,totalVotes.get(candidateInterf)+voteforCandidate[1]);
        }
    }
}

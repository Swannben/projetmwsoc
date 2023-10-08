package Server.DataClass;

import Interfaces.AuthentificatorIntef;
import Interfaces.CandidateInterf;
import Interfaces.VotingMaterialInterf;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

import static Server.ServerMain.*;

public class Authentificator extends UnicastRemoteObject implements AuthentificatorIntef {
    private Map<Integer,User> usersDB= new HashMap<>();
    String AlphaNumericStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz0123456789";
    private Random random=new Random();

    public Authentificator(int numport) throws RemoteException {
        super(numport);
        for(int i=1;i<=10;i++){
            String password="a".repeat(i);
            this.usersDB.put(i,new User(i,password));
        }
    }

    public boolean authentify(int studentNumber,String password) throws RemoteException{
        if(this.usersDB.containsKey(studentNumber)&&this.usersDB.get(studentNumber).getPassword().equals(password)){
            addNewVoter(studentNumber);
            return true;
        }
        return false;
    }

    @Override
    public VotingMaterialInterf getVotingStub(int studentNumber) throws RemoteException {
        return new VotingMaterial(studentNumber);
    }
    private String generateOtp(){
        String otp="";
        for(int i=0;i<10;i++){
            otp+=AlphaNumericStr.charAt(random.nextInt(AlphaNumericStr.length()));
        }
        if(voterAndOTP.values().contains(otp)) return generateOtp();
        return otp;
    }
    private void addNewVoter(int studentNumber) {
        voterAndOTP.put(studentNumber,generateOtp());
    }


    @Override
    public boolean checkVotingStatus() throws RemoteException {
        return voteIsOngoing;
    }

    @Override
    public String showFinalVotes() throws RemoteException {
        String finalVotes="";
        for(Candidate candidate:totalVotes.keySet()){
            finalVotes+=(candidate.getName()+" : "+totalVotes.get(candidate)+"\n");
        }
        return finalVotes;
    }

    @Override
    public List<CandidateInterf> getCandidateList() throws RemoteException{
        return DisplayCandidate.candidates;
    }

}

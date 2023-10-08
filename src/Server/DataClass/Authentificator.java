package Server.DataClass;

import Interfaces.AuthentificatorIntef;
import Interfaces.CandidateInterf;
import Interfaces.VotingMaterialInterf;
import Server.ServerMain;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

import static Server.ServerMain.voterAndOTP;

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

    public boolean authentify(int studentNumber,String password){
        return this.usersDB.containsKey(studentNumber)&&this.usersDB.get(studentNumber).getPassword().equals(password);
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
    public List<CandidateInterf> getCandidateList() {
        return DisplayCandidate.candidates;
    }

    @Override
    public VotingMaterialInterf logIn(Scanner scanner) throws RemoteException {
        int studentNumber=0;
        String password = "";
        VotingMaterialInterf votingMaterialInterf;
        String otp="";
        System.out.println("Veuillez saisir votre numéro étudiant :");
        if(scanner.hasNextInt()) studentNumber=scanner.nextInt();
        System.out.println("Veuillez saisir votre numéro étudiant :");
        if(scanner.hasNext()) password=scanner.next();
        if(authentify(studentNumber,password)) {
            votingMaterialInterf=new VotingMaterial(studentNumber);
            addNewVoter(studentNumber);
        }else{
            System.out.println("votre mot de pass ou numéro d'étudiant est faux");
            return null;
        }
        System.out.println(studentNumber+ " votre One Time Password(OTP) est"+ otp);
        return votingMaterialInterf;
    }
}

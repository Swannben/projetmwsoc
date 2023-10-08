package Client;

import Interfaces.AuthentificatorIntef;
import Interfaces.ClientVoteInterface;
import Interfaces.VotingMaterialInterf;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ClientMain {

    static Scanner scanner=new Scanner(System.in);


    static boolean isSignedIn=false;
    public static void main(String args[]) throws RemoteException, NotBoundException {

        Registry reg= LocateRegistry.getRegistry(2001);
        AuthentificatorIntef stub=(AuthentificatorIntef) reg.lookup("authentify");


        Registry reg2 = LocateRegistry.createRegistry(2002);
        ClientVoteInterface clientVote = new ClientVote(20001);
        reg2.rebind("VotingSystem",clientVote);



    }

    public static VotingMaterialInterf signIn(AuthentificatorIntef stub){
        int studentNumber=0;
        String password = "";
        System.out.println("Bonjour au systeme de vote PNS \n Pour vous athentifier veuillez saisir votre numéro étudiant :");
        if(scanner.hasNextInt()) studentNumber=scanner.nextInt();
        System.out.println("Veuillez maintenant saisir votre mot de passe :");
        if(scanner.hasNext()) password=scanner.next();
        VotingMaterialInterf votingMaterialInterf;
        String otp="";
        if(stub.authentify(studentNumber,password)) {
            votingMaterialInterf=stub.getOtherRef(studentNumber);
            otp= votingMaterialInterf.giveOTP(studentNumber);
            isSignedIn=true;
        }else{
            System.out.println("votre mot de pass ou numéro d'étudiant est faux");
            isSignedIn=false;
            return null;
        }

        System.out.println(studentNumber+ " votre One Time Password(OTP) est"+ otp);
        return votingMaterialInterf;
    }
}

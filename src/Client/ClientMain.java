package Client;

import Interfaces.AuthentificatorIntef;
import Interfaces.ClientVoteInterface;
import Interfaces.DisplayCandidateInterf;
import Interfaces.VotingMaterialInterf;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ClientMain {

    static Scanner scanner=new Scanner(System.in);


    static boolean isLoggedIn=false;
    public static void main(String args[]) throws RemoteException, NotBoundException {
        String otp;
        VotingMaterialInterf votingMaterialInterf=null;
        Registry reg= LocateRegistry.getRegistry(2001);
        AuthentificatorIntef stub=(AuthentificatorIntef) reg.lookup("authentify");
        DisplayCandidateInterf displayStub= (DisplayCandidateInterf) reg.lookup("displayCan");
        int switchValue=0;
        while(true){ //potentiellement une interface pourrait nous dire si le vote est fini ?)
            System.out.println("Bienvenue dans le système de vote, pour voir les candidats tapez 1, pour vous connecter tapez 2");
            if (isLoggedIn)
                System.out.print(", pour voter tapez 3");
            if (scanner.hasNextInt()) switchValue=scanner.nextInt();
            switch (switchValue){
                case 1:
                    displayStub.DisplayCan();
                    break;
                case 2:
                    votingMaterialInterf = stub.logIn(scanner);
                    //todo ajouter une vérifiaction pour mettre à jour l'otp et isLoggedIn
                    break;
                case 3:
                    if (isLoggedIn && votingMaterialInterf!=null)
                        Vote(votingMaterialInterf);
                default:
                    System.out.println("vous avez entré un nombre refusé. réessayez");
            }
        }
        /*
        Registry reg2 = LocateRegistry.createRegistry(2002);
        ClientVoteInterface clientVote = new ClientVote(20001);
        reg2.rebind("VotingSystem",clientVote);
        */


    }

    private static void Vote(VotingMaterialInterf votingMaterialInterf) {
    }
/*
    public static VotingMaterialInterf signIn(AuthentificatorIntef stub){
        int studentNumber=0;
        String password = "";
        System.out.println("Bonjour au systeme de vote PNS \n Pour vous athentifier veuillez saisir votre numéro étudiant :");
        if(scanner.hasNextInt()) studentNumber=scanner.nextInt();
        stub.signIn();
        VotingMaterialInterf votingMaterialInterf;
        otp="";
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
 */
}

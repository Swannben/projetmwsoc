package Client;

import Interfaces.AuthentificatorIntef;
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
                    if(votingMaterialInterf!=null)otp=votingMaterialInterf.giveOTP();
                    break;
                case 3:
                    if (isLoggedIn && votingMaterialInterf!=null)
                        Vote(votingMaterialInterf,displayStub);
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

    private static void Vote(VotingMaterialInterf votingMaterialInterf, DisplayCandidateInterf displayStub) throws RemoteException {
        //todo ajouter la fonction qui vérifie l'otp
        ClientVote vote = new ClientVote(20002);
        ;
        votingMaterialInterf.processVotes(vote.Voting(displayStub.getCan()));
    }
/*

 */
}

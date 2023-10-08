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
            System.out.println("Bienvenue dans le système de vote, pour voir les candidats tapez 1,");
            if (isLoggedIn) {
                System.out.print(" pour changer de compte tapez 2, pour voter tapez 3, pour consulter votre vote tapez 4");
            }
            else{
                System.out.print(" pour vous connecter tapez 2");
            }
            if (scanner.hasNextInt()) switchValue=scanner.nextInt();
            switch (switchValue){
                case 1:
                    displayStub.DisplayCan();
                    break;
                case 2:
                    votingMaterialInterf = stub.logIn(scanner);
                    if(votingMaterialInterf!=null){
                        otp=votingMaterialInterf.giveOTP();
                        isLoggedIn=true;
                    }
                    break;
                case 3:
                    if (isLoggedIn && votingMaterialInterf!=null)
                        Vote(votingMaterialInterf,displayStub);
                    else{
                        System.out.println("vous avez entré un nombre refusé. réessayez");
                    }
                    break;
                case 4:
                    if(isLoggedIn && votingMaterialInterf!=null)
                        System.out.println(votingMaterialInterf.getIndividualVotes());
                    else{
                        System.out.println("vous avez entré un nombre refusé. réessayez");
                    }
                    break;
                case 5:
                    if (isLoggedIn)
                        reVote(votingMaterialInterf,displayStub);
                    else
                        System.out.println("vous avez entré un nombre refusé. réessayez");
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

    private static void reVote(VotingMaterialInterf votingMaterialInterf,DisplayCandidateInterf displayStub) throws RemoteException {
        System.out.println("veuillez entrer votre OTP");
        String otp = scanner.next();
        if (votingMaterialInterf.verifyOTP(otp)) {
            ClientVote vote = new ClientVote(20002);
            votingMaterialInterf.processVotes(vote.Voting(displayStub.getCan()));
        } else System.out.println("mauvaise OTP");
    }


    private static void Vote(VotingMaterialInterf votingMaterialInterf, DisplayCandidateInterf displayStub) throws RemoteException {
        if(votingMaterialInterf.alreadyVoted())
            System.out.println("vous avez déjà voté");
        else {
            reVote(votingMaterialInterf,displayStub);
        }
    }
/*

 */
}

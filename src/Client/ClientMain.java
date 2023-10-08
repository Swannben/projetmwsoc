package Client;

import Interfaces.AuthentificatorIntef;
import Interfaces.CandidateInterf;
import Interfaces.DisplayCandidateInterf;
import Interfaces.VotingMaterialInterf;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

public class ClientMain {

    static Scanner scanner=new Scanner(System.in);


    static boolean isLoggedIn=false;
    public static void main(String args[]) throws RemoteException, NotBoundException {
        boolean started=false;
        String otp;
        int studentNumber=0;
        String password = "";
        VotingMaterialInterf votingMaterialInterf=null;
        Registry reg= LocateRegistry.getRegistry(2001);
        AuthentificatorIntef stub=(AuthentificatorIntef) reg.lookup("authentify");
        DisplayCandidateInterf displayStub= (DisplayCandidateInterf) reg.lookup("displayCan");
        int switchValue=0;
        while(true){
            if(stub.checkVotingStatus()) {
                started = true;
                System.out.println("Bienvenue dans le système de vote, pour voir les candidats tapez 1,");
                if (isLoggedIn) {
                    System.out.print(", pour changer de compte ou régénerer un OTP tapez 2, pour voter tapez 3, pour consulter votre vote tapez 4, pour changer votre vote tapez 5 ");
                } else {
                    System.out.print(", pour vous connecter tapez 2");
                }
                if (scanner.hasNextInt()) switchValue = scanner.nextInt();
                switch (switchValue) {
                    case 1:
                        System.out.println(displayStub.getShownCandidates());
                        break;
                    case 2:
                        Scanner scanner = new Scanner(System.in);
                        System.out.println("Veuillez saisir votre numéro étudiant :");
                        if (scanner.hasNextInt()) studentNumber = scanner.nextInt();
                        System.out.println("Veuillez saisir votre mot de passe :");
                        if (scanner.hasNext()) password = scanner.next();
                        if (stub.authentify(studentNumber, password)){
                            votingMaterialInterf = stub.getVotingStub(studentNumber);
                        }
                        else System.out.println("Numéro étudiant ou mot de passe incorrect");
                        if (votingMaterialInterf != null) {
                            otp = votingMaterialInterf.giveOTP();
                            System.out.println(studentNumber + " votre One Time Password(OTP) est : " + votingMaterialInterf.giveOTP());
                            isLoggedIn = true;
                        }
                        break;
                    case 3:
                        if (isLoggedIn && votingMaterialInterf != null)
                            Vote(votingMaterialInterf, displayStub);
                        else {
                            System.out.println("vous avez entré un nombre refusé. réessayez");
                        }
                        break;
                    case 4:
                        if (isLoggedIn && votingMaterialInterf != null)
                            System.out.println(votingMaterialInterf.getIndividualVotes());
                        else {
                            System.out.println("vous avez entré un nombre refusé. réessayez");
                        }
                        break;
                    case 5:
                        if (isLoggedIn)
                            reVote(votingMaterialInterf, displayStub);
                        else
                            System.out.println("vous avez entré un nombre refusé. réessayez");
                    default:
                        System.out.println("vous avez entré un nombre refusé. réessayez");
                }
            }
            if(!stub.checkVotingStatus() && started){
                System.out.println("Le vote est maintenant clos, voici les résultats :\n"+stub.showFinalVotes());
                break;
            }
        }

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

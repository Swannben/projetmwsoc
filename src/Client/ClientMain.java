package Client;

import Interfaces.AuthentificatorIntef;
import Interfaces.ClientVoteInterface;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ClientMain {

    public static void main(String args[]) throws RemoteException, NotBoundException {
        Scanner scanner=new Scanner(System.in);
        int studentNumber=0;
        String password="";
        Registry reg= LocateRegistry.getRegistry(2001);
        AuthentificatorIntef stub=(AuthentificatorIntef) reg.lookup("authentify");
        System.out.println("Bonjour au systeme de vote PNS \n Pour vous athentifier veuillez saisir votre numéro étudiant :");
        if(scanner.hasNextInt()) studentNumber=scanner.nextInt();
        System.out.println("Veuillez maintenant saisir votre mot de passe :");
        if(scanner.hasNext()) password=scanner.next();
        //if(stub.authentify(studentNumber,password) renvoyer voting material;

        Registry reg2 = LocateRegistry.createRegistry(2002);
        ClientVoteInterface clientVote = new ClientVote(20001);
        reg2.rebind("VotingSystem",clientVote);
    }
}

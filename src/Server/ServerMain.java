package Server;

import Interfaces.*;
import Server.DataClass.Authentificator;
import Server.DataClass.Candidate;
import Server.DataClass.DisplayCandidate;
import Server.DataClass.VotingMaterial;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class ServerMain {
    public static VotingMaterialInterf votingMaterialInterf;
    public static Map<Candidate,Integer> totalVotes=new HashMap<>();

    static {
        try {
            votingMaterialInterf = new VotingMaterial(10003);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String args[]) throws RemoteException, NotBoundException {
        Scanner scanner =new Scanner(System.in);
        System.out.println("type start to begin the vote");
        while (!scanner.next().equals("start"));


        for(CandidateInterf c:DisplayCandidate.candidates){
            totalVotes.put((Candidate) c,0);
        }
        Registry reg= LocateRegistry.createRegistry(2001);
        AuthentificatorIntef auth= new Authentificator(10001);
        reg.rebind("authentify",auth);
        DisplayCandidateInterf dispCan= new DisplayCandidate(10002);
        reg.rebind("displayCan",dispCan);


        Registry reg2 = LocateRegistry.getRegistry(2002);
        ClientVoteInterface cVote = (ClientVoteInterface) reg2.lookup("VotingSystem");

    }
}

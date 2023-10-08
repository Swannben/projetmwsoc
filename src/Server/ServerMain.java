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
import java.util.List;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class ServerMain {
    public static Map<Candidate,Integer> totalVotes=new HashMap<>();
    public static Map<Integer, List<int[]>> individualVotes=new HashMap<>();
    public static Map<Integer,String> voterAndOTP=new HashMap<>();
    public static boolean voteIsOngoing=false;

    public static void main(String args[]) throws RemoteException, NotBoundException {
        Scanner scanner =new Scanner(System.in);

        for(CandidateInterf c:DisplayCandidate.candidates){
            totalVotes.put((Candidate) c,0);
        }
        Registry reg= LocateRegistry.createRegistry(2001);
        AuthentificatorIntef auth= new Authentificator(1001);
        reg.rebind("authentify",auth);
        DisplayCandidateInterf dispCan= new DisplayCandidate(10002);
        reg.rebind("displayCan",dispCan);

        System.out.println("type start to begin the vote");
        while (!voteIsOngoing) voteIsOngoing=scanner.next().equals("start");

        System.out.println("type stop to end the vote");
        while(voteIsOngoing) voteIsOngoing=scanner.next().equals("stop");

    }
}

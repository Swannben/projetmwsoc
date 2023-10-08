package Client;

import Interfaces.CandidateInterf;
import Interfaces.ClientVoteInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientVote extends UnicastRemoteObject implements ClientVoteInterface {



    ClientVote(int numport) throws RemoteException {
        super(numport);

    }


    @Override
    public List<int[]> Voting(List<CandidateInterf> candidates) throws RemoteException {
        Scanner scanner = new Scanner(System.in);
        List<int[]> votes = new ArrayList<>();
        for (CandidateInterf c : candidates){
            System.out.println("how many votes to give to "+c.getName()+" ?");
            int[] valueToAdd={c.getNumber(),-1};
            int vote= scanner.nextInt();
            while (vote < 0 || vote > 3) {
                System.out.println("a vote should be between 0 and 3");
                vote= scanner.nextInt();
            }
            valueToAdd[1]=vote;
            votes.add(valueToAdd);
        }
        return votes;
    }
}

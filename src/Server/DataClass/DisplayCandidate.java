package Server.DataClass;

import Interfaces.DisplayCandidateInterf;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class DisplayCandidate extends UnicastRemoteObject implements DisplayCandidateInterf {

    List<Candidate> candidates;

    public DisplayCandidate(int port) throws RemoteException {
        super(port);
        candidates.add(new CandidateText("Jean Poule", "avec Jean Poule ça va pouler"));
        candidates.add(new Candidate("Sam Grisard"));
        candidates.add(new CandidateVideo("Lex Luthor", "https://www.youtube.com/watch?v=wFHGnA98jDE"));
    }

    @Override
    public void DisplayCan() {
        for(Candidate c : candidates){
            System.out.println("candidate number "+ c.getNumber() +": "+c.getName()+"\n son pitch est"+c.getPitch());
        }
    }
}

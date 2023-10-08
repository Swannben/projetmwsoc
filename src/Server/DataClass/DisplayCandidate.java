package Server.DataClass;

import Interfaces.CandidateInterf;
import Interfaces.DisplayCandidateInterf;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class DisplayCandidate extends UnicastRemoteObject implements DisplayCandidateInterf {

    public static List<CandidateInterf> candidates = new ArrayList<>();

    public DisplayCandidate(int port) throws RemoteException {
        super(port);
        candidates.add(new CandidateText("Jean Poule", "avec Jean Poule ça va pouler"));
        candidates.add(new Candidate("Sam Grisard"));
        candidates.add(new CandidateVideo("Lex Luthor", "https://www.youtube.com/watch?v=wFHGnA98jDE"));
    }


    @Override
    public List<CandidateInterf> getCan() throws RemoteException{

        return candidates;
    }

    @Override
    public String getShownCandidates() throws RemoteException {
        String result="";
        for (CandidateInterf c : getCan()) {
            result+=("candidate number " + c.getNumber() + ": " + c.getName() + "\n son pitch est" + c.getPitch()+"\n");
        }
        return result;
    }


}

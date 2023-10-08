package Server;

import Interfaces.AuthentificatorIntef;
import Interfaces.ClientVoteInterface;
import Interfaces.DisplayCandidateInterf;
import Server.DataClass.Authentificator;
import Server.DataClass.DisplayCandidate;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerMain {

    public static void main(String args[]) throws RemoteException, NotBoundException {
        Registry reg= LocateRegistry.createRegistry(2001);
        AuthentificatorIntef auth= new Authentificator(10001);
        reg.rebind("authentify",auth);
        DisplayCandidateInterf dispCan= new DisplayCandidate(10002);
        reg.rebind("displayCan",dispCan);


        Registry reg2 = LocateRegistry.getRegistry(2002);
        ClientVoteInterface cVote = (ClientVoteInterface) reg2.lookup("VotingSystem");

    }
}

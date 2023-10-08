package Server;

import Interfaces.AuthentificatorIntef;
import Server.DataClass.Authentificator;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerMain {

    public static void main(String args[]) throws RemoteException {
        Registry reg= LocateRegistry.createRegistry(2001);
        AuthentificatorIntef auth= new Authentificator(10001);
        reg.rebind("VotingSystem",auth);


    }
}

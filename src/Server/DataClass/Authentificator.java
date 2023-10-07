package Server.DataClass;

import Interfaces.AuthentificatorIntef;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class Authentificator extends UnicastRemoteObject implements AuthentificatorIntef {
    private Map<Integer,User> usersDB= new HashMap<>();

    public Authentificator(int numport) throws RemoteException {
        super(numport);
        for(int i=1;i<=10;i++){
            String password="a".repeat(i);
            this.usersDB.put(i,new User(i,password));
        }
    }

    public boolean authentify(int studentNumber,String password){
        return this.usersDB.containsKey(studentNumber)&&this.usersDB.get(studentNumber).getPassword().equals(password);
    }
}

package Interfaces;

import java.rmi.Remote;

public interface AuthentificatorIntef extends Remote {
    public boolean authentify(int studentNumber,String password);
}

package Interfaces;

import java.rmi.Remote;

public interface CandidateInterf extends Remote {
    public String getName();
    public int getNumber();
}

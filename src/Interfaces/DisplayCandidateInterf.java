package Interfaces;

import java.rmi.Remote;
import java.util.List;

public interface DisplayCandidateInterf extends Remote {
    public void DisplayCan();

    public List<CandidateInterf> getCan();
}

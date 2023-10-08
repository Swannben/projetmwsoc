package Server.DataClass;
import Interfaces.UserInterf;
public class User implements UserInterf{

    Integer studentNumber;
    String password;

    boolean hasVoted;

    public User(Integer studentNumber,String password){
        this.studentNumber=studentNumber;
        this.password=password;
    }

    public Integer getStudentNumber() {
        return studentNumber;
    }

    public String getPassword() {
        return password;
    }
}

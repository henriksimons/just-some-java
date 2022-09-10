import assignments.three.AccountCreator;
import assignments.three.AccountReader;

public class Main {

    public static void main(String[] args){
        Thread t1 = new Thread(new AccountCreator());
        Thread t2 = new Thread(new AccountReader());
        t1.start();
        t2.start();
    }

}

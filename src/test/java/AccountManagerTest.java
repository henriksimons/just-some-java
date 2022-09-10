import assignments.three.AccountCreator;
import assignments.three.AccountReader;
import org.junit.Test;

public class AccountManagerTest {


    @Test
    public void testAccountsCreation() {
        Thread t1 = new Thread(new AccountCreator());
        Thread t2 = new Thread(new AccountReader());
        t1.start();
        t2.start();
    }

}

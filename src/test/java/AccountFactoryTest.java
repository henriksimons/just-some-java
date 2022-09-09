import assignments.one.AccountFactory;
import org.junit.Test;

import static org.junit.Assert.assertThrows;

public class AccountFactoryTest {

    @Test
    public void test(){
        AccountFactory factory = AccountFactory.createAccountFactory();
        Exception exception = assertThrows(RuntimeException.class, AccountFactory::createAccountFactory);
    }


}

import assignments.one.AccountFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AccountFactoryTest {

    @Test
    public void test() {
        AccountFactory factory = AccountFactory.createAccountFactory();
        AccountFactory factory2 = AccountFactory.createAccountFactory();
        assertEquals(factory, factory2);
    }
}

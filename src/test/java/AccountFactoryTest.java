import assignments.one.AccountFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AccountFactoryTest {

    @Test
    public void test() {
        AccountFactory factory = AccountFactory.getAccountFactory();
        AccountFactory factory2 = AccountFactory.getAccountFactory();
        assertEquals(factory, factory2);
    }
}

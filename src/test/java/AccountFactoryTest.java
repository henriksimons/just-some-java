import assignments.one.AccountFactory;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class AccountFactoryTest {

    @Test
    public void test() {
        AccountFactory factory = AccountFactory.getInstance();
        AccountFactory factory2 = AccountFactory.getInstance();
        assertEquals(factory, factory2);
    }
}

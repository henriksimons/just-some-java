import assignments.one.Account;
import assignments.one.AccountFactory;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertThrows;

public class AccountTest {

    private final AccountFactory ACCOUNT_FACTORY = AccountFactory.createAccountFactory();

    @Test
    public void testCreatingSingleAccount() {
        Account account = ACCOUNT_FACTORY.createAccount("123");
        Assert.assertEquals(account.getAccountName(), "123");
    }

    @Test
    public void testDuplicateAccount() {
        assertThrows(RuntimeException.class, () -> ACCOUNT_FACTORY.createAccount("123"));
    }

}

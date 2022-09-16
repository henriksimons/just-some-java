import assignments.one.Account;
import assignments.one.AccountFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccountTest {

    private final AccountFactory ACCOUNT_FACTORY = AccountFactory.getInstance();

    @Test
    public void testCreatingSingleAccount() {
        Account account = ACCOUNT_FACTORY.createAccount("123", null);
        assertEquals(account.getId(), "123");
    }

    @Test
    public void testDuplicateAccount() {
        assertThrows(RuntimeException.class, () -> ACCOUNT_FACTORY.createAccount("123", null));
    }

    @Test
    public void testCreatingAccountWithNoId() {
        assertThrows(IllegalArgumentException.class, () -> Account.builder().build());
    }
}

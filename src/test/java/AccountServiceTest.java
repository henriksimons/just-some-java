import assignments.four.AccountService;
import assignments.four.AccountServiceImpl;
import assignments.four.Person;
import assignments.one.Account;
import assignments.one.AccountFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class AccountServiceTest {

    private final String ACCOUNT_ID_1 = "a1";
    private final String ACCOUNT_ID_2 = "a2";
    private final String ACCOUNT_ID_3 = "a3";
    private final String ACCOUNT_ID_4 = "a4";
    private final String PERSON_ID_1 = "p1";
    private final Person PERSON_1 = Person.builder().id(PERSON_ID_1).build();
    private final AccountService accountService = new AccountServiceImpl(AccountFactory.getInstance());

    @Test
    public void testCreatingAccount() {
        assertThrows(IllegalArgumentException.class, () -> accountService.createAccount(null, null));
    }

    @Test
    public void testCreatingAccountWithNullId() {
        assertThrows(IllegalArgumentException.class, () -> accountService.createAccount(null, PERSON_1));
    }

    @Test
    public void testCreatingDuplicateAccount() {
        assertTrue(accountService.createAccount(ACCOUNT_ID_2, PERSON_1));
        assertFalse(accountService.createAccount(ACCOUNT_ID_2, PERSON_1));
    }

    @Test
    public void testCreatingAccountToNullPerson() {
        assertThrows(RuntimeException.class, () -> accountService.createAccount(ACCOUNT_ID_3, null));
    }

    @Test
    public void testCreatingNullAccountPerson() {
        assertThrows(RuntimeException.class, () -> accountService.createAccount(null, PERSON_1));
    }

    @Test
    public void testGettingNonExistingAccount() {
        assertThrows(RuntimeException.class, () -> accountService.getAccount("0"));
    }

    @Test
    public void testGettingExistingAccount() {
        assertTrue(accountService.createAccount(ACCOUNT_ID_4, PERSON_1));
        Account expected = accountService.getAccount(ACCOUNT_ID_4);
        assertEquals(expected, accountService.getAccount(ACCOUNT_ID_4));
    }
}

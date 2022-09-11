import assignments.four.*;
import assignments.one.Account;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

public class AccountServiceTest {

    private final String ACCOUNT_ID_1 = "a1";
    private final String ACCOUNT_ID_2 = "a2";
    private final String ACCOUNT_ID_3 = "a3";
    private final String ACCOUNT_ID_4 = "a4";
    private final String PERSON_ID_1 = "p1";
    private final Person PERSON_1 = Person.builder().id(PERSON_ID_1).build();
    private final AccountService accountService = AccountServiceImpl.getInstance();

    @Test
    public void testCreatingAccount() {
        Assert.assertTrue(accountService.createAccount(ACCOUNT_ID_1, PERSON_1));
    }

    @Test
    public void testCreatingDuplicateAccount() {
        Assert.assertTrue(accountService.createAccount(ACCOUNT_ID_2, PERSON_1));
        Assert.assertFalse(accountService.createAccount(ACCOUNT_ID_2, PERSON_1));
    }

    @Test
    public void testCreatingAccountToNullPerson() {
        Assert.assertThrows(RuntimeException.class, () -> accountService.createAccount(ACCOUNT_ID_3, null));
    }

    @Test
    public void testCreatingNullAccountPerson() {
        Assert.assertThrows(RuntimeException.class, () -> accountService.createAccount(null, PERSON_1));
    }

    @Test
    public void testGettingNonExistingAccount() {
        Assert.assertThrows(RuntimeException.class, () -> accountService.getAccount("0"));
    }

    @Test
    public void testGettingExistingAccount() {
        Assert.assertTrue(accountService.createAccount(ACCOUNT_ID_4, PERSON_1));
        Account expected = accountService.getAccount(ACCOUNT_ID_4);
        Assert.assertEquals(expected, accountService.getAccount(ACCOUNT_ID_4));
    }
}

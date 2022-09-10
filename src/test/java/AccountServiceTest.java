import assignments.four.AccountService;
import assignments.four.Person;
import assignments.four.PersonService;
import org.junit.Assert;
import org.junit.Test;

public class AccountServiceTest {


    private static final String PERSON_ID = "1";
    private final AccountService accountService = AccountService.getInstance();
    private final PersonService personService = PersonService.getInstance();
    private final Person person = Person.builder().id(PERSON_ID).build();
    private final String ACCOUNT_ID_1 = "1";
    private final String ACCOUNT_ID_2 = "2";

    @Test
    public void testCreatingAccount() {
        Assert.assertTrue(accountService.createAccount(ACCOUNT_ID_1, person));
    }

    @Test
    public void testCreatingDuplicateAccount() {
        Assert.assertTrue(accountService.createAccount(ACCOUNT_ID_2, person));
        Assert.assertFalse(accountService.createAccount(ACCOUNT_ID_2, person));
    }
}

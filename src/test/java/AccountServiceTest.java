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

    @Test
    public void testCreatingAccount() {
        String accountId = "1";
        Assert.assertTrue(accountService.createAccount(accountId, person));
    }

    @Test
    public void testCreatingDuplicateAccount() {
        String accountId = "1";
        Assert.assertTrue(accountService.createAccount(accountId, person));
        Assert.assertFalse(accountService.createAccount(accountId, person));
    }
}

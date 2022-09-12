import assignments.four.*;
import assignments.four.endpoint.ApiService;
import assignments.four.endpoint.ApiServiceImpl;
import assignments.one.Account;
import assignments.one.AccountFactory;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Set;

import static org.mockito.ArgumentMatchers.any;

public class ApiServiceTest {

    private final String ACCOUNT_ID = "123";
    private final Account ACCOUNT = Account.builder().id(ACCOUNT_ID).build();
    private final Set<Account> ACCOUNTS = Set.of(ACCOUNT);
    private final String PERSON_ID = "199901311234";
    private final Person PERSON = Person.builder().id(PERSON_ID).build();
    private final AccountService accountService = Mockito.mock(AccountServiceImpl.class);
    private final PersonService personService = Mockito.mock(PersonServiceImpl.class);
    private final AccountFactory accountFactory = Mockito.mock(AccountFactory.class);
    private final ApiService apiService = new ApiServiceImpl(accountService, personService);

    @Test
    public void testGetAccount() {
        Mockito.when(accountService.getAccount(Mockito.anyString())).thenReturn(ACCOUNT);
        Assert.assertEquals(ACCOUNT, apiService.getAccount(ACCOUNT_ID));
    }

    @Test
    public void testGetNonExistingAccount() {
        Mockito.when(accountService.getAccount(Mockito.anyString())).thenThrow(new RuntimeException());
        Assert.assertThrows(RuntimeException.class, () -> apiService.getAccount(ACCOUNT_ID));
    }

    @Test
    public void testGetAccountsForPerson() {
        Mockito.when(personService.getPerson(Mockito.anyString())).thenReturn(PERSON);
        Mockito.when(accountService.getAccounts(PERSON)).thenReturn(ACCOUNTS);
        Assert.assertEquals(ACCOUNTS, apiService.getAccounts(PERSON_ID));
    }

    @Test
    public void testGetAccountsForNonExistingPerson() {
        Mockito.when(personService.getPerson(Mockito.anyString())).thenThrow(new RuntimeException());
        Mockito.when(accountService.getAccounts(PERSON)).thenReturn(ACCOUNTS);
        Assert.assertThrows(RuntimeException.class, () -> apiService.getAccounts(PERSON_ID));
    }

    @Test
    public void testCreateAccountForPerson() {
        Mockito.when(personService.getPerson(Mockito.anyString())).thenReturn(PERSON);
        Mockito.when(accountService.createAccount(Mockito.anyString(), any())).thenReturn(true);
        Assert.assertTrue(apiService.createAccount(ACCOUNT_ID, PERSON_ID));
    }

    @Test
    public void testCreateAccountForPersonButAccountCreationFails() {
        Mockito.when(personService.getPerson(Mockito.anyString())).thenReturn(PERSON);
        Mockito.when(accountService.createAccount(Mockito.anyString(), any())).thenReturn(false);
        Assert.assertFalse(apiService.createAccount(ACCOUNT_ID, PERSON_ID));
    }

    @Test
    public void testCreateAccountForNullPerson() {
        Mockito.when(personService.getPerson(null)).thenCallRealMethod();
        Mockito.when(accountService.createAccount(Mockito.anyString(), any())).thenReturn(false);
        Assert.assertThrows(RuntimeException.class, () -> apiService.createAccount(ACCOUNT_ID, null));
    }

    @Test
    public void testCreateNullAccountForPerson() {
        Mockito.when(personService.getPerson(Mockito.anyString())).thenReturn(PERSON);
        Mockito.when(accountService.createAccount(null, PERSON)).thenCallRealMethod();
        Assert.assertThrows(RuntimeException.class, () -> apiService.createAccount(null, PERSON_ID));
    }

    @Test
    public void testCreatingSeveralAccountsForPerson() {
        AccountService accountService = new AccountServiceImpl(accountFactory);
        ApiService apiService = new ApiServiceImpl(accountService, personService);
        Mockito.when(personService.getPerson(Mockito.anyString())).thenReturn(PERSON);
        Mockito.when(accountFactory.createAccount(Mockito.anyString(), Mockito.any())).thenCallRealMethod();
        accountService.createAccount("1", PERSON);
        accountService.createAccount("2", PERSON);
        Assert.assertEquals(2, apiService.getAccounts(PERSON_ID).size());
    }
}

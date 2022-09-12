import assignments.four.*;
import assignments.four.endpoint.ApiService;
import assignments.four.endpoint.ApiServiceImpl;
import assignments.one.Account;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Set;

public class IntegrationTest {

    private final AccountService accountServiceMock = Mockito.mock(AccountServiceImpl.class);
    private final PersonService personServiceMock = Mockito.mock(PersonServiceImpl.class);
    private final String PERSON_ID = "1234";
    private final Person PERSON = Person.builder().id(PERSON_ID).build();
    private final Set<Account> ACCOUNTS = Set.of(Account.builder().id("1").owner(PERSON).build());
    private final ApiService apiService = new ApiServiceImpl(accountServiceMock, personServiceMock);

    @Test
    public void testGetAccountsForPerson() {
        Mockito.when(personServiceMock.getPerson(PERSON_ID)).thenReturn(PERSON);
        Mockito.when(accountServiceMock.getAccounts(PERSON)).thenReturn(ACCOUNTS);
        Assert.assertEquals(ACCOUNTS, apiService.getAccounts(PERSON_ID));
        Mockito.verify(accountServiceMock).getAccounts(PERSON);
        Mockito.verify(personServiceMock).getPerson(PERSON_ID);
    }
}

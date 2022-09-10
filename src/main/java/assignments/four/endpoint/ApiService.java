package assignments.four.endpoint;

import assignments.four.AccountService;
import assignments.four.Person;
import assignments.four.PersonService;
import assignments.one.Account;

import java.util.Set;

public class ApiService {

    private final AccountService accountService = AccountService.getInstance();
    private final PersonService personService = PersonService.getInstance();

    public Set<Account> getAccounts(String personId) {
        Person person = personService.getPerson(personId);
        return accountService.getAccounts(person);
    }

    public Account getAccount(String accountId) {
        return accountService.getAccount(accountId);
    }

    public boolean createAccount(String accountId, String personId) {
        Person person = null;
        try {
            person = personService.getPerson(personId);
        } catch (Exception e) {
            Person newPerson = personService.createPerson(personId);
            personService.savePerson(newPerson);
            person = personService.getPerson(personId);
        }
        return accountService.createAccount(accountId, person);
    }


}

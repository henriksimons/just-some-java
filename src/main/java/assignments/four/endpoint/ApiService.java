package assignments.four.endpoint;

import assignments.four.*;
import assignments.one.Account;

import java.util.Set;

public class ApiService {

    private final AccountService accountService = AccountServiceImpl.getInstance();
    private final PersonService personService = PersonServiceImpl.getInstance();

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
            Person newPerson = Person.builder().id(personId).build();
            personService.savePerson(newPerson);
            person = personService.getPerson(personId);
        }
        return accountService.createAccount(accountId, person);
    }


}

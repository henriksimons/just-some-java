package assignments.four.endpoint;

import assignments.four.*;
import assignments.one.Account;

import java.util.Set;

public class ApiServiceImpl implements ApiService {

    private final AccountService accountService;
    private final PersonService personService;

    public ApiServiceImpl(AccountService accountService, PersonService personService) {
        this.accountService = accountService;
        this.personService = personService;
    }

    @Override
    public Set<Account> getAccounts(String personId) {
        Person person = personService.getPerson(personId);
        return accountService.getAccounts(person);
    }

    @Override
    public Account getAccount(String accountId) {
        return accountService.getAccount(accountId);
    }

    @Override
    public boolean createAccount(String accountId, String personId) {
        Person person;
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

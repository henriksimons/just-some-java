package assignments.four;

import assignments.one.Account;

import java.util.Set;

public interface AccountService {
    boolean createAccount(String id, Person person);

    Account getAccount(String id);

    Set<Account> getAccounts(Person person);
}

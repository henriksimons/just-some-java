package assignments.four;

import assignments.one.Account;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public interface AccountService {
    boolean createAccount(String id, Person person);

    Account getAccount(String id);

    Set<Account> getAccounts(Person person);

    boolean deleteAccount(String id);

    List<Account> getAllAccounts();
}

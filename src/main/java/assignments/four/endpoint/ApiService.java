package assignments.four.endpoint;

import assignments.one.Account;

import java.util.Set;

public interface ApiService {
    Set<Account> getAccounts(String personId);

    Account getAccount(String accountId);

    boolean createAccount(String accountId, String personId);
}

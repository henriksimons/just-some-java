package assignments.three;

import assignments.one.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountManager {

    private final List<Account> accounts = new ArrayList<>();
    private static AccountManager instance = null;

    private AccountManager() {
    }

    public static synchronized AccountManager getInstance() {
        if (instance == null) {
            instance = new AccountManager();
        }
        return instance;
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}

package assignments.three;

import assignments.one.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class AccountManager {

    private static final Logger LOGGER = Logger.getLogger(AccountManager.class.getName());
    private static final List<Account> ACCOUNTS = new ArrayList<>();
    private static AccountManager instance = null;

    private AccountManager() {
    }

    public static synchronized AccountManager getInstance() {
        if (instance == null) {
            instance = new AccountManager();
        }
        return instance;
    }

    public static List<Account> getAccounts() {
        return ACCOUNTS;
    }
}

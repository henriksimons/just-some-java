package assignments.four;

import assignments.one.Account;
import assignments.one.AccountFactory;

import java.util.HashMap;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static assignments.four.Util.assertIdIsNotNull;
import static assignments.four.Util.assertPersonIsNotNull;

public class AccountService {

    private static final Logger LOGGER = Logger.getLogger(AccountService.class.getName());
    private static AccountService instance = null;
    private final HashMap<String, Account> ACCOUNTS = new HashMap<>();
    private final AccountFactory ACCOUNT_FACTORY = AccountFactory.getInstance();

    private AccountService() {
    }

    public synchronized static AccountService getInstance() {
        if (instance == null) {
            instance = new AccountService();
        }
        return instance;
    }

    public boolean createAccount(String id, Person person) {
        assertIdIsNotNull(id);
        assertPersonIsNotNull(person);
        try {
            Account account = ACCOUNT_FACTORY.createAccount(id);
            ACCOUNTS.put(account.getId(), account);
            return true;
        } catch (Exception e) {
            LOGGER.warning(e.getMessage());
            return false;
        }
    }

    public Account getAccount(String id) {
        assertIdIsNotNull(id);
        String key = id.trim().toLowerCase();
        if (ACCOUNTS.containsKey(key)) {
            return ACCOUNTS.get(key);
        } else {
            String message = "No such account with id: " + key + "exists.";
            LOGGER.warning(message);
            throw new RuntimeException(message);
        }
    }

    public Set<Account> getAccounts(Person person) {
        assertPersonIsNotNull(person);
        return ACCOUNTS.values().stream()
                .filter(account -> account.getOwner().equals(person))
                .collect(Collectors.toUnmodifiableSet());
    }
}

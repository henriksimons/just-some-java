package assignments.four;

import assignments.one.Account;
import assignments.one.AccountFactory;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static assignments.four.Util.assertIdIsNotNull;
import static assignments.four.Util.assertPersonIsNotNull;

public class AccountServiceImpl implements AccountService {

    private static final Logger LOGGER = Logger.getLogger(AccountServiceImpl.class.getName());
    private static AccountService instance = null;
    private final HashMap<String, Account> ACCOUNTS_BY_ID = new HashMap<>();
    private final AccountFactory accountFactory = AccountFactory.getInstance();

    private AccountServiceImpl() {
    }

    public synchronized static AccountService getInstance() {
        if (instance == null) {
            instance = new AccountServiceImpl();
        }
        return instance;
    }

    @Override
    public boolean createAccount(String id, Person person) {
        assertIdIsNotNull(id);
        assertPersonIsNotNull(person);
        try {
            Account account = accountFactory.createAccount(id);
            account.setOwner(person);
            ACCOUNTS_BY_ID.put(account.getId(), account);
            return true;
        } catch (Exception e) {
            LOGGER.warning(e.getMessage());
            return false;
        }
    }

    @Override
    public Account getAccount(String id) {
        assertIdIsNotNull(id);
        String key = id.trim().toLowerCase();
        if (ACCOUNTS_BY_ID.containsKey(key)) {
            return ACCOUNTS_BY_ID.get(key);
        } else {
            String message = "No account with id " + key + " exists.";
            LOGGER.warning(message);
            throw new RuntimeException(message);
        }
    }

    @Override
    public Set<Account> getAccounts(Person person) {
        assertPersonIsNotNull(person);
        return ACCOUNTS_BY_ID.values().stream()
                .filter(account -> account.getOwner().equals(person))
                .collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public boolean deleteAccount(String id) {
        assertIdIsNotNull(id);
        boolean exists = ACCOUNTS_BY_ID.containsKey(id);
        if (exists) {
            ACCOUNTS_BY_ID.remove(id); //What to do with accounts?
            return true;
        } else
            LOGGER.warning("No account with id " + id + " exists to delete.");
        return false;
    }

    /**
     * Returns a copy.
     */
    @Override
    public List<Account> getAllAccounts() {
        if (ACCOUNTS_BY_ID.isEmpty()) {
            return Collections.emptyList();
        } else
            return Collections.synchronizedList(new ArrayList<>(ACCOUNTS_BY_ID.values()));
    }
}

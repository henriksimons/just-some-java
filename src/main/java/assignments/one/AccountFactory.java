package assignments.one;

import assignments.four.Person;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

public class AccountFactory {

    private static final Logger LOGGER = Logger.getLogger(AccountFactory.class.getName());
    private static final Set<Account> createdAccounts = new HashSet<>();
    private static AccountFactory instance = null;

    private AccountFactory() {
    }

    public static synchronized AccountFactory getInstance() {
        if (instance == null) {
            instance = new AccountFactory();
        }
        return instance;
    }

    /**
     * As assignment one only requires an account to have an Id. No assertions for {@link assignments.four.Person} is made.
     */
    public Account createAccount(String name, Person person) {

        assertNotBlankOrNull(name);

        String nameTrimmedToLowercase = name.trim().toLowerCase();

        synchronized (createdAccounts) {
            Account account = Account.builder().id(nameTrimmedToLowercase).owner(person).build();
            if (createdAccounts.contains(account)) {
                String message = "Account " + nameTrimmedToLowercase + " already exists. Can not create a duplicate!";
                LOGGER.warning(message);
                throw new RuntimeException(message);
            } else {
                createdAccounts.add(account);
                String message = "Creating new account with name: " + nameTrimmedToLowercase;
                LOGGER.info(message);
                return account;
            }
        }
    }

    private void assertNotBlankOrNull(String name) {
        if (name == null || name.isBlank()) {
            throw new RuntimeException("Can not create an Account without a name.");
        }
    }
}

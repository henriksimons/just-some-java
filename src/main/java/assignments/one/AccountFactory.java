package assignments.one;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

public class AccountFactory {

    private static final Logger LOGGER = Logger.getLogger(AccountFactory.class.getName());
    private static final Set<Account> createdAccounts = new HashSet<>();
    private static AccountFactory instance = null;

    private AccountFactory() {
    }

    public static synchronized AccountFactory getAccountFactory() {
        if (instance == null) {
            instance = new AccountFactory();
        }
        return instance;
    }


    public Account createAccount(String name) {

        assertNotBlankOrNull(name);

        String nameTrimmedToLowercase = name.trim().toLowerCase();

        synchronized (createdAccounts) {
            if (createdAccounts.contains(new Account(nameTrimmedToLowercase))) {
                String message = "Account " + nameTrimmedToLowercase + " already exists. Can not create a duplicate!";
                LOGGER.warning(message);
                throw new RuntimeException(message);
            } else {
                createdAccounts.add(new Account(nameTrimmedToLowercase));
                String message = "Creating new account with name: " + nameTrimmedToLowercase;
                LOGGER.info(message);
                return new Account(name);
            }
        }
    }

    private void assertNotBlankOrNull(String name) {
        if (name == null || name.isBlank()) {
            throw new RuntimeException("Can not create an Account without a name.");
        }
    }
}

package assignments.one;

import java.util.HashSet;
import java.util.logging.Logger;

public class AccountFactory {

    private static final Logger LOGGER = Logger.getLogger(AccountFactory.class.getName());
    private static final HashSet<String> createdAccounts = new HashSet<>();
    private static AccountFactory instance = null;

    private AccountFactory() {
    }

    public static synchronized AccountFactory createAccountFactory() {
        if (instance == null) {
            instance = new AccountFactory();
        }
        return instance;
    }


    public Account createAccount(String name) {
        if (name == null || name.isBlank()) {
            throw new RuntimeException("Can not create an Account without a name.");
        }

        String nameTrimmedToLowercase = name.trim().toLowerCase();
        boolean exists = createdAccounts.stream().anyMatch(accountName -> accountName.equalsIgnoreCase(nameTrimmedToLowercase));
        if (!exists) {
            createdAccounts.add(nameTrimmedToLowercase);
            LOGGER.info("Creating new account with name: " + name);
            return new Account(name);
        } else
            LOGGER.warning("Account already exists. Can not create a duplicate!");
        throw new RuntimeException("Account already exists. Can not create a duplicate!");
    }
}

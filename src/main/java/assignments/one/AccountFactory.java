package assignments.one;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class AccountFactory {

    private static final Logger LOGGER = Logger.getLogger(AccountFactory.class.getName());
    private static final List<String> createdAccounts = new ArrayList<>();
    private static int instances = 0;

    private AccountFactory(int init) {
        instances = init;
    }

    public static AccountFactory createAccountFactory() {
        if (instances == 0) {
            return new AccountFactory(1);
        } else
            LOGGER.warning("One instance of AccountFactory already exists. Can not create a duplicate!");
        throw new RuntimeException("Can not create more than one AccountFactory.");
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

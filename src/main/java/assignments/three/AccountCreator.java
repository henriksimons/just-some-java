package assignments.three;

import assignments.one.Account;
import assignments.one.AccountFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

public class AccountCreator implements Runnable {

    private static final List<Account> ACCOUNTS = AccountManager.getAccounts();
    private static final AccountFactory ACCOUNT_FACTORY = AccountFactory.createAccountFactory();
    private static final Logger LOGGER = Logger.getLogger(AccountCreator.class.getName());
    private boolean running = true;

    public AccountCreator() {
    }

    @Override
    public void run() {
        LOGGER.info("Running AccountCreator on thread: " + Thread.currentThread().getName());
        while (running) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ACCOUNTS.add(ACCOUNT_FACTORY.createAccount(LocalDateTime.now().toString()));
        }
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}

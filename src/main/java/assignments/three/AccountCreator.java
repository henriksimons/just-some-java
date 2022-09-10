package assignments.three;

import assignments.one.Account;
import assignments.one.AccountFactory;

import java.util.List;
import java.util.logging.Logger;

public class AccountCreator implements Runnable {

    private static final List<Account> ACCOUNTS = AccountManager.getAccounts();
    private Thread thread;
    private static final AccountFactory ACCOUNT_FACTORY = AccountFactory.createAccountFactory();
    private static final Logger LOGGER = Logger.getLogger(AccountCreator.class.getName());
    private boolean running = true;

    public AccountCreator() {
    }

    public void start() {
        thread = new Thread(this);
        thread.start();
    }

    public void stop() {
        running = false;
    }

    public void kill() {
        thread.stop();
    }

    @Override
    public void run() {
        LOGGER.info("Running AccountCreator on thread: " + Thread.currentThread().getName());
        int index = 0;
        while (running) {
            try {
                Thread.sleep(500);
                ACCOUNTS.add(ACCOUNT_FACTORY.createAccount("#" + index++));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}

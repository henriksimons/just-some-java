package assignments.three;

import assignments.one.Account;
import assignments.one.AccountFactory;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class AccountCreator implements Runnable {

    private Thread thread;
    private static final AccountFactory ACCOUNT_FACTORY = AccountFactory.getInstance();
    private static final Logger LOGGER = Logger.getLogger(AccountCreator.class.getName());
    private boolean running = true;

    public AccountCreator() {
    }

    public void start() {
        thread = new Thread(this);
        thread.setName("AccountCreatorThread-1");
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
        List<Account> accounts = Collections.synchronizedList(AccountManager.getInstance().getAccounts());
        LOGGER.info("Running AccountCreator on thread: " + Thread.currentThread().getName());
        int index = 0;
        while (running) {
            synchronized (accounts) {
                try {
                    Thread.sleep(500);
                    accounts.add(ACCOUNT_FACTORY.createAccount("#" + index++, null));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

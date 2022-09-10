package assignments.three;

import assignments.one.Account;

import java.util.List;
import java.util.logging.Logger;

public class AccountReader implements Runnable {

    private static final List<Account> ACCOUNTS = AccountManager.getAccounts();
    private Thread thread;
    private static final Logger LOGGER = Logger.getLogger(AccountReader.class.getName());
    private boolean running = true;

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
        LOGGER.info("Running AccountReader on thread: " + Thread.currentThread().getName());
        int startSize = ACCOUNTS.size();
        int index = 0;
        while (running) {
            if (startSize <= ACCOUNTS.size()) {
                if (!ACCOUNTS.isEmpty()) {
                    LOGGER.info("Account added: " + ACCOUNTS.get(index));
                }
                index++;
            }
        }
    }
}

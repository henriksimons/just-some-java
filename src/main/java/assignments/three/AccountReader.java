package assignments.three;

import assignments.one.Account;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class AccountReader implements Runnable {

    private Thread thread;
    private static final Logger LOGGER = Logger.getLogger(AccountReader.class.getName());
    private boolean running = true;

    public AccountReader() {
    }

    public void start() {
        thread = new Thread(this);
        thread.setName("AccountReaderThread-1");
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
        List<Account> accounts = Collections.synchronizedList(AccountManager.getInstance().getAccounts());
        int indexToGet = 0;
        while (running) {
            synchronized (accounts) {
                if (!accounts.isEmpty()) {
                    if (indexToGet < accounts.size()) {
                        Account account = accounts.get(indexToGet++);
                        LOGGER.info("Account " + account + " added!");
                    }
                }
            }
        }
    }
}

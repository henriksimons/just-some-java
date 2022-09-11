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
        while (running) {
            synchronized (accounts) {
                LOGGER.info("Accounts size = " + accounts.size());
            }
            sleep();
        }
    }

    private void sleep() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

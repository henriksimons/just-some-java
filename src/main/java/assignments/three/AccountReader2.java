package assignments.three;

import assignments.one.Account;

import java.util.List;
import java.util.logging.Logger;

public class AccountReader2 implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(AccountReader2.class.getName());
    private final List<Account> accounts;
    private boolean running;
    private Thread thread;

    public AccountReader2(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public void stop() {
        running = false;
    }

    public void kill() {
        thread.stop();
    }

    @Override
    public void run() {
        LOGGER.info("Running AccountReader2 on thread: " + Thread.currentThread().getName());
        while (true) {
            synchronized (accounts) {
                LOGGER.info("Accounts size: " + accounts.size());
            }
            sleep();
        }
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}

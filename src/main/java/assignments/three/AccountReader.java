package assignments.three;

import assignments.one.Account;

import java.util.List;
import java.util.logging.Logger;

public class AccountReader implements Runnable {

    private static final List<Account> ACCOUNTS = AccountManager.getAccounts();
    private static final Logger LOGGER = Logger.getLogger(AccountReader.class.getName());
    private boolean running = true;

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

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}

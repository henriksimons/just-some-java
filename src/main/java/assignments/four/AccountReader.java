package assignments.four;

import assignments.one.Account;

import java.util.List;
import java.util.logging.Logger;

public class AccountReader implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(AccountReader.class.getName());
    private boolean running;
    private Thread thread;

    public AccountReader() {
    }

    public void start() {
        thread = new Thread(this);
        thread.setName("AccountReaderThread-2");
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
        List<Account> accounts = AccountServiceImpl.getInstance().getAllAccounts();
        int sizeOld = 0;
        while (running) {
            synchronized (accounts) {
                if(accounts.size() > sizeOld){
                    LOGGER.info("Accounts: " + accounts.size());
                    sizeOld = accounts.size();
                }
            }
            sleep();
            if (AccountServiceImpl.getInstance().getAllAccounts().size() != accounts.size()) {
                accounts = AccountServiceImpl.getInstance().getAllAccounts();
            }
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

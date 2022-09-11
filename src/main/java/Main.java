import assignments.one.Account;
import assignments.three.AccountCreator;
import assignments.three.AccountManager;
import assignments.three.AccountReader;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws IOException {

        /*LOGGER.info("Running Main on thread: " + Thread.currentThread().getName());

        RestService.start(); //Starts REST endpoint*/

        assignmentThree();
    }

  /*  private static void assignmentThree() {
        AccountCreator creator = new AccountCreator();
        creator.start();
        AccountReader reader = new AccountReader();
        reader.start();



        boolean running = true;

        long startTime = System.currentTimeMillis();
        long endTime = startTime + 5000;
        long executionTime = 0;

        System.out.println("Starting account creation.");
        while (running) {
            if (System.currentTimeMillis() > endTime) {
                running = false;
                creator.stop();
                reader.stop();
                executionTime = System.currentTimeMillis() - startTime;
            }
        }
        System.out.println("Stopped account creation after " + executionTime / 1000 + " ms.");
        creator.kill();
        reader.kill();
    }  */

    private static void assignmentThree() {

        AccountCreator creator = new AccountCreator();
        creator.start();
        AccountReader reader = new AccountReader();
        reader.start();

        boolean running = true;

        long startTime = System.currentTimeMillis();
        long endTime = startTime + 5000;
        long executionTime = 0;

        System.out.println("Starting account creation.");
        while (running) {
            if (System.currentTimeMillis() > endTime) {
                running = false;
                creator.stop();
                reader.stop();
                executionTime = System.currentTimeMillis() - startTime;
            }
        }
        System.out.println("Stopped account creation after " + executionTime / 1000 + " ms.");
        creator.kill();
        reader.kill();
    }
}

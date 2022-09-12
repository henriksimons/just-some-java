import assignments.four.endpoint.ApiServlet;
import assignments.three.AccountCreator;
import assignments.three.AccountReader;
import assignments.three.AccountReader2;

import java.io.IOException;
import java.util.logging.Logger;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws IOException {

        LOGGER.info("Running Main on thread: " + Thread.currentThread().getName());

        ApiServlet apiServlet = new ApiServlet(); /*Starts REST endpoint*/
        apiServlet.start();

        //assignmentThree();

        AccountReader2 accountReader2 = new AccountReader2();
        accountReader2.start();
    }

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

package training.metofficeweather;

import java.util.concurrent.TimeUnit;

public class Worker implements Runnable {

    @Override
    public void run() {

        int count = 0;
        while (true) {
            try {
                System.out.printf("Working %d%n", count++);
                Thread.sleep(10 * 1000);
            } catch (InterruptedException ex) {
                /* noop */
            }
        }

    }
}

package training.metofficeweather;

public class Threader {
    public static void main(String[] args) {
        Runnable worker = new Worker();
        Thread threadOne = new Thread(worker);
        threadOne.start();


        System.out.println("hello");
        Runnable anonymousWorker = new Runnable() {
            @Override
            public void run() {

                int count = 0;
                while (true) {
                    try {
                        System.out.printf("Working again %d%n", count++);
                        Thread.sleep(10 * 1000);
                    } catch (InterruptedException ex) {
                        /* noop */
                    }
                }
            }
        };

        new Thread(anonymousWorker).start();



    }
}

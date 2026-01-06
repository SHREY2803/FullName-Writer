package p1;

import java.util.concurrent.BlockingQueue;

public class FullNamePrinter implements Runnable {

    private BlockingQueue<String> firstnameQueue;
    private BlockingQueue<String> lastnameQueue;

    public FullNamePrinter(BlockingQueue<String> firstnameQueue,
                           BlockingQueue<String> lastnameQueue) {

        this.firstnameQueue = firstnameQueue;
        this.lastnameQueue = lastnameQueue;
    }

    public void run() {
        try {

            while (true) {

                String fname = firstnameQueue.take(); // waits if empty
                String lname = lastnameQueue.take();

                if (fname.equals("EOF") || lname.equals("EOF")) {
                    break;
                }

                System.out.println(fname + " " + lname);

                Thread.sleep(800); // delay between names
            }

            System.out.println("✔ Printing complete");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

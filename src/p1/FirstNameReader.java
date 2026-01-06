package p1;

import java.io.*;
import java.util.concurrent.BlockingQueue;

public class FirstNameReader implements Runnable {

    private File file;
    private BlockingQueue<String> queue;

    public FirstNameReader(File file, BlockingQueue<String> queue) {
        this.file = file;
        this.queue = queue;
    }

    public void run() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            String name;
            while ((name = br.readLine()) != null) {

                queue.put(name); // safely enqueue
                Thread.sleep(100); // simulate reading delay
            }

            br.close();

            // add END marker
            queue.put("EOF");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

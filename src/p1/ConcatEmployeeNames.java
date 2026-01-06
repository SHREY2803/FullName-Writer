package p1;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConcatEmployeeNames {

    public static void main(String[] args) {

        File resourcesDir = new File("resources");
        if (!resourcesDir.exists()) {
            resourcesDir.mkdirs();
        }

        File firstNameFile = new File(resourcesDir, "firstnames.txt");
        File lastNameFile  = new File(resourcesDir, "lastnames.txt");

        try {
            firstNameFile.createNewFile();
            lastNameFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BlockingQueue<String> firstnameQueue = new LinkedBlockingQueue<String>();
        BlockingQueue<String> lastnameQueue  = new LinkedBlockingQueue<String>();

        Thread t1 = new Thread(new FirstNameReader(firstNameFile, firstnameQueue));
        Thread t2 = new Thread(new LastNameReader(lastNameFile, lastnameQueue));
        Thread printer = new Thread(new FullNamePrinter(firstnameQueue, lastnameQueue));

        t1.start();
        t2.start();
        printer.start();

        try {
            t1.join();
            t2.join();
            printer.join();   // wait for printer also
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("✔ All names printed successfully");
    }
}

package zad2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    static float ListOneLockTest(int threads, int sleep_time) {
        ListOneLockThread[] lockLists = new ListOneLockThread[threads];
        ListOneLock list = new ListOneLock("start", sleep_time);
        long start = System.nanoTime();

        for (int i = 0; i < threads; i++) {
            lockLists[i] = new ListOneLockThread(list);
            lockLists[i].start();
        }

        for (int i = 0; i < threads; i++) {
            try {
                lockLists[i].join();
            } catch (Exception e) {
                System.out.println("ERROR");
            }
        }

        long end = System.nanoTime();
        float time = (end-start)/1000000;
        System.out.println("Elapsed time in ms for locking list: " + time);

        return time;
    }
    static float ListManyLocksTest(int threads, int sleep_time) {
        ListManyLocksThread[] lockLists = new ListManyLocksThread[threads];
        ListManyLocks list = new ListManyLocks("start", sleep_time);
        long start = System.nanoTime();

        for (int i = 0; i < threads; i++) {
            lockLists[i] = new ListManyLocksThread(list);
            lockLists[i].start();
        }

        for (int i = 0; i < threads; i++) {
            try {
                lockLists[i].join();
            } catch (Exception e) {
                System.out.println("ERROR");
            }
        }

        long end = System.nanoTime();
        float time = (end-start)/1000000;
        System.out.println("Elapsed time in ms for locking list: " + time);

        return time;
    }


    public static void main(String[] args) throws IOException {
        System.out.println("Started");

        File file = new File("locking.txt");
        boolean fileCreated = file.createNewFile();
        if (!fileCreated)
            throw new IOException("ERROR");

        FileWriter fileWriter = new FileWriter("locking.txt");

        for (int sleep_time = 1; sleep_time < 50; sleep_time++) {
            for (int threads_num= 5; threads_num<= 5; threads_num++) {
                float lockTime = ListManyLocksTest(threads_num, sleep_time);
//            float lockTime = ListOneLockTest(threads_num, sleep_time);
                fileWriter.write(threads_num+ " "+ sleep_time + " " + lockTime + "\r\n");
            }
        }
            fileWriter.write("\r\n");
            fileWriter.close();
        }
}
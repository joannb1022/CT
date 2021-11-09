import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PK {

    public static void main(String[] args) {

        int m = 20;
        int n = 20;
        ArrayList<Producer> producers = new ArrayList<>();
        ArrayList<Consumer> consumers = new ArrayList<>();

        final ReentrantLock lock = new ReentrantLock();
        final Condition processesFinished  = lock.newCondition();
        lock.lock();

        Buffer buffer = new Buffer(120);

        for(int i=0; i<n; i++) {
            Consumer p = new Consumer(buffer, processesFinished, lock);
            p.start();
            consumers.add(p);
        }

        for(int i=0; i<m; i++) {
            Producer p = new Producer(buffer, processesFinished, lock);
            p.start();
            producers.add(p);
        }



        long start = System.nanoTime();

        try {
            processesFinished.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long finish = System.nanoTime();

        long timeElapsed = finish - start;

        producers.forEach(Thread::interrupt);
        consumers.forEach(Thread::interrupt);

        System.out.println(m + " " + n + " " + timeElapsed/1000000);

        try {
            FileWriter myWriter = new FileWriter("results.txt", true);
            myWriter.write(String.valueOf(timeElapsed/1000000) + "\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        lock.unlock();
    }
}
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

public class PK {

    public static void main(String[] args) {

        int producers=Integer.parseInt(args[0]);
        int consumers= Integer.parseInt(args[0]);
        ArrayList<Producer> producers_list = new ArrayList<>();
        ArrayList<Consumer> consumers_list = new ArrayList<>();

        final ReentrantLock lock = new ReentrantLock();
        final Condition processesFinished = lock.newCondition();
        lock.lock();

        int M = 100;

        Buffer buffer = new Buffer(M);

        for (int i = 0; i < consumers; i++) {
            Consumer p = new Consumer(buffer, processesFinished, lock);
            p.start();
            consumers_list.add(p);
        }

        for (int i = 0; i < producers; i++) {
            Producer p = new Producer(buffer, processesFinished, lock);
            p.start();
            producers_list.add(p);
        }

        long start = System.nanoTime();

        try {
            processesFinished.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long finish = System.nanoTime();

        long timeElapsed = finish - start;

        producers_list.forEach(Thread::interrupt);
        consumers_list.forEach(Thread::interrupt);

        System.out.println(producers + " " + consumers + " " + timeElapsed / 1000000);

        try {
            FileWriter myWriter = new FileWriter("results.txt", true);
            myWriter.write("time = " + String.valueOf(timeElapsed / 1000000) + " num_producers = " + producers + " num_consumers = " + consumers + " buffer_size = " + M + "\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        lock.unlock();

    }
}
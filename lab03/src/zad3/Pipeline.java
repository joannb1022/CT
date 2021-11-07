package zad3;

import java.util.LinkedList;

public class Pipeline {
    public static void main(String[] args) {
        int n = 5;
        int size = 100;

        Buffer buffer = new Buffer(size, n);

        LinkedList<Thread> threads = new LinkedList<>();

        threads.add(new Producer(buffer));
        for (int i = 0; i < n; i++) {
            threads.add(new ProcessThread(buffer, i));
        }
        threads.add(new Consumer(buffer));

        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
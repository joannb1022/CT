package zad1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Library {
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition readers = lock.newCondition();
    private final Condition writers = lock.newCondition();
    private int reading = 0;
    private int writing = 0;

    public Library() {
    }

    public void beginReading() {
        lock.lock();
        try {
            while (writing > 0 && lock.hasWaiters(writers)) {
                readers.await();
            }
            reading += 1;
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    public void endReading() {
        lock.lock();
        try {
            reading -= 1;
            if (reading == 0)
                writers.signal();
        } finally {
            lock.unlock();
        }
    }

    public void beginWriting() {
        lock.lock();
        try {
            while (reading + writing > 0) {
                writers.await();
            }
            writing = 1;
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    public void endWriting() {
        lock.lock();
        try {
            writing = 0;
            if (!lock.hasWaiters(readers))
                writers.signal();
            else
                readers.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
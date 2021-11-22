package zad2;

import java.util.concurrent.locks.ReentrantLock;

public class ListOneLock {

    private Node head;
    private final ReentrantLock lock = new ReentrantLock();
    private int sleep_time;


    public ListOneLock(Object o, int sleep_time) {
        this.head = new Node(o, null);
        this.sleep_time = sleep_time;
    }

    boolean contains(Object o) {
        lock.lock();
        try {

            Node curr = head;

            while (curr != null) {
                if (curr.getObject() == o) {
                    try {
                        Thread.sleep(sleep_time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return true;
                }
                curr = curr.getNext_element();
            }
        } finally {
            lock.unlock();
        }
        return false;
    }

    boolean remove(Object o) {
        lock.lock();
        try {
            if (head.getObject() == o) {
                head = head.getNext_element();
                try {
                    Thread.sleep(sleep_time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return true;
            }
            Node curr = head;
            while (!curr.isLast()) {
                Node next = curr.getNext_element();
                if (next.getObject() == o) {
                    Node next_next = next.getNext_element();
                    curr.addNext(next_next);
                    try {
                        Thread.sleep(sleep_time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return true;
                }
                curr = curr.getNext_element();
            }
        } finally {
            lock.unlock();
        }
        return false;
    }

    public boolean add(Object o) {
        lock.lock();
        try {
            Node curr = head;
            while (!curr.isLast()) {
                curr = curr.getNext_element();
            }
            try {
                Thread.sleep(sleep_time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            curr.addNext(new Node(o, null));
        } finally {
            lock.unlock();
        }
        return true;
    }
}

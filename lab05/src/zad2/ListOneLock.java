package zad2;

import java.util.concurrent.locks.ReentrantLock;

public class ListOneLock {

    private Node head;
    private final ReentrantLock lock = new ReentrantLock();


    public ListOneLock(Object o){
        this.head = new Node(o, null);
    }

    boolean contains(Object o){
        lock.lock();
        try {
            Node curr = head;
            Node next = head.getNext_element();
            while (!curr.isLast()) {
                if (next.getObject() == o) {
                    return true;
                }
                curr = curr.getNext_element();
                next = next.getNext_element();
            }
        }
        finally {
            lock.unlock();
        }

        return false;
    }

    boolean remove(Object o){
        lock.lock();
        try {

            Node curr = head;
            Node next = head.getNext_element();

            while (!curr.isLast()) {
                if (next.getObject() == o) {
                    Node tmp = next.getNext_element();
                    curr.addNext(tmp);
                    return true;
                }
                Node tmp = curr;
                curr = curr.getNext_element();
                tmp.getLock().unlock();
                next = next.getNext_element();
            }
        }
        finally {
            lock.unlock();
        }
        return false;
    }

    boolean add(Object o) {
        Node curr = head;
        curr.getLock().lock();
        Node next = head.getNext_element();

        while (!curr.isLast()) {
            next.getLock().lock();
            Node tmp = curr;
            curr = tmp.getNext_element();
            tmp.getLock().unlock();
            next = next.getNext_element();
        }
        curr.addNext(new Node(o, null));
        curr.getLock().unlock();
        return true;
    }
}

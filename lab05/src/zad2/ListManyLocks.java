package zad2;

import java.util.concurrent.locks.ReentrantLock;

public class ListManyLocks {

    private Node head;
    private int sleep_time;

    public ListManyLocks(Object o, int sleep_time) {
        this.sleep_time = sleep_time;
        head = new Node(o, null);

    }

    boolean contains(Object o) {
        Node curr = head;
        curr.getLock().lock();

        while (curr != null) {
            if (curr.getObject() == o) {
                try {
                    Thread.sleep(sleep_time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                curr.getLock().unlock();
                return true;
            }
            if (!curr.isLast())
                curr.getNext_element().getLock().lock();
            curr.getLock().unlock();
            curr = curr.getNext_element();
        }

        return false;

    }

    //czy lista zawiera element o
    boolean remove(Object o) {

        head.getLock().lock();

        if (head.getObject() == o) {
            Node tmp = head;
            head = head.getNext_element();
            tmp.getLock().unlock();
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
            next.getLock().lock();
            if (next.getObject() == o) {
                Node next_next = next.getNext_element();
                curr.addNext(next_next);
                curr.getLock().unlock();
                next.getLock().unlock();
                try {
                    Thread.sleep(sleep_time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return true;
            }
            curr.getLock().unlock();
            curr = curr.getNext_element();
        }
        return false;
    }

    boolean add(Object o) {
        Node curr = head;
        curr.getLock().lock();

        while (!curr.isLast()) {
//            System.out.println("Dodaje" + o);
            curr.getNext_element().getLock().lock();
            curr.getLock().unlock();
            curr = curr.getNext_element();
        }
        try {
            Thread.sleep(sleep_time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        curr.addNext(new Node(o, null));
        curr.getLock().unlock();
        return true;
    }
}

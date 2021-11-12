package zad2;

import java.util.concurrent.locks.ReentrantLock;

public class ListManyLocks {

    private ReentrantLock lock = new ReentrantLock();
    private Node head;

    public ListManyLocks(){
        Object o = new Object();
        head = new Node(o, null);

    }
    boolean contains(Object o){
        Node curr = head;
        curr.getLock().lock();
        Node next = head.getNext_element();

        while (!curr.isLast()) {
            next.getLock().lock();
            if (next.getObject() == o) {
                return true;
            }
            Node tmp = curr;
            curr = curr.getNext_element();
            tmp.getLock().unlock();
            next = next.getNext_element();
        }

        curr.getLock().unlock();
        return false;

    } //czy lista zawiera element o
    boolean remove(Object o){
        Node curr = head;
        curr.getLock().lock();
        Node next = head.getNext_element();

        while (!curr.isLast()) {
            next.getLock().lock();
            if (next.getObject() == o) {
                Node tmp = next.getNext_element();
                curr.addNext(tmp);
                curr.getLock().unlock();
                next.getLock().unlock();
                return true;
            }
            Node tmp = curr;
            curr = curr.getNext_element();
            tmp.getLock().unlock();
            next = next.getNext_element();
        }

        curr.getLock().unlock();
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

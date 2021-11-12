package zad2;

import java.util.concurrent.locks.ReentrantLock;

public class Node {

    private final Object o;
    private Node next_element;
    private final ReentrantLock lock = new ReentrantLock();

    public Node(Object o, Node next_element){
        this.o = o;
        this.next_element = next_element;
    }

    public void addNext(Node next_element){
        this.next_element = next_element;
    }

    public boolean isLast(){
      return this.next_element == null;
    }

    public Object getObject(){
        return this.o;
    }

    public Node getNext_element(){
        return this.next_element;
    }

    public ReentrantLock getLock(){
        return this.lock;
    }
}

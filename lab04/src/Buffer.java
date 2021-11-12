import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Buffer {

    private LinkedList<Integer> bufferList = new LinkedList<>();
    private final int size;

    final ReentrantLock lock = new ReentrantLock();
    final Condition firstProd  = lock.newCondition();
    final Condition firstCons = lock.newCondition();
    final Condition restProd = lock.newCondition();
    final Condition restCons = lock.newCondition();

    public Buffer(int M) {
        this.size = 2 * M;
    }

    public void put(ArrayList<Integer> portion_list) throws InterruptedException{
        lock.lock();
        try {
            if(lock.hasWaiters(firstProd)) restProd.await();
            while(size - bufferList.size() < portion_list.size())
                firstProd.await();
            bufferList.addAll(portion_list);
            restProd.signal();
            firstCons.signal();
        } finally {
            lock.unlock();
        }
    }

    public void get(int size) throws InterruptedException {
        lock.lock();
        try {
            if(lock.hasWaiters(firstCons)) restCons.await();
            while(bufferList.size() < size)
                firstCons.await();
            for(int i=0; i<size; i++) {
                bufferList.removeLast();
            }
            restCons.signal();
            firstProd.signal();
        } finally {
            lock.unlock();
        }
    }

    public int getSize() {
        return size;
    }
}
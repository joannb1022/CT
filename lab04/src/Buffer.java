import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class Buffer {
    private final int M;
    private final LinkedList<Integer> bufferList = new LinkedList<>();
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition firstProd = lock.newCondition();
    private final Condition firstCons = lock.newCondition();
    private final Condition restProd = lock.newCondition();
    private final Condition restCons = lock.newCondition();


    public Buffer(int M) {
        this.M = M;
    }

    public void put(ArrayList<Integer> list) throws InterruptedException {
        lock.lock();
        try{
            if (lock.hasWaiters(firstProd)){
                restProd.await();
            }
            while(this.M - bufferList.size() < list.size()){
            firstProd.await();
            }
            bufferList.addAll(list);
            System.out.println("Jest " + bufferList.size() + "elementow");

            restProd.signal();
            firstCons.signal();

        } finally {
            lock.unlock();
        }
    }


    public void get(int num_portions) throws InterruptedException {
        lock.lock();
        try{
            if (lock.hasWaiters(firstCons)){
                restCons.await();
            }
            while (bufferList.size() < num_portions){
                firstCons.await();
            }
            for (int i = 0; i < num_portions; i++){
                bufferList.removeLast();
            }
            restCons.signal();
            firstProd.signal();
        }finally {
            lock.unlock();

        }
    }

    public int getSize(){
        return M;
    }
}
import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Producer extends Thread {
    private Buffer _buf;
    private Condition cond;
    private ReentrantLock mainLock;

    public Producer(Buffer buffer, Condition cond, ReentrantLock mainLock) {
        this._buf = buffer;
        this.cond = cond;
        this.mainLock = mainLock;
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            ArrayList<Integer> items = new ArrayList<>();
            int length = (int) (Math.random() * (_buf.getSize() / 2 - 1));
            for(int j = 0; j<length; j++ ){
                items.add((int) (Math.random() * 10));
            }
            try {
                _buf.put(items);
            } catch (InterruptedException e) {
                return;
            }
        }
        mainLock.lock();
        cond.signal();
        mainLock.unlock();
    }
}
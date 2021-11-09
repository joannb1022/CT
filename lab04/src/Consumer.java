import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Consumer extends Thread {
    private Buffer _buf;
    private Condition cond;
    private ReentrantLock mainLock;

    public Consumer(Buffer buffer, Condition cond, ReentrantLock mainLock) {
        this._buf = buffer;
        this.cond = cond;
        this.mainLock = mainLock;
    }

    public void run() {
        for (int i = 0; i < 100; ++i) {
            int num_portions = (int)(Math.random() * (_buf.getSize() / 2 - 1));
            try {
                _buf.get(num_portions);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        mainLock.lock();
        cond.signal();
        mainLock.unlock();
    }
}

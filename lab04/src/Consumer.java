import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class Consumer extends Thread {
    private Buffer _buf;
    private Condition cond;
    private ReentrantLock mainLock;

    public Consumer(Buffer buffer, Condition cond, ReentrantLock mainLock) {
        this._buf = buffer;
        this.cond = cond;
        this.mainLock = mainLock;
    }


    public void run() {
        for (int i = 0; i < 100; i++) {
            int length = (int) (Math.random() * (_buf.getSize() / 2 - 1));
            try {
                _buf.get(length);
            } catch (InterruptedException e) {
                return;
            }
        }
        mainLock.lock();
        cond.signal();
        mainLock.unlock();
    }
}
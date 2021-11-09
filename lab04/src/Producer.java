import javax.management.relation.RelationNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Producer extends Thread {
    private Buffer _buf;
    private Condition cond;
    private ReentrantLock mainLock;
    private final Random random  = new Random();

    public Producer(Buffer buffer, Condition cond, ReentrantLock mainLock) {
        this._buf = buffer;
        this.cond = cond;
        this.mainLock = mainLock;
    }


    public void run() {
        for (int i = 0; i < 100; ++i) {
            int num_portions = (int)(Math.random() * (_buf.getSize() / 2 - 1));
            ArrayList<Integer>portions_list = new ArrayList<>(num_portions);
            for (int j = 0; j < num_portions; j++){
                portions_list.add(random.nextInt(50));
            }
            try {
                _buf.put(portions_list);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        mainLock.lock();
        cond.signal();
        mainLock.unlock();
    }
}
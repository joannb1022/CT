import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import java.util.concurrent.LinkedBlockingQueue;

public class Scheduler extends Thread {
    private final LinkedBlockingQueue<IMethodRequest> activationQueue;

    public Scheduler() {
        activationQueue = new LinkedBlockingQueue<>();
    }

    public void enqueue(IMethodRequest r) {
        activationQueue.add(r);
    }

    @Override
    public void run() {
        System.out.println("Hello scheduler here!");
        while (true) {
            IMethodRequest r;
            try {
                r = activationQueue.take();
                if (r.guard()) {
                    r.call();
                    activationQueue.remove(r);
                } else {
                    activationQueue.put(r);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

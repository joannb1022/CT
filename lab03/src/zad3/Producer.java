package zad3;

import java.util.Random;

public class Producer extends Thread {
    private final Buffer _buf;
    private final Random random = new Random();

    public Producer(Buffer _buf) {
        this._buf = _buf;
    }

    public void run() {
        for (int i = 0; i < 100; ++i) {
            _buf.put(i);
            try {
                Thread.sleep(random.nextInt(300));
            } catch (InterruptedException ignored) {

            }

        }
    }
}

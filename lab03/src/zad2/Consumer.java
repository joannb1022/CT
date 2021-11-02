package zad2;


import java.util.Random;

public class Consumer extends Thread {
    private final Buffer _buf;
    private final Random random = new Random();

    public Consumer(Buffer _buf) {
        this._buf = _buf;
    }

    public void run() {
        for (int i = 0; i < 100; ++i) {
            System.out.println(_buf.get());
            try {
                Thread.sleep(random.nextInt(300) + 100);
            } catch (InterruptedException ignored) {

            }
        }
    }
}

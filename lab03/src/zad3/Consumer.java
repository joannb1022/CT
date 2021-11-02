package zad3;



import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Consumer extends Thread{
    private final Buffer _buf;
    private final Random random = new Random();
    private static List<Integer> results = new LinkedList<>();

    public Consumer(Buffer _buf) {
        this._buf = _buf;
    }

    public void run() {
        for (int i = 0; i < 100; ++i) {
            try {
                int val = _buf.get();
                results.add(val);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(random.nextInt(300) + 100);
            } catch (InterruptedException ignored) {

            }
        }
    }

    public static List<Integer> getres()
    {
    return results;
    }
}

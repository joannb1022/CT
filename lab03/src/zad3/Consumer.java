package zad3;



import java.util.Random;

public class Consumer extends Thread{
    private final Buffer _buf;
    private final Random random = new Random();

    public Consumer(Buffer _buf) {
        this._buf = _buf;
    }

    public void run() {
        for (int i = 0; i < 100; ++i) {
            try {
                System.out.println(this._buf.get().getVal());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(random.nextInt(300) + 100);
            } catch (InterruptedException ignored) {

            }
        }
    }

}

package zad3;

import java.util.Random;

public class ProcessThread extends Thread {
    private final Buffer _buf;
    private final Random random = new Random();
    private final int stage;

    public ProcessThread(Buffer buf, int stage){
        this._buf = buf;
        this.stage = stage;
    }

    public void run(){
        for (int i = 0; i < 100; ++i) {
            try {
                _buf.procces(i, stage);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(random.nextInt(300));
            } catch (InterruptedException ignored) {

            }
        }

    }

}

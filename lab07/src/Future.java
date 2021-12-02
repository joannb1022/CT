import java.util.concurrent.Semaphore;

public class Future {

    private int val = 0;
    Semaphore sem = new Semaphore(0);

    public void changeVal(int val) {
        this.val = val;
        sem.release();
    }

    public int getVal() {
        try {
            sem.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this.val;
    }
}

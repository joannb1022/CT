package zad2;

public class BinarySemaphore {
    private boolean state;
    private int waiting;
    

    public BinarySemaphore(boolean state) {
        this.state = state;
        this.waiting = 0;
    }

    public synchronized void P() { //acquire
        this.waiting++;
        while (!this.state) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.waiting--;
        this.state = false;
    }

    public synchronized void V() { //release
        if (this.waiting > 0) {
            this.notify();
        }
        this.state = true;
    }
}


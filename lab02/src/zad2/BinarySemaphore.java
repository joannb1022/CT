package zad2;

public class BinarySemaphore {
    private boolean state = true;
    private int waiting = 0;

    public BinarySemaphore() {
    }

    public synchronized void P() {
        if(!state){
            waiting++;
                try {
                    wait();
                }
                catch (InterruptedException e){
                    e.printStackTrace();
            }
            waiting--;
        }
        state = false;
    }

    public synchronized void V() {
        state = true;
        if(waiting > 0){
            this.notify();
        }
    }
}

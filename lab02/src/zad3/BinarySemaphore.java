package zad3;

public class BinarySemaphore {
    private boolean state = true;
    private int waiting = 0;

    public BinarySemaphore(boolean state) {
        this.state = state;
    }

    public BinarySemaphore(){

    }

    public synchronized void P() {
        if(!state){
            waiting++;
            while(!state){
                try {
                    wait();
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
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

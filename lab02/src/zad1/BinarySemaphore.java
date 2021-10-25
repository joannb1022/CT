package zad1;

public class BinarySemaphore {
    private boolean state = true;
    private int waiting = 0;

    public BinarySemaphore() {
    }

    public synchronized void P() {  //opuszczanie
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

    public synchronized void V() { //podnoszenie
        if(waiting > 0){
            this.notify();
        }
        state = true;
    }
}

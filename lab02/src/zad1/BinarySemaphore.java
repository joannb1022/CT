package zad1;

public class BinarySemaphore {
    private boolean state = true;
    private int waiting = 0;

    public BinarySemaphore() {
    }

    public synchronized void P() {
        if(!state){
            System.out.println("jestem w P" + waiting);
            waiting++;
            while(!state){
                System.out.println("while");
                try {
                    wait();
                }
                catch (InterruptedException e){
                        e.printStackTrace();
                }
            }
            waiting--;
            System.out.println("zmniejsam w p" + waiting);
        }
    state = false;
    }

    public synchronized void V() {
        state = true;
        System.out.println("jestem w V");
        if(waiting > 0){
            this.notify();
            System.out.println("jestem w V " + waiting);
        }
    }
}

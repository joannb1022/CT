package zad3;

public class Semaphore {
    private BinarySemaphore mutexSemaphore;
    private BinarySemaphore waitSemaphore;
    private int resources;

    public Semaphore(int resources){
        this.resources = resources;
        mutexSemaphore = new BinarySemaphore(true);
        waitSemaphore = new BinarySemaphore(false);
    }

    public void P() {
        mutexSemaphore.P();
        resources-=1;

        if(resources < 0){
            mutexSemaphore.V();
            waitSemaphore.P();
        }
        else {
            mutexSemaphore.V();
        }

    }

    public void V() {
        mutexSemaphore.P();
        resources+=1;

        if(resources <= 0){
            waitSemaphore.V();
        }
        mutexSemaphore.V();
    }
}

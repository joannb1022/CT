package zad2;

public class Semaphore {
    private int resources;
    private final BinarySemaphore sem1;
    private final BinarySemaphore sem2;

    public Semaphore(int resources) {
        this.sem2 = new BinarySemaphore(true);
        this.sem1 = new BinarySemaphore(true);
        this.resources = resources;
    }

    public void P() { //acquire
        this.sem2.P();

        this.sem1.P();

        this.resources--;
        if (this.resources > 0) {
            this.sem2.V();
        }

        this.sem1.V();
    }

    public void V() { //release
        this.sem1.P();

        this.resources++;
        if (this.resources == 1) {
            this.sem2.V();
        }

        this.sem1.V();
    }
}

package zad2;
import java.util.LinkedList;

class Buffer {
    private final int size;
    private final LinkedList<Integer> bufferList = new LinkedList<>();
    private final Semaphore notAvailable;
    private final Semaphore available;
    private final BinarySemaphore binSem;


    public Buffer(int size) {
        this.size = size;
        this.available = new Semaphore(size);
        this.notAvailable = new Semaphore(0);
        this.binSem = new BinarySemaphore(true);
    }
    

    public void put(int i) {
        this.available.P();

        this.binSem.P();
        this.bufferList.add(i);
        this.binSem.V();

        this.notAvailable.V();
    }


    public int get() {
        this.notAvailable.P();

        this.binSem.P();
        int val = this.bufferList.removeFirst();
        this.binSem.V();

        this.available.V();
        return val;
    }
}
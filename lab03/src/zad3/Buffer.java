package zad3;


import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class Buffer {

    private final int size;
    private final LinkedList<Integer> bufferList = new LinkedList<>();
    private final Semaphore[] semaphoreArray;
    private final int processThreads;


    public Buffer(int size, int processThreads) {
        this.size = size;
        this.processThreads = processThreads;
        this.semaphoreArray = new Semaphore[size];
        for (int i = 0; i < size; i++) {
            if ( i == 0)
                semaphoreArray[i] = new Semaphore(size);
            else
                semaphoreArray[i] = new Semaphore(0);

        }
    }

    public void put ( int i){
        bufferList.add(i, i);
        semaphoreArray[0].release();
    }

    public LinkedList<Integer> getList(){
        return bufferList;
    }

    public void procces(int i, int stage) throws InterruptedException {
        semaphoreArray[stage].acquire();

        int val = transform(i);
        bufferList.add(stage, val);

        semaphoreArray[stage + 1].release();
    }

    public int get() throws InterruptedException {
        semaphoreArray[processThreads - 1].acquire();
        return bufferList.get(processThreads - 1);
    }

    public int transform(int i) {
        if (i % 2 == 0) {
            i = i * 21;
        } else if (i % 3 == 0) {
            i -= 2;
            i *= 50;
        } else if (i > 5) {
            i = 333;
        } else {
            i = i % 6;
        }

        return i;
    }
}


package zad3;

import java.util.ArrayList;

// to nie dziala xd

public class Buffer {

    private final int size;
    private final ArrayList<BufferItem> bufferList;
    private final int processThreads;
    private int lastAdded = 0;
    private int lastTaken = 0;
    private int items = 0;
    private final int[] lastProcessed;


    public Buffer(int size, int processThreads) {

        this.lastProcessed = new int[processThreads];
        this.size = size;
        this.processThreads = processThreads;
        this.bufferList = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            bufferList.add(i, null);
        }
        for (int i = 0; i < processThreads; i++) {
            lastProcessed[i] = 0;
        }

    }

    public synchronized void put(int i) {
        while (items >= size) {
            System.out.println("Wanting to add.. number of items = " + items);
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("ERROR");
            }
        }
        //szuka miejsca do wstawienia
        for (int idx = lastAdded; ; idx = (idx + 1) % size) {
            System.out.println(idx + " idx");
            if (bufferList.get(idx) == null) {
                System.out.println("Adding to " + idx);
                BufferItem item = new BufferItem(i);
                lastAdded = idx;
                bufferList.set(lastAdded, item);
                items++;
                break;
            }
        }
        notify();

    }

    public synchronized void process(int processID) throws InterruptedException {
        while (items <= 0) {
            System.out.println("Waiting for producer to put....");
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("ERROR");
            }
        }
        int index = lastProcessed[processID];
        BufferItem item = bufferList.get(index);
        while (item == null || processID != item.getProcessed()) {
            item = bufferList.get(index);
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("ERROR");
            }
        }
        int newVal = transform(processID, item.getVal());
        item.setVal(newVal);
        item.increaseProcess();
        bufferList.set(index, item);
        System.out.println("New value at " + index + " val = " + item.getVal() + " processID " + processID);
        lastProcessed[processID] = (index + 1) % size;
        notify();

    }

    public synchronized BufferItem get() throws InterruptedException {
        while (items <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("ERROR");
            }
        }

        BufferItem item = bufferList.get(lastTaken);
        while (item == null || item.getProcessed() != processThreads) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("ERROR");
            }
        }
        bufferList.set(lastTaken, null);
        items--;
        lastTaken = (lastTaken + 1) % this.size;

        notify();
        return item;
    }

    public int transform(int ID, int val) {
        if (ID == 0) {
            val = val + 21;
        } else if (ID % 2 == 0) {
            val *= 50;
        } else {
            val = val % 6;
        }
        return val;
    }
}


package zad1;

import java.util.LinkedList;

public class Buffer {
        private final int size;
        private final LinkedList <Integer> bufferList = new LinkedList<>();

        public Buffer(int size){
                this.size = size;
        }

        public synchronized void put(int i) {
                while(this.bufferList.size() >= this.size){
                        System.out.println("Waiting to put...");
                        try{
                                this.wait();
                        } catch (InterruptedException e){
                                System.out.println("ERROR");
                        }
                }
                System.out.println("Putting... " + i);
                this.bufferList.add(i);
                this.notify();
        }

        public synchronized int get() {
                while(this.bufferList.size() == 0){
                        try{
                                this.wait();
                                System.out.println("Waiting to get...");
                        } catch (InterruptedException e){
                                e.printStackTrace();
                                System.out.println("ERROR");
                        }
                }
                int val = this.bufferList.removeFirst();
                System.out.println("Getting item... " + val);
                this.notify();
                return val;
        }
}


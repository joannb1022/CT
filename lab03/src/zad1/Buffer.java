package zad1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Buffer {
        private final int size;
        private final List<Integer> bufferList = new ArrayList<>();

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
                int val = this.bufferList.remove(bufferList.size()-1);
                System.out.println("Getting item... " + val);
                this.notify();
                return val;
        }
}


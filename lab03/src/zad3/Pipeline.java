package zad3;


import java.util.LinkedList;

public class Pipeline {
    public static void main(String[] args) {
        int n = 5;

        Buffer buffer = new Buffer(15,5);

        LinkedList<Thread> threads = new LinkedList<>();

        threads.add(new Producer(buffer));
        for(int i=0; i<n; i++){
            threads.add(new ProcessThread(buffer,i+1));
        }
        threads.add(new Consumer(buffer));

        for(Thread thread: threads){
            thread.start();
        }
        for(Thread thread: threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

       for (Integer integ : buffer.getList()){
           System.out.println(integ);
        }
    }
}
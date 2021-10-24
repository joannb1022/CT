package zad2;

public class DThread extends Thread {
    private Counter cnt;

    public DThread(Counter cnt){
        this.cnt = cnt;
    }

    public void run() {
        for(int i =0;i<100000;i++) {
            cnt.dec();
        }
    }
}

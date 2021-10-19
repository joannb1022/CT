package zad2;

public class IThread extends  Thread {
    private Counter cnt;

    public IThread(Counter cnt){
        this.cnt = cnt;
    }

    public void run() {
        for(int i =0;i<10000;i++) {
            cnt.inc();
        }
    }
}

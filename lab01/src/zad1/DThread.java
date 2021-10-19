package zad1;

// Watek, ktory dekrementuje licznik 100.000 razy
public class DThread extends Thread{
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

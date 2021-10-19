package zad1;

// Watek, ktory inkrementuje licznik 100.000 razy
public class IThread extends  Thread {
    private Counter cnt;

    public IThread(Counter cnt){
        this.cnt = cnt;
    }

    public void run() {
        for(int i =0;i<100000;i++) {
            cnt.inc();
        }
    }
}

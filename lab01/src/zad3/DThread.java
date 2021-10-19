package zad3;

// Watek, ktory dekrementuje licznik 100.000 razy
public class DThread extends Thread{
    private Counter cnt;
    private SyncThreads sync;

    public DThread(Counter cnt, SyncThreads sync){
        this.sync = sync;
        this.cnt = cnt;
    }

    public void run() {
        for(int i =0;i<10000;i++) {
            while (!this.sync.checkDThread()){
                System.out.println("DTHREAD " + i + " " + this.cnt.value());
            }
            cnt.dec();
            this.sync.stopDThread(); //zmieniam na falsz
            this.sync.startIThread(); //zaczynam ten drugi watek

        }
    }
}

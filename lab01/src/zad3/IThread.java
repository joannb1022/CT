package zad3;

// Watek, ktory inkrementuje licznik 100.000 razy
public class IThread extends Thread {
    private Counter cnt;
    private SyncThreads sync;

    public IThread(Counter cnt, SyncThreads sync) {
        this.cnt = cnt;
        this.sync = sync;
    }

    public void run() {
        for (int i = 0; i < 10000; i++) {
            while (!this.sync.checkIThread()) {
                System.out.println("ITHREAD" + i + " " + this.cnt.value() + sync.checkIThread());
            }
            cnt.inc();
            this.sync.stopIThread(); //zmieniam na falsz
            this.sync.startDThread(); //zaczynam ten drugi watek
        }
    }
}


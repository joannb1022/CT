package zad3;

public class Race extends Thread{
    public static void main(String[] args) throws InterruptedException {
        Counter cnt = new Counter(0);
        SyncThreads sync = new SyncThreads();

        IThread iThread = new IThread(cnt, sync);
        DThread dThread = new DThread(cnt, sync);

        iThread.start();
        dThread.start();

        iThread.join();
        dThread.join();

        System.out.println("stan=" + cnt.value());
    }
}
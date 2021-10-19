package zad1;

public class Race extends Thread{
    public static void main(String[] args) throws InterruptedException {
        Counter cnt = new Counter(0);

        IThread iThread = new IThread(cnt);
        DThread dThread = new DThread(cnt);

        iThread.start();
        dThread.start();

        iThread.join();
        dThread.join();

        System.out.println("stan=" + cnt.value());
    }
}
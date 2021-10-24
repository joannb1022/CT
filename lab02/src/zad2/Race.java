package zad2;


public class Race {
    public static void main(String[] args) throws InterruptedException {


        Counter cnt = new Counter(0);
        IThread iThread = new IThread(cnt);
        DThread dThread = new DThread(cnt);
        iThread.start();
        dThread.start();

        iThread.join();
        dThread.join();


    }
}


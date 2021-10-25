package zad1;

import java.util.Arrays;
import java.util.HashMap;

public class Race {
    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 100; i++) {
            Counter cnt = new Counter(0);
            IThread iThread = new IThread(cnt);
            DThread dThread = new DThread(cnt);
            iThread.start();
            dThread.start();

            iThread.join();
            dThread.join();

            if (cnt.value() != 0)
                System.out.println(cnt.value());
        }
    }
}

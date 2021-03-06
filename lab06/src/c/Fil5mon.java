package c;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Fil5mon{
        public static void main(String[] args) throws InterruptedException {
        int n = 5;

        ArrayList<Filozof> filozofowie = new ArrayList<>();
        ArrayList<Widelec> widelce = new ArrayList<>();

        Semaphore semafor = new Semaphore(4);

        for (int i = 0; i < n; i++) {
            Widelec widelec = new Widelec();
            widelce.add(widelec);
        }

        for (int i = 0; i < n; i++) {
            Filozof filozof = new Filozof(i, widelce, semafor);
            filozofowie.add(filozof);
        }

        for (Filozof f : filozofowie)
            f.start();

        for (Filozof f : filozofowie)
            f.join();
    }
}

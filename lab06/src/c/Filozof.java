package c;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Filozof extends Thread {
    private int _licznik = 0;
    private int id;
    private final ArrayList<Widelec> widelce;
    private long czas;
    private long czasCzekania = 0;
    private final Semaphore semafor;

    public Filozof(int ID, ArrayList<Widelec> widelce, Semaphore semafor) {
        this.id = ID;
        this.widelce = widelce;
        this.semafor = semafor;
    }

    public void run() {
        Widelec prawy = widelce.get(id);
        Widelec lewy = widelce.get((id + 1) % 5);
        while (true) {
            long start = System.nanoTime();
            try {
                semafor.acquire(1);
                prawy.podnies();
                lewy.podnies();

                // jedzenie
                ++_licznik;

                // koniec jedzenia

                prawy.odloz();
                lewy.odloz();

                long t = System.nanoTime();
                Czekanie((t - start) / 1000000);

                if (_licznik % 100000 == 0) {
                    System.out.println(_licznik + " " + czasCzekania);
//                System.out.println("Filozof: " + Thread.currentThread() +
//                        "jadlem " + _licznik + " razy, czas czkeania + " + czasCzekania);
                }

                semafor.release(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }

    public void Czekanie(long t) {
        this.czasCzekania += t;
    }
}


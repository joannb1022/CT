package a;

import javax.crypto.AEADBadTagException;
import java.util.ArrayList;

public class Filozof extends Thread {
    private int _licznik = 0;
    private int id;
    private ArrayList<Widelec> widelce;
    private long czas;

    public Filozof(int ID, ArrayList<Widelec> widelce) {
        this.id = ID;
        this.widelce = widelce;
    }

    public void run() {
        while (true) {
            Widelec prawy = widelce.get(id);
            Widelec lewy = widelce.get((id + 1) % 5);

            long start = System.nanoTime();

            prawy.podnies();
            System.out.println("Tutaj fil " + id + " podniosłem widelec prawy");
            lewy.podnies();
            System.out.println("Tutaj fil " + id + " podniosłem widelec lewy");

            // jedzenie
            ++_licznik;
            if (_licznik % 1000 == 0) {
                System.out.println("Filozof: " + Thread.currentThread() +
                        "jadlem " + _licznik + " razy");
            }
            // koniec jedzenia

            prawy.odloz();
            lewy.odloz();

            long end = System.nanoTime();
            this.czas = (start - end) / 1000000;
        }
    }

    public long getTime() {
        return czas;
    }

    public int getID() {
        return this.id;
    }
}

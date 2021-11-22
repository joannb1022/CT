package b;


import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Filozof extends Thread{
    private int _licznik = 0;
    private int id;
    private ArrayList<Widelec> widelce;
    private long czas;
    private long czasCzekania = 0;
    private Semaphore semafor;

    public Filozof(int ID, ArrayList<Widelec> widelce, Semaphore semafor){
        this.id = ID;
        this.widelce = widelce;
        this.semafor = semafor;
    }

    public void run() {
        while (true) {
            Widelec prawy = widelce.get(id);
            Widelec lewy = widelce.get((id + 1) % 5);

            long start = System.nanoTime();


            try {
                semafor.acquire(1);
                prawy.podnies();
//                System.out.println("Tutaj fil " + id + " podniosłem widelec prawy");
                lewy.podnies();
//                System.out.println("Tutaj fil " + id + " podniosłem widelec lewy");

                semafor.release(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            // jedzenie
            ++_licznik;

            // koniec jedzenia

            prawy.odloz();
//            System.out.println("Odkladam prawy " + id);
            lewy.odloz();
//            System.out.println("Odkladam lewy " + id);


            long t = System.nanoTime();
            Czekanie((t-start) / 1000000);

            if (_licznik % 100000 == 0 && id == 1) {
                System.out.println(_licznik + " " + czasCzekania);

//                System.out.println("Filozof: " + Thread.currentThread() +
//                        "jadlem " + _licznik + " razy, czas czkeania + " + czasCzekania);
            }
            long end = System.nanoTime();
            this.czas = (end - start) / 1000000;


        }
    }

    public long getTime(){
        return czas;
    }

    public int getID(){
        return this.id;
    }

    public int getLicznik(){
        return this._licznik;
    }

    public void Czekanie(long t){
        this.czasCzekania +=t;
    }

    public long getWaitingTime(){
        return this.czasCzekania;
    }
}

package b;

public class Widelec {

    private boolean podniesiony = false;

    public Widelec(){

    }
    public synchronized void podnies() {
        try{
            while(podniesiony)
                wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        podniesiony = true;

    }
    public synchronized void odloz() {
        podniesiony = false;
        notify();
    }
}

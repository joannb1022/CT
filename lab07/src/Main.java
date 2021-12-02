import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        int size = 20;
        Proxy proxy = new Proxy(size);

        ArrayList<Producer> producers_list = new ArrayList<>();
        ArrayList<Consumer> consumers_list = new ArrayList<>();

        int consumers = 3;
        int producers = 5;

        for (int i = 0; i < consumers; i++) {
            Consumer p = new Consumer(i + 1, proxy);
            p.start();
            consumers_list.add(p);
        }

        for (int i = 0; i < producers; i++) {
            Producer p = new Producer(i + 1, proxy);
            p.start();
            producers_list.add(p);
        }

        for (Consumer c : consumers_list)
            c.join();
        for (Producer p : producers_list)
            p.join();
    }
}

package zad2;

public class PKmon {
    public static void main(String[] args) {
        var buffer = new Buffer(10);

        long startTime = System.nanoTime();

        int n1 = 2;
        int n2 = 5;

            Producer[] producers = new Producer[n1];
            Consumer[] consumers = new Consumer[n2];

            for (int i = 0; i < n1; i++) {
                producers[i] = new Producer(buffer);
                producers[i].start();
            }

            for (int i = 0; i < n2; i++) {
                consumers[i] = new Consumer(buffer);
                consumers[i].start();
            }

            try {
                for (int i = 0; i < n1; i++) {
                    producers[i].join();
                }
                for (int i = 0; i < n2; i++) {
                    consumers[i].join();
                }
            } catch (InterruptedException e) {
                System.out.println("ERROR");
            }

           long endTime = System.nanoTime();
            System.out.println("Time = " + (endTime - startTime));
    }
}

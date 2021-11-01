package zad1;

public class PKmon {
    public static void main(String[] args) {

        Buffer buffer = new Buffer(10);

        int n1 = Integer.parseInt(args[0]); //liczba producentów
        int n2 = Integer.parseInt(args[1]); //liczba konsumentów

        long endTime, startTime;

        if (n1 > 1 && n2 > 1) {

            startTime = System.nanoTime();

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

            endTime = System.nanoTime();

        } else {

            startTime = System.nanoTime();

            Producer producer = new Producer(buffer);
            Consumer consumer = new Consumer(buffer);

            producer.start();
            consumer.start();

            try {
                producer.join();
                consumer.join();
            } catch (InterruptedException e) {
                System.out.println("ERROR");
            }

           endTime = System.nanoTime();
        }

        System.out.println("Time = " + (endTime - startTime));
    }
}
package dod;

public class PKmon {
    private static int turns_no = 98;

    public static void main(String[] args) {
        long start = System.nanoTime();

        Buffer buff = new Buffer(10, 5);
        Producer producer = new Producer(buff, turns_no);
        Processor proc1 = new Processor(buff, turns_no, 1);
        Processor proc2 = new Processor(buff, turns_no, 2);
        Processor proc3 = new Processor(buff, turns_no, 3);
        Processor proc4 = new Processor(buff, turns_no, 4);
        Processor proc5 = new Processor(buff, turns_no, 5);
        Consumer consumer = new Consumer(buff, turns_no);

        producer.start();
        proc1.start();
        proc2.start();
        proc3.start();
        proc4.start();
        proc5.start();
        consumer.start();

        try {
            producer.join();
            proc1.join();
            proc2.join();
            proc3.join();
            proc4.join();
            proc5.join();
            consumer.join();
        } catch (Exception ex) { }

        long end = System.nanoTime();

        float elapsed = (end - start)/1000000;
        System.out.println("Elapsed time in ms:" + elapsed);
    }
}

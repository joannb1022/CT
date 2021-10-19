package zad2;

import java.util.*;

public class Race extends Thread {
    public static void main(String[] args) throws InterruptedException {

        HashMap<Integer, Integer> histogram = new HashMap<>();

        for (int i = 0; i < 100; i++) {
            Counter cnt = new Counter(0);
            IThread iThread = new IThread(cnt);
            DThread dThread = new DThread(cnt);
            iThread.start();
            dThread.start();

            iThread.join();
            dThread.join();

            Integer how_many = histogram.get(cnt.value());

            if (how_many == null) {
                histogram.put(cnt.value(), 1);
            } else {
                histogram.put(cnt.value(), histogram.get(cnt.value()) + 1);
            }
        }

        Integer[] values = histogram.keySet().toArray(new Integer[0]);
        int[] freq = new int[histogram.size()];
        Arrays.sort(values);

        int i = 0;
        for (Integer j : values) {
            freq[i++] = histogram.get(j);
        }

        for (int k = 0; k < histogram.size(); k++) {
            System.out.println(values[k] + " " + freq[k]);
        }
    }
}

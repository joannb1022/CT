package zad1;

import java.util.Arrays;
import java.util.HashMap;

public class Race {
    public static void main(String[] args) throws InterruptedException {


            Counter cnt = new Counter(0);
            IThread iThread = new IThread(cnt);
            DThread dThread = new DThread(cnt);
            iThread.start();
            dThread.start();

            iThread.join();
            dThread.join();


            System.out.println(cnt.value());
//        }

//        Integer[] values = histogram.keySet().toArray(new Integer[0]);
//        int[] freq = new int[histogram.size()];
//        Arrays.sort(values);
//
//        int i = 0;
//        for (Integer j : values) {
//            freq[i++] = histogram.get(j);
//        }
//
//        for (int k = 0; k < histogram.size(); k++) {
//            System.out.println(values[k] + " " + freq[k]);
//        }
    }
}

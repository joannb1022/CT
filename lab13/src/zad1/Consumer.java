package zad1;

import org.jcsp.lang.Alternative;
import org.jcsp.lang.CSProcess;
import org.jcsp.lang.Guard;
import org.jcsp.lang.One2OneChannelInt;

public class Consumer implements CSProcess {

    private final One2OneChannelInt[] in;
    private final int num_items;

    Consumer(One2OneChannelInt[] channel_in, int num_items){

        this.in = channel_in;
        this.num_items = num_items;
    }

    @Override
    public void run() {
        long start = System.nanoTime();
        var guards = new Guard[in.length];
        for (int i = 0; i < in.length; i++){
            guards[i] = in[i].in();
        }
        var alternatives = new Alternative(guards);
        for (int i =0 ; i< num_items; i++){
            int idx = alternatives.select();
            int item = in[idx].in().read();
            System.out.println("Consumer " + item);
        }
        long end = System.nanoTime();
        long time =(end - start) / 1000000;
        System.out.println("Time: " + time);
    }
}


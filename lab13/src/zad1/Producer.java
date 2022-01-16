package zad1;

import org.jcsp.lang.Alternative;
import org.jcsp.lang.CSProcess;
import org.jcsp.lang.Guard;
import org.jcsp.lang.One2OneChannelInt;

public class Producer implements CSProcess {
    private final One2OneChannelInt[] out;
    private final One2OneChannelInt[] buff;
    int num_items;

    public Producer(One2OneChannelInt[] out, One2OneChannelInt[] buff, int num_items){
        this.out = out;
        this.buff = buff;
        this.num_items = num_items;
    }
    @Override
    public void run() {
        var guards = new Guard[buff.length];
        for (int i = 0; i < out.length; i++){
            guards[i] = buff[i].in();
        }

        var alternatives = new Alternative(guards);

        for (int i = 0; i < num_items; i++){
            var idx = alternatives.select();
            buff[idx].in().read();

            int item = (int) (Math.random() * 1000);
            out[idx].out().write(item);

        }

    }
}

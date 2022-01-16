package zad1;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.One2OneChannelInt;

public class Buffer implements CSProcess {
    private final One2OneChannelInt channel_in;
    private final One2OneChannelInt channel_out;
    private final One2OneChannelInt channel_more;
    
    public Buffer(One2OneChannelInt channel_prod, One2OneChannelInt channel_out, One2OneChannelInt channel_more){
        this.channel_in = channel_prod;
        this.channel_out = channel_out;
        this.channel_more = channel_more;
    }

    @Override
    public void run() {
        while(true){
            channel_more.out().write(0);
            channel_out.out().write(channel_in.in().read());
        }
    }
}

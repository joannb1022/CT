package zad3;

import java.util.Random;

public class ProcessThread extends Thread {
    private final Buffer _buf;
    private final Random random = new Random();
    private final int processID;


    public ProcessThread(Buffer buf, int processID){
        this._buf = buf;
        this.processID = processID;
    }

    public void run(){
        for (int i = 0; i < 100; ++i) {
            try {
                _buf.process(processID);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}

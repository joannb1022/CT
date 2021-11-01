package zad1;

class Producer extends Thread {
    private Buffer _buf;

    public Producer(Buffer buffer){
        this._buf = buffer;
    }

    public void run() {
        for (int i = 0; i < 100; ++i) {
            _buf.put(i);
            try{
                sleep(1000);
            }
            catch(InterruptedException e){
                System.out.println("ERROR");
            }
        }
    }
}
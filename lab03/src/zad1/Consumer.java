package zad1;

class Consumer extends Thread {
    private Buffer _buf;

    public Consumer(Buffer buffer){
        this._buf = buffer;
    }

    public void run() {
        for (int i = 0; i < 100; ++i) {
            System.out.println(this._buf.get());
//            try{
//                sleep(1000);
//            }
//            catch(InterruptedException e){
//                System.out.println("ERROR");
//            }
        }
    }
}
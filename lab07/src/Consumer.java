
class Consumer extends Thread {
    private Proxy proxy;
    private int ID;

    public Consumer(int ID, Proxy proxy) {
        this.ID = ID;
        this.proxy = proxy;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            Future get = this.proxy.get();
            System.out.println("consumer " + ID + " got value " + get.getVal());
        }
        try {
            this.proxy.getScheduler().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
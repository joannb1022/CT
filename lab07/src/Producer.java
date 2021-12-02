public class Producer extends Thread {
    private Proxy proxy;
    private int ID;

    public Producer(int ID, Proxy proxy) {
        this.ID = ID;
        this.proxy = proxy;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            Future adding = this.proxy.add(ID);
            System.out.println("Producer added val " +  adding.getVal() + " " + i);
        }
        try {
            this.proxy.getScheduler().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

package zad2;

public class ListOneLockThread extends Thread {
    private ListOneLock listOneLock;

    public ListOneLockThread(ListOneLock list) {
        this.listOneLock = list;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                this.listOneLock.add(i);
                boolean check = this.listOneLock.contains(i + 1);
                System.out.println("Lista nie zawiera : " + (i + 1) + " " + check);
            } else {
                this.listOneLock.add("NIEPARZYSTA");
                boolean check1 = this.listOneLock.contains(i - 1);
                System.out.println("Lista zawiera : " + (i - 1) + " " + check1);

                this.listOneLock.remove(i-1);
                check1 = this.listOneLock.contains(i - 1);
                System.out.println("Lista zawiera (druga proba) : " + (i - 1) + " " + check1);
            }
        }
    }

    
}

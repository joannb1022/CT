package zad2;

public class ListManyLocksThread extends Thread {
    private ListManyLocks listManyLocks;

    public ListManyLocksThread(ListManyLocks list) {
        this.listManyLocks = list;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                this.listManyLocks.add(i);
                boolean check = this.listManyLocks.contains(i + 1);
                System.out.println("Lista nie zawiera : " + (i + 1) + " " + check);
            } else {
                this.listManyLocks.add("NIEPARZYSTA");
                boolean check1 = this.listManyLocks.contains(i - 1);
                System.out.println("Lista zawiera : " + (i - 1) + " " + check1);

                this.listManyLocks.remove(i-1);
                check1 = this.listManyLocks.contains(i - 1);
                System.out.println("Lista zawiera (druga proba) : " + (i - 1) + " " + check1);
            }
        }
    }
}

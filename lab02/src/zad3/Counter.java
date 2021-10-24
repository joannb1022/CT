package zad3;

public class Counter {
    private Semaphore sem;
    private int _val;
    public Counter(int n) {
        _val = n;
        sem = new Semaphore(1);
    }
    public void inc() {
        sem.P();
        _val++;
        sem.V();
    }
    public void dec() {
        sem.P();
        _val--;
        sem.V();
    }
    public int value() {
        return _val;
    }
}

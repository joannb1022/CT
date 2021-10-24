package zad1;

public class Counter {
    private BinarySemaphore sem;
    private int _val;
    public Counter(int n) {
        _val = n;
        sem = new BinarySemaphore();
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

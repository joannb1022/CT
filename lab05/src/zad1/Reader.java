package zad1;

class Reader extends Thread {
    private int nr;
    private Library library;
    public Reader(int nr, Library library) {
        super();
        this.nr = nr;
        this.library = library;
    }
    @Override
    public void run() {
        int i = 0;
        while (i++ < 1000) {
            library.beginReading();
            library.endReading();
        }
    }
}


public class Servant {

    private int size;
    private final int[] buffer;

    public Servant(int size) {
        this.size = size;
        this.buffer = new int[size];

        for (int i = 0; i < this.size; i++)
            this.buffer[i] = 0; //traktuje zero jako pusta komórka
    }

    public void add(int val) {
        int index = findEmpty();
        this.buffer[index] = val;
    }

    public int remove() {
        int index = findElement();
        int value = buffer[index];
        buffer[index] = 0;
        return value;
    }

    public boolean isEmpty() {
        for (int i = 0; i < this.size; i++) {
            if (this.buffer[i] != 0)
                return false;
        }
        return true;
    }

    public boolean isFull() {
        for (int i = 0; i < this.size; i++) {
            if (this.buffer[i] == 0)
                return false;
        }
        return true;
    }

    public int findEmpty() {
        for (int i = 0; i < this.size; i++) {
            if (buffer[i] == 0) {
                return i;
            }
        }
        throw new IllegalStateException("at least one files should be free");
    }

    public int findElement() {
        for (int i = 0; i < this.size; i++) {
            if (buffer[i] != 0) {
                return i;
            }
        }
        throw new IllegalStateException("at least one files should be free");
    }
}


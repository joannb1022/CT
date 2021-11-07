package zad3;

public class BufferItem {
    private int processed;
    private int val;

    public BufferItem(int val){
        this.val = val;
        this.processed = 0;
    }

    public int getProcessed(){
        return processed;
    }
    public int getVal(){
        return val;
    }

    public void setVal(int val){
        this.val = val;
    }

    public synchronized void increaseProcess(){
        processed+=1;
    }
}

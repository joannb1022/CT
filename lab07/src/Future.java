public class Future {

    private int val = 0;

    public boolean isAvailable(){
        return val!=0;
    }

    public void changeVal(int val){
        this.val = val;
    }

    public int getVal(){
        return this.val;
    }
}

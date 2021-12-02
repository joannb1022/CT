public class MethodRequestAdd implements IMethodRequest {
    private Future future;
    private Servant servant;
    private int value;

    public MethodRequestAdd(int val, Future future, Servant servant) {
        this.value = val;
        this.future = future;
        this.servant = servant;
    }

    @Override
    public void call() {
        this.servant.add(this.value);
        this.future.changeVal(this.value);
    }

    @Override
    public boolean guard() {
        return !this.servant.isFull();
    }
}

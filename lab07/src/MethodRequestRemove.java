public class MethodRequestRemove implements IMethodRequest{
    private Future future;
    private Servant servant;


    public MethodRequestRemove(Future future, Servant servant){
        this.future = future;
        this.servant = servant;
    }
    @Override
    public void call() {
        int val = this.servant.remove();
        future.changeVal(val);
    }

    @Override
    public boolean guard() {
        return !this.servant.isEmpty();
    }
}

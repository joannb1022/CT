public class Proxy {
    private Scheduler scheduler;
    private Servant buffer; //servant

    public Proxy(int size){
        this.scheduler = new Scheduler();
        this.buffer = new Servant(size);
    }

    public Future add(int val){
        Future future = new Future();
        IMethodRequest request_add = new MethodRequestAdd(val, future, this.buffer);
//        scheduler.enqueue(request_add);
        return future;

    }

    public Future get(){
        Future future = new Future();
        IMethodRequest request_remove = new MethodRequestRemove(future, this.buffer);
//        scheduler.enqueue(request_remove);
        return future;
    }

}

package zad3;

public class SyncThreads {
    private boolean IThreadWorks;
    private boolean DThreadWorks;

    public SyncThreads(){
        this.IThreadWorks = true;
        this.DThreadWorks = false;
    }

    public boolean checkIThread(){
        return  this.IThreadWorks;
    }

    public boolean checkDThread(){
        return this.DThreadWorks;
    }

    public void stopIThread(){
        this.IThreadWorks = false;
    }
    public void stopDThread(){
        this.DThreadWorks = false;
    }
    public void startIThread(){
        this.IThreadWorks = true;
    }
    public void startDThread(){
        this.DThreadWorks = true;
    }
}

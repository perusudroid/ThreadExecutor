package com.cognitive.executorrecycler.sequence;


class SeqRunnable implements Runnable{
	SequentialThread st;
    int result;
    Object sharedObj = new Object();
    SeqRunnable(SequentialThread st, int result){
        this.st = st;
        this.result = result;
    }
    @Override
    public void run() {
    	st.printNum(result);
    }
}

public class SequentialThreadWorker  {
	
   
    public static void main(String[] args) {
        // Shared object
        SequentialThread st = new SequentialThread();
        // Creating 3 threads
        Thread t1 = new Thread(new SeqRunnable(st, 1), "Thread1");
        Thread t2 = new Thread(new SeqRunnable(st, 2), "Thread2");
        Thread t3 = new Thread(new SeqRunnable(st, 0), "Thread3");

        t1.start();
        t2.start();
        t3.start();
    }

}

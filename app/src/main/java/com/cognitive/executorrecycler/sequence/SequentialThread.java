package com.cognitive.executorrecycler.sequence;

public class SequentialThread {

	int number = 1; // changes
    
    SequentialThread(){
    	
    }
    
    public void printNum(int result){
    	
        synchronized(this) {
        	
            while (number < 9) {
                while(number % 3 != result){
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + " - " + number++);
                this.notifyAll();
            }
        }
    }
}


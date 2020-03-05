package com.cognitive.executorrecycler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class ThreadOOEActivity extends AppCompatActivity {

    private static final String TAG = "Threads";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        try {
            callAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void callAll() throws InterruptedException {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    callA();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    callB();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });


        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    callC();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });


        t1.start();
        t2.start();
        t3.start();

    }

    private void callA() throws InterruptedException {

        //Thread.sleep(500);

        synchronized (this){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //System.out.println("This is call A");
            Log.d(TAG, "run: This is call A");
            ThreadOOEActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(ThreadOOEActivity.this, "This is call A", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    private void callC() throws InterruptedException {

        //Thread.sleep(500);

        synchronized (this){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
           //System.out.println("This is call C");
            Log.d(TAG, "run: This is call C");
            ThreadOOEActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(ThreadOOEActivity.this, "This is call C", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void callB() throws InterruptedException {

        Thread.sleep(1000);

        synchronized (this){
            //notify();
            notifyAll();
            //System.out.println("This is call B");
            Log.d(TAG, "run: This is call B");
            ThreadOOEActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(ThreadOOEActivity.this, "This is call B", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    private void finalCall(int type) {

    }
}

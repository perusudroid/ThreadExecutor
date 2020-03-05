package com.cognitive.executorrecycler.tpe;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

class LooperThread extends Thread {

    private static final String TAG = "Looper Thread";
    public Handler mHandler;

    public void run() {
        Looper.prepare();

        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {

                Log.d(TAG, "handleMessage: ");
                return true;
            }
        });

        Looper.loop();
    }
}

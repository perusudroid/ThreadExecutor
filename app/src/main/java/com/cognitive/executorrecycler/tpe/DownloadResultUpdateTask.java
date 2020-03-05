package com.cognitive.executorrecycler.tpe;

import android.widget.TextView;

public class DownloadResultUpdateTask implements Runnable{
    private TextView message,tvProgress;
    private String backgroundMsg, progressMsg;

    public DownloadResultUpdateTask(TextView msg, TextView tvProgress){
        this.message = msg;
        this.tvProgress = tvProgress;
    }

    public void setBackgroundMsg(String bmsg){
        backgroundMsg = bmsg;
    }

    public void setProgressMsg(String bmsg){
        progressMsg = bmsg;
    }

    @Override
    public void run() {
        message.setText(backgroundMsg);
        tvProgress.setText(progressMsg);
    }
}

package com.cognitive.executorrecycler.tpe;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class DownloadTask implements Runnable{

    private String url;
    private String localFile;
    private DownloadResultUpdateTask resultUpdateTask;

    public DownloadTask(String urlIn, String localFileIn,
                        DownloadResultUpdateTask drUpdateTask){
        this.url = urlIn;
        this.localFile = localFileIn;
        this.resultUpdateTask = drUpdateTask;
    }

    @Override public void run() {
       boolean isDownloaded  = download();

        resultUpdateTask.setBackgroundMsg(isDownloaded ? "Downloaded" : "Failed");
        MyThreadPoolExecutor.getIntsance().getMainThreadExecutor()
                .execute(resultUpdateTask);
    }


    private boolean download(){
        OkHttpClient httpClient = new OkHttpClient();
        Call call = httpClient.newCall(new Request.Builder().url(url).get().build());
        try {
            Response response = call.execute();
            if (response.code() == 200) {
                InputStream inputStream = null;
                try {
                    inputStream = response.body().byteStream();
                    FileOutputStream fileOutput =
                            new FileOutputStream(localFile);

                    byte[] buffer = new byte[1024];
                    int bufferLength = 0;

                    long downloaded = 0;
                    long target = response.body().contentLength();

                    publishProgress(0L, target);
                    while (true) {
                        int readed = inputStream.read(buffer);
                        if(readed == -1){
                            break;
                        }
                        //write buff
                        downloaded += readed;
                        publishProgress(downloaded, target);

                        fileOutput.write(buffer, 0, bufferLength);
                    }

                    return true;
                } catch (IOException ignore) {
                    return false;
                } finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                }
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void publishProgress(long values, long target) {
        resultUpdateTask.setProgressMsg(String.valueOf(values));
        MyThreadPoolExecutor.getIntsance().getMainThreadExecutor()
                .execute(resultUpdateTask);
    }

}

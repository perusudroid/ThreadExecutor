package com.cognitive.executorrecycler.tpe;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyThreadPoolExecutor {

    private final ThreadPoolExecutor downloadThreadPool;
    private final BlockingQueue<Runnable> downaloadWorkQueue;

    private static final int CORE_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 5;
    private static final int KEEP_ALIVE_TIME = 50;

    private static MyThreadPoolExecutor myThreadPoolExecutor = new MyThreadPoolExecutor();
    private static MainThreadExecutor handler = new MainThreadExecutor();

    public static MyThreadPoolExecutor getIntsance() {
        return myThreadPoolExecutor;
    }

    private MyThreadPoolExecutor() {
        downaloadWorkQueue = new LinkedBlockingQueue<>();

        downloadThreadPool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE,
                KEEP_ALIVE_TIME, TimeUnit.MILLISECONDS, downaloadWorkQueue);
    }

    public ThreadPoolExecutor getDownloadThreadPool() {
        return downloadThreadPool;
    }

    public MainThreadExecutor getMainThreadExecutor() {
        return handler;
    }
}
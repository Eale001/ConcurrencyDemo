package com.eale.io.bio.handler;

import java.util.concurrent.*;

/**
 * @Author Administrator
 * @Date 2021/2/25
 **/
public class HandlerExecutorPool {

    private ExecutorService executorService;

    public HandlerExecutorPool(int maxPoolSize,int  queueSize){
        executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                maxPoolSize,120L, TimeUnit.SECONDS,new ArrayBlockingQueue<>(queueSize));
    }

    public void execute(Runnable runnable){
        executorService.execute(runnable);
    }

}

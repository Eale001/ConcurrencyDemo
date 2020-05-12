package com.eale.concurrent.thread;

import java.util.concurrent.*;

/**
 * @Author Administrator
 * @Date 2020/5/11
 * @Description // 返回值的线程任务
 * @Version 1.0
 **/
public class CallableThread {

    private static ExecutorService executorService = Executors.newSingleThreadExecutor();

    private static Callable<Object> task = new Callable<Object>() {
        @Override
        public Object call() throws Exception {

            return task.toString();
        }
    };

    public static void main(String[] args) {
        try {
            Future<Object> future = executorService.submit(task);
            // 等待到任务执行完毕返回结果
            // 如果任务执行出错,这里会抛ExecutionException
            future.get();

            // 等待三秒 超时会抛 TimeOutException
            future.get(3,TimeUnit.SECONDS);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }


}

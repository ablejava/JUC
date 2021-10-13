package com.juc.thread.executeService;

import java.util.concurrent.*;

public class CustomRejected {
    public static void main(String[] args) {
        ExecutorService service = new ThreadPoolExecutor(4,4, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(6), Executors.defaultThreadFactory(), new MyHandler());
    }


    static class MyHandler implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

            if (executor.getQueue().size() <10000){
                //todo
                
            }
        }
    }
}

package com.juc.thread.countDownLatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 模拟火箭点火，所有自检完毕后才进行点火
 * Created by Administrator on 2019/2/24.
 */
public class CountDownLatch_T1 implements Runnable {
    static final CountDownLatch end = new CountDownLatch(10);
    static final CountDownLatch_T1 demo = new CountDownLatch_T1();

    @Override
    public void run() {

        // 模拟检查任务
        try {
            Thread.sleep(new Random().nextInt(10) *1000);
            System.out.println("check complete");
            end.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 1.提交线程任务
        ExecutorService exec = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            exec.submit(demo);
        }
        // 2.等待检查,当线程数量到10的时候开始发射
        end.await();
        System.out.print("fire!");
        exec.shutdown();
    }
}

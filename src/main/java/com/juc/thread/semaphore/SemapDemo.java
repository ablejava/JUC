package com.juc.thread.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by Administrator on 2019/2/24.
 */
public class SemapDemo implements Runnable {
    final Semaphore semp = new Semaphore(5);
    @Override
    public void run() {

        try {
            semp.acquire();// 方法会一致等待
            // semp.tryAcquire() // 方法尝试获得锁，如果成功返回true执行否则放回false释放
            // 模拟耗时操作
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getId()+": done !");
            semp.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(20);
        final SemapDemo demo = new SemapDemo();
        for (int i = 0; i < 20; i++) {
            exec.submit(demo);
        }
    }
}
